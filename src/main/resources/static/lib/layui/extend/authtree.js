/*
* @Author: Jeffrey Wang
* @Date:   2018-03-16 18:24:47
* @Last Modified by:   Jeffrey Wang
* @Last Modified time: 2018-10-12 10:38:52
*/
// 节点树
layui.define(['jquery', 'form'], function(exports){
	$ = layui.jquery;
	form = layui.form;
	var MOD_NANE = 'authtree';

	obj = {
		// 渲染 + 绑定事件
		openIconContent: '&#xe625;',
		closeIconContent: '&#xe623;',
		// 保存节点数据
		checkedNode : {},
		notCheckedNode : {},
		// 临时保存最新操作影响的节点
		lastCheckedNode : {},
		lastNotCheckedNode : {},
		// 已经渲染过的树，可用来获取配置，{ dst: {trees: '树的节点数据', opt: '配置'} }
		renderedTrees: {},
		// 使用 layui 的监听事件
		on: function(events, callback) {
			return layui.onevent.call(this, MOD_NANE, events, callback);
		},
		/**
		 * 渲染DOM并绑定事件
		 * @param  {[type]} dst       [目标ID，如：#test1]
		 * @param  {[type]} trees     [数据，格式：{}]
		 * @param  {[type]} inputname [上传表单名]
		 * @param  {[type]} layfilter [lay-filter的值]
		 * @param  {[type]} openall [是否展开全部]
		 * @return {[type]}           [description]
		 */
		render: function(dst, trees, opt){
			var inputname = opt.inputname ? opt.inputname : 'menuids[]';
			opt.inputname = inputname;
			var layfilter = opt.layfilter ? opt.layfilter : 'checkauth';
			opt.layfilter = layfilter;
			var openall = opt.openall ? opt.openall : false;
			opt.openall = openall;
			var dblshow = opt.dblshow ? opt.dblshow : false;
			opt.dblshow = dblshow;
			var dbltimeout = opt.dbltimeout ? opt.dbltimeout : 180;
			opt.dbltimeout = dbltimeout;
			var openchecked = typeof opt.openchecked !== 'undefined' ? opt.openchecked : true;
			opt.openchecked = openchecked;
			var autoclose = typeof opt.autoclose !== 'undefined' ? opt.autoclose : true;
			opt.autoclose = autoclose;
			var autochecked = typeof opt.autochecked !== 'undefined' ? opt.autochecked : true;
			opt.autochecked = autochecked;

			// 不启用双击展开，单击不用延迟
			if (!dblshow) {
				dbltimeout = 0;
			}

			// 记录渲染过的树
			obj.renderedTrees[dst] = {trees: trees, opt: opt};

			$(dst).html(obj.renderAuth(trees, 0, {inputname: inputname, layfilter: layfilter, openall: openall, openchecked: openchecked}));
			form.render();
			// 变动则存一下临时状态
			obj._saveNodeStatus(dst);

			// 开启自动宽度优化
			obj.autoWidth(dst);
			// 备注：如果使用form.on('checkbox()')，外部就无法使用form.on()监听同样的元素了（LAYUI不支持重复监听了）。
			// form.on('checkbox('+layfilter+')', function(data){
			// 	/*属下所有权限状态跟随，如果选中，往上走全部选中*/
			// 	var childs = $(data.elem).parent().next().find('input[type="checkbox"]').prop('checked', data.elem.checked);
			// 	if(data.elem.checked){
			// 		/*查找child的前边一个元素，并将里边的checkbox选中状态改为true。*/
			// 		$(data.elem).parents('.auth-child').prev().find('input[type="checkbox"]').prop('checked', true);
			// 	}
			// 	/*console.log(childs);*/
			// 	form.render('checkbox');
			// });
			
			// 解决单击和双击冲突问题的 timer 变量
			var timer = 0;
			$(dst).find('.auth-single:first').unbind('click').on('click', '.layui-form-checkbox', function(){
				var that = this;
				clearTimeout(timer);
				// 双击判断需要的延迟处理
				timer = setTimeout(function(){
					var elem = $(that).prev();
					var checked = elem.is(':checked');

					if (autochecked) {
						if (checked) {
							/*查找child的前边一个元素，并将里边的checkbox选中状态改为true。*/
							elem.parents('.auth-child').prev().find('input[type="checkbox"]').prop('checked', true);
						}
						var childs = elem.parent().next().find('input[type="checkbox"]').prop('checked', checked);
					}
					if (autoclose) {
						if (checked) {
							// pass
						} else {
							// 自动关闭父级选中节点
							obj._autoclose($(that).parent());
						}
					}
					/*console.log(childs);*/
					form.render('checkbox');
					// 变动则存一下临时状态
					obj._saveNodeStatus(dst);
					// 触发 change 事件
					obj._triggerEvent(dst, 'change', {othis: $(that)});
					obj.autoWidth(dst);
				}, dbltimeout);
			});
			/*动态绑定展开事件*/
			$(dst).unbind('click').on('click', '.auth-icon', function(){
				obj.iconToggle(dst, this);
			});
			/*双击展开*/
			if (dblshow) {
				$(dst).find('.auth-single:first').unbind('dblclick').on('dblclick', '.layui-form-checkbox', function(e){
					clearTimeout(timer);
					obj.iconToggle(dst, $(this).prevAll('.auth-icon:first'));
				}).on('selectstart', function(){
					// 屏蔽双击选中文字
					return false;
				});
			}
		},
		// 自动关闭 - 如果兄弟节点均没选中，递归取消上级元素选中状态，传入的是 .auth-status 节点，递归 .auth-status 上级节点
		_autoclose: function(obj) {
			var single = $(obj).parent().parent();
			var authStatus = single.parent().prev();

			if (!authStatus.hasClass('auth-status')) {
				return false;
			}
			// 仅一层
			if (single.find('div>.auth-status>input[type="checkbox"]:checked').length === 0) {
				authStatus.find('input[type="checkbox"]').prop('checked', false);
				this._autoclose(authStatus);
			}
		},
		// 以 icon 的维度，切换显示下级空间
		iconToggle: function(dst, iconobj){
			var origin = $(iconobj);
			var child = origin.parent().parent().find('.auth-child:first');
			if(origin.is('.active')){
				/*收起*/
				origin.removeClass('active').html(obj.closeIconContent);
				child.slideUp('fast');
			} else {
				/*展开*/
				origin.addClass('active').html(obj.openIconContent);
				child.slideDown('fast');
			}
			obj._triggerEvent(dst, 'deptChange');	
			return false;
		},
		// 递归创建格式
		renderAuth: function(tree, dept, opt){
			var inputname = opt.inputname;
			var layfilter = opt.layfilter;
			var openall = opt.openall;
			var str = '<div class="auth-single">';

			layui.each(tree, function(index, item){
				var hasChild = item.list ? 1 : 0;
				// 注意：递归调用时，this的环境会改变！
				var append = hasChild ? obj.renderAuth(item.list, dept+1, opt) : '';
				var openstatus = openall || (opt.openchecked && item.checked);

				// '+new Array(dept * 4).join('&nbsp;')+'
				str += '<div><div class="auth-status" style="display: flex;flex-direction: row;align-items: flex-end;"> '+
					(hasChild?'<i class="layui-icon auth-icon '+(openstatus?'active':'')+'" style="cursor:pointer;">'+(openstatus?obj.openIconContent:obj.closeIconContent)+'</i>':'<i class="layui-icon auth-leaf" style="opacity:0;">&#xe626;</i>')+
					(dept > 0 ? '<span>├─ </span>':'')+
					'<input type="checkbox" name="'+inputname+'" title="'+item.name+'" value="'+item.value+'" lay-skin="primary" lay-filter="'+layfilter+'" '+
					(item.checked?'checked="checked"':'')+'> </div>'+
					' <div class="auth-child" style="'+(openstatus ?'':'display:none;')+'padding-left:40px;"> '+append+'</div></div>'
			});
			str += '</div>';
			return str;
		},
		/**
		 * 将普通列表无限递归转换为树
		 * @param  {[type]} list       [普通的列表，必须包括 opt.primaryKey 指定的键和 opt.parentKey 指定的键]
		 * @param {[type]} opt [配置参数，支持 primaryKey(主键 默认id) parentKey(父级id对应键 默认pid) nameKey(节点标题对应的key 默认name) valueKey(节点值对应的key 默认id) checkedKey(节点是否选中的字段 默认checked，传入数组则判断主键是否在此数组中) startPid(第一层扫描的PID 默认0) currentDept(当前层 默认0) maxDept(最大递归层 默认100) childKey(递归完成后子节点对应键 默认list) deptPrefix(根据层级重复的前缀 默认'')]
		 * @return {[type]}            [description]
		 */
		listConvert: function(list, opt) {
			opt.primaryKey = opt.primaryKey ? opt.primaryKey : 'id';
			opt.parentKey = opt.parentKey ? opt.parentKey : 'pid';
			opt.startPid = opt.startPid ? opt.startPid : 0;
			opt.currentDept = opt.currentDept ? opt.currentDept : 0;
			opt.maxDept = opt.maxDept ? opt.maxDept : 100;
			opt.childKey = opt.childKey ? opt.childKey : 'list';
			opt.nameKey = opt.nameKey ? opt.nameKey : 'name';
			opt.valueKey = opt.valueKey ? opt.valueKey : 'id';
			return this._listToTree(list, opt.startPid, opt.currentDept, opt);
		},
		// 实际的递归函数，将会变化的参数抽取出来
		_listToTree: function(list, startPid, currentDept, opt) {
			if (opt.maxDept < currentDept) {
				return [];
			}
			var child = [];
			for (index in list) {
				// 筛查符合条件的数据（主键 = startPid）
				var item = list[index];
				if (typeof item[opt.parentKey] !== 'undefined' && item[opt.parentKey] === startPid) {
					// 满足条件则递归
					var nextChild = this._listToTree(list, item[opt.primaryKey], currentDept+1, opt);
					// 节点信息保存
					var node = {};
					if (nextChild.length > 0) {
						node[opt.childKey] = nextChild;
					}
					node['name'] = item[opt.nameKey];
					node['value'] = item[opt.valueKey];
					if (typeof opt.checkedKey === "string" || typeof opt.checkedKey === 'number') {
						node['checked'] = item[opt.checkedKey];
					} else if(typeof opt.checkedKey === 'object') {
						if ($.inArray(item[opt.valueKey], opt.checkedKey) != -1) {
							node['checked'] = true;
						} else {
							node['checked'] = false;
						}
					} else {
						node['checked'] = false;
					}
					child.push(node);
				}
			}
			return child;
		},
		/**
		 * 将树转为单选可用的 select，如果后台返回列表数据，可以先转换为 tree
		 * @param  {[type]} tree [description]
		 * @param  {[type]} opt  [description]
		 * @return {[type]}      [description]
		 */
		treeConvertSelect: function(tree, opt) {
			if (typeof tree.length !== 'number' || tree.length <= 0) {
				return [];
			}
			// 初始化层级
			opt.currentDept = opt.currentDept ? opt.currentDept : 0;
			// 子节点列表的Key
			opt.childKey = opt.childKey ? opt.childKey : 'list';
			// 有子节点的前缀
			opt.prefixHasChildStr = opt.prefixStr ? opt.prefixStr : '├─';
			// 没有子节点的前缀
			opt.prefixHasChildStr = opt.prefixStr ? opt.prefixStr : '|';
			// 树的深度影响的子节点数据
			opt.prefixDeptStr = opt.prefixDeptStr ? opt.prefixDeptStr : '　';

			return this._treeToSelect(list, opt.currentDept, opt);
		},
		// 实际处理递归的函数
		_treeToSelect: function(list, currentDept, opt) {
			var ansList = [];

			for (index in list) {
				var item = list[index];
				item['name'] = 
				ansList.push(item);
			}
		},
		// 自动调整宽度以解决 form.render()生成元素兼容性问题，如果用户手动调用 form.render() 之后也需要调用此方法
		autoWidth: function(dst) {
			$(dst).css({
				'whiteSpace': 'nowrap',
				'maxWidth' : '100%',
			});
			$(dst).find('.layui-form-checkbox').each(function(index, item){
				if ($(this).is(':hidden')) {
					// 比较奇葩的获取隐藏元素宽度的手法，请见谅
					$('body').append('<div id="layui-authtree-get-width">'+$(this).html()+'</div>');
					$width = $('#layui-authtree-get-width').find('span').width() + $('#layui-authtree-get-width').find('i').width() + 29;
					$('#layui-authtree-get-width').remove();
				} else {
					$width = $(this).find('span').width() + $(this).find('i').width() + 25;
				}
				$(this).width($width);
			});
		},
		// 触发自定义事件
		_triggerEvent: function(dst, events, other) {
			var tree = this.renderedTrees[dst];
			var origin = $(dst);
			if (tree) {
				var opt = tree.opt;
				var data = {
					opt: opt,
					dst: dst,
					othis: origin,
				};
				if (other && typeof other === 'object') {
					data = $.extend(data, other);
				}
				// 支持 dst 和 用户的配置的 layfilter 监听
				layui.event.call(origin, MOD_NANE, events+'('+dst+')', data);
				layui.event.call(origin, MOD_NANE, events+'('+opt.layfilter+')', data);
			} else {
				return false;
			}
		},
		// 动态获取最大深度
		getMaxDept: function(dst){
			var next = $(dst);
			var dept = 0;
			while(next.length && dept < 100000) {
				next = this._getNext(next);
				if (next.length) {
					dept++;
				} else {
					break;
				}
			}
			return dept;
		},
		// 全选
		checkAll: function(dst){
			var origin = $(dst);

			origin.find('input[type="checkbox"]:not(:checked)').prop('checked', true);
			form.render('checkbox');
			obj.autoWidth(dst);
			// 变动则存一下临时状态
			obj._saveNodeStatus(dst);
			obj._triggerEvent(dst, 'change');	
			obj._triggerEvent(dst, 'checkAll');	
		},
		// 全不选
		uncheckAll: function(dst){
			var origin = $(dst);
			origin.find('input[type="checkbox"]:checked').prop('checked', false);
			form.render('checkbox');
			obj.autoWidth(dst);
			// 变动则存一下临时状态
			obj._saveNodeStatus(dst);
			obj._triggerEvent(dst, 'change');
			obj._triggerEvent(dst, 'uncheckAll');	
		},
		// 显示整个树
		showAll: function(dst) {
			this.showDept(dst, this.getMaxDept(dst));
		},
		// 关闭整颗树
		closeAll: function(dst) {
			this.closeDept(dst, 1);
		},
		// 切换整颗树的显示/关闭
		toggleAll: function(dst) {
			if (this._shownDept(2)) {
				this.closeDept(dst);
			} else {
				this.showAll(dst);
			}
		},
		// 显示到第 dept 层
		showDept: function(dst, dept) {
			var next = $(dst);
			for(var i = 1; i < dept; i++) {
				next = this._getNext(next);
				if (next.length) {
					this._showSingle(next);
				} else {
					break;
				}
			}
			obj._triggerEvent(dst, 'deptChange', {dept: dept});
		},
		// 第 dept 层之后全部关闭
		closeDept: function(dst, dept) {
			var next = $(dst);
			for(var i = 0; i < dept; i++){
				next = this._getNext(next);
			}
			while(next.length) {
				this._closeSingle(next);
				next = this._getNext(next);
			}
			obj._triggerEvent(dst, 'deptChange', {dept: dept});
		},
		// 临时保存所有节点信息状态
		_saveNodeStatus: function(dst){
			var currentChecked = this.getChecked(dst);
			var currentNotChecked = this.getNotChecked(dst);
			// 保存新信息前，最新选择的信息
			this.lastCheckedNode[dst] = this._getLastChecked(dst, currentChecked, currentNotChecked);
			this.lastNotCheckedNode[dst] = this._getLastNotChecked(dst, currentChecked, currentNotChecked);
			this.checkedNode[dst] = currentChecked;
			this.notCheckedNode[dst] = currentNotChecked;

			// console.log('保存节点信息', this.checkedNode[dst], this.notCheckedNode[dst], this.lastCheckedNode[dst], this.lastNotCheckedNode[dst]);
		},
		// 判断某一层是否显示
		_shownDept: function(dst, dept) {
			var next = $(dst);
			for(var i = 0; i < dept; i++){
				next = this._getNext(next);
			}
			return !next.is(':hidden');
		},
		// 获取
		_getNext: function(dst) {
			return $(dst).find('.auth-single:first>div>.auth-child');
		},
		// 显示某层 single
		_showSingle: function(dst) {
			layui.each(dst, function(index, item) {
				var origin = $(item).find('.auth-single:first');
				var parentChild = origin.parent();
				var parentStatus = parentChild.prev();
				if (!parentStatus.find('.auth-icon').hasClass('active')) {
					parentChild.show();
					// 显示上级的 .auth-child节点，并修改.auth-status的折叠状态
					parentStatus.find('.auth-icon').addClass('active').html(obj.openIconContent);
				}
			});
		},
		// 关闭某层 single
		_closeSingle: function(dst) {
			var origin = $(dst).find('.auth-single:first');
			var parentChild = origin.parent();
			var parentStatus = parentChild.prev();
			if (parentStatus.find('.auth-icon').hasClass('active')) {
				parentChild.hide();
				// 显示上级的 .auth-child节点，并修改.auth-status的折叠状态
				parentStatus.find('.auth-icon').removeClass('active').html(obj.closeIconContent);
			}
		},
		// 获取选中叶子结点
		getLeaf: function(dst){
			var leafs = $(dst).find('.auth-leaf').parent().find('input[type="checkbox"]:checked');
			var data = [];
			leafs.each(function(index, item){
				// console.log(item);
				data.push(item.value);
			});
			// console.log(data);
			return data;
		},
		// 获取所有节点数据
		getAll: function(dst){
			var inputs = $(dst).find('input[type="checkbox"]');
			var data = [];
			inputs.each(function(index, item){
				data.push(item.value);
			});
			// console.log(data);
			return data;
		},
		// 获取最新选中（之前取消-现在选中）
		getLastChecked: function(dst) {
			return this.lastCheckedNode[dst] || [];
		},
		// (逻辑)最新选中（之前取消-现在选中）
		_getLastChecked: function(dst, currentChecked, currentNotChecked) {
			var lastCheckedNode = currentChecked;

			var data = [];
			for (i in lastCheckedNode) {
				if ($.inArray(lastCheckedNode[i], this.notCheckedNode[dst]) != -1) {
					data.push(lastCheckedNode[i]);
				}
			}
			return data;
		},
		// 获取所有选中的数据
		getChecked: function(dst){
			var inputs = $(dst).find('input[type="checkbox"]:checked');
			var data = [];
			inputs.each(function(index, item){
				data.push(item.value);
			});
			return data;
		},
		// 获取最新取消（之前取消-现在选中）
		getLastNotChecked: function(dst) {
			return this.lastNotCheckedNode[dst] || [];
		},
		// (逻辑)最新取消（之前选中-现在取消）
		_getLastNotChecked: function(dst, currentChecked, currentNotChecked) {
			var lastNotCheckedNode = currentNotChecked;

			var data = [];
			for (i in lastNotCheckedNode) {
				if ($.inArray(lastNotCheckedNode[i], this.checkedNode[dst]) != -1) {
					data.push(lastNotCheckedNode[i]);
				}
			}
			return data;
		},
		// 获取未选中数据
		getNotChecked: function(dst){
			var inputs = $(dst).find('input[type="checkbox"]:not(:checked)');
			var data = [];
			inputs.each(function(index, item){
				data.push(item.value);
			});
			// console.log(data);
			return data;
		}
	}
	exports('authtree', obj);
});