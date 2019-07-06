/**

 @Name：tablePlug 表格拓展插件
 @Author：岁月小偷
 @License：MIT
 @version 1.0.0

 */
layui.define(['table'], function (exports) {
  "use strict";

  var version = '2.0.0-preview1';
  var modelName = 'tablePlug'; // 插件名称，支持自己定义，但是不建议
  var pathTemp = layui.cache.modules[modelName] || ''; // 正常情况下不会出现未定义的情况
  var filePath = pathTemp.substr(0, pathTemp.lastIndexOf('/'));
  // 引入tablePlug.css
  layui.link(filePath + '/tablePlug.css?v' + version);
  // 引入图标文件
  layui.link(filePath + '/icon/iconfont.css?v' + version);

  var $ = layui.$
    , laytpl = layui.laytpl
    , laypage = layui.laypage
    , layer = layui.layer
    , form = layui.form
    , util = layui.util
    , table = layui.table
    , hint = layui.hint()
    , device = layui.device();

  if (!form.render.plugFlag) {
    // 只要未改造过的才需要改造一下
    // 保留一下原始的form.render
    var formRender = form.render;
    form.render = function (type, filter, jqObj) {
      var that = this;
      var retObj;
      if (jqObj) {
        layui.each(jqObj, function (index, elem) {
          elem = $(elem);
          var elemP = elem.parent();
          var formFlag = elemP.hasClass('layui-form');
          var filterTemp = elemP.attr('lay-filter');
          // mark一下当前的
          formFlag ? '' : elemP.addClass('layui-form');
          filterTemp ? '' : elemP.attr('lay-filter', 'tablePlug_form_filter_temp_' + new Date().getTime() + '_' + Math.floor(Math.random() * 100000));
          // 将焦点集中到要渲染的这个的容器上
          retObj = formRender.call(that, type, elemP.attr('lay-filter'));
          // 恢复现场
          formFlag ? '' : elemP.removeClass('layui-form');
          filterTemp ? '' : elemP.attr('lay-filter', null);
        });
      } else {
        retObj = formRender.call(that, type, filter);
      }
      return retObj;
    };
    form.render.plugFlag = true;
  }

  // 异步地将独立功能《优化layui的select的选项设置》引入
  layui.extend({optimizeSelectOption: '{/}' + filePath + '/optimizeSelectOption/optimizeSelectOption'}).use('optimizeSelectOption');

  var layuiVersion = '2.5.4' // 基于2.5.4开发的
    // 检测是否满足智能重载的条件 检测是否修改了源码将构造出还有thisTable透漏出来
    , checkSmartReloadCodition = (function () {
      if (layui.device().ie && parseInt(layui.device().ie) < 9) {
        console.warn('tablePlug插件暂时不支持ie9以下的ie浏览器，如果需要支持可自行调试，一般就是一些数组的方法ie8没有还有一个重要的就是window.parent这些支持不好，在getPosition的时候会死循环，如果有这方面相关的经验有处理方法请分享给俺，谢谢。')
      }
      if (table.thisTable && table.Class) {
        // console.info('欢迎使用tablePlug插件，使用过程中有任何问题或者有什么建议都可以到码云上新建issues', 'https://gitee.com/sun_zoro/layuiTablePlug');
        return true;
      } else {
        console.error('如果要使用该插件（tablePlug），参照readme.md的说明修改layui的table模块的代码，目前该组件是基于layui-V' + layuiVersion, 'https://gitee.com/sun_zoro/layuiTablePlug');
        return false;
      }
    })()
    , tablePlug = {
      version: version // tablePlug的版本后面提交的时候会更新也好知道使用的是不是同一个版本
    }
    , tableIns = {}
    , CHECK_TYPE_ADDITIONAL = 'additional'  // 新增的
    , CHECK_TYPE_REMOVED = 'removed'  // 删除的
    , CHECK_TYPE_ORIGINAL = 'original' // 原有的
    , CHECK_TYPE_DISABLED = 'disabled' // 不可选的
    , FIXED_SCROLL = 'layui-table-fixed-scroll'

    //解析自定义模板数据
    ,parseTempData = function(item3, content, tplData, text){
      var str = item3.templet ? function(){
        return typeof item3.templet === 'function'
          ? item3.templet(tplData)
          : laytpl($(item3.templet).html() || String(content)).render(tplData)
      }() : content;
      return text ? $('<div>'+ str +'</div>').text() : str;
    }

    , ELEM_VIEW = 'layui-table-view', ELEM_TOOL = '.layui-table-tool', ELEM_BOX = '.layui-table-box',
    ELEM_INIT = '.layui-table-init', ELEM_HEADER = '.layui-table-header', ELEM_BODY = '.layui-table-body',
    ELEM_MAIN = '.layui-table-main', ELEM_FIXED = '.layui-table-fixed', ELEM_FIXL = '.layui-table-fixed-l',
    ELEM_FIXR = '.layui-table-fixed-r', ELEM_TOTAL = '.layui-table-total', ELEM_PAGE = '.layui-table-page',
    ELEM_SORT = '.layui-table-sort', ELEM_EDIT = 'layui-table-edit', ELEM_HOVER = 'layui-table-hover'
    , NONE = 'layui-none'
    , HIDE = 'layui-hide'
    // , LOADING = 'layui-tablePlug-loading-p'
    , ELEM_CLICK = 'layui-table-click'
    , COLGROUP = 'colGroup' // 定义一个变量，方便后面如果table内部有变化可以对应的修改一下即可
    , tableSpacialColType = ['numbers', 'checkbox', 'radio'] // 表格的特殊类型字段
    , LayuiTableColFilter = [
      '<span class="layui-table-filter layui-inline">',
      '<span class="layui-tablePlug-icon layui-tablePlug-icon-filter"></span>',
      '</span>'
    ]
    , filterLayerIndex // 保存打开字段过滤的layer的index
    , getIns = function (id) {
      return table.thisTable.that[id];
    }

    , tableCheck = function () {
      var checked = {};
      return {
        // 检验是否可用，是否初始化过
        check: function (tableId) {
          return !!checked[tableId];
        },
        reset: function (tableId) {
          if (!checked[tableId]) {
            checked[tableId] = {};
            checked[tableId][CHECK_TYPE_ORIGINAL] = [];
            checked[tableId][CHECK_TYPE_ADDITIONAL] = [];
            checked[tableId][CHECK_TYPE_REMOVED] = [];
            checked[tableId][CHECK_TYPE_DISABLED] = [];
          } else {
            this.set(tableId, CHECK_TYPE_ADDITIONAL, []);    // 新增的
            this.set(tableId, CHECK_TYPE_REMOVED, []);       // 删除的
          }
        },
        init: function (tableId, data, primaryKey) {
          this.reset(tableId);
          var ids = [];
          if (data && data.length && typeof data[0] === 'object') {
            // 如果data是对象数组
            ids = addCacheData(tableId, data, primaryKey);
          } else {
            ids = data;
          }
          this.set(tableId, CHECK_TYPE_ORIGINAL, ids);
        },
        // 设置部分记录不可选
        disabled: function (tableId, data) {
          if (!checked[tableId]) {
            this.reset(tableId);
          }
          this.set(tableId, CHECK_TYPE_DISABLED, data);
        },
        checkDisabled: function (tableId, value) {
          return this.get(tableId, CHECK_TYPE_DISABLED).indexOf(value) !== -1;
        },
        // 获得当前选中的，不区分状态
        getChecked: function (tableId) {
          var delArr = this.get(tableId, CHECK_TYPE_REMOVED);

          var retTemp = this.get(tableId, CHECK_TYPE_ORIGINAL).concat(this.get(tableId, CHECK_TYPE_ADDITIONAL));
          var ret = [];
          layui.each(retTemp, function (index, data) {
            if (delArr.indexOf(data) === -1 && ret.indexOf(data) === -1) {
              ret.push(data);
            }
          });
          return ret;
        },
        get: function (tableId, type) {
          if (type === CHECK_TYPE_ADDITIONAL
            || type === CHECK_TYPE_REMOVED
            || type === CHECK_TYPE_ORIGINAL
            || type === CHECK_TYPE_DISABLED) {
            return checked[tableId] ? (checked[tableId][type] || []) : [];
          } else {
            return checked[tableId];
          }
        },
        set: function (tableId, type, data) {
          if (type !== CHECK_TYPE_ORIGINAL
            && type !== CHECK_TYPE_ADDITIONAL
            && type !== CHECK_TYPE_REMOVED
            && type !== CHECK_TYPE_DISABLED) {
            return;
          }
          checked[tableId][type] = (!data || !isArray(data)) ? [] : data;
        },
        update: function (tableId, id, checkedStatus, isRadio) {
          var _original = checked[tableId][CHECK_TYPE_ORIGINAL];
          var _additional = checked[tableId][CHECK_TYPE_ADDITIONAL];
          var _removed = checked[tableId][CHECK_TYPE_REMOVED];
          if (checkedStatus) {
            // 单选的时候不管什么时候都将新增的记录给去掉
            isRadio && _additional.splice(0, 1);
            // 勾选
            if (_original.indexOf(id) === -1) {
              // 不在原来的集合中
              if (_additional.indexOf(id) === -1) {
                _additional.push(id);
              }
            } else {
              // 在原来的集合中，意味着之前有去掉勾选的操作
              if (!isRadio && _removed.indexOf(id) !== -1) {
                _removed.splice(_removed.indexOf(id), 1);
              }
            }
          } else {
            // 取消勾选
            if (_original.indexOf(id) === -1) {
              // 不在原来的集合中，意味着以前曾经添加过
              if (_additional.indexOf(id) !== -1) {
                _additional.splice(_additional.indexOf(id), 1);
              }
            } else {
              // 在原来的集合中
              if (_removed.indexOf(id) === -1) {
                _removed.push(id);
              }
            }
          }
        }
      }
    }()

    , isArray = function (obj) {
      // 判断一个变量是不是数组
      return Object.prototype.toString.call(obj) === '[object Array]';
    }

    // 针对表格中是否选中的数据处理
    , dataRenderChecked = function (data, tableId, config) {
      if (!data || !tableId) {
        return;
      }
      config = config || getConfig(tableId);
      if (!config || !config.checkStatus) {
        return;
      }
      var nodeSelected = tableCheck.getChecked(tableId);
      for (var i = 0; i < data.length; i++) {
        data[i][table.config.checkName] = nodeSelected.indexOf(data[i][getPrimaryKey(config)]) !== -1;
      }
    }

    // 同步表格不可点击的checkbox
    , disabledCheck = function (tableId, syncConfig) {
      // tableId 这个参数有可能传入table的id也可以直接传入table的实例
      // syncConfig是否需要同步config
      // var config = getConfig(tableId);
      var config;
      if (typeof tableId === 'string') {
        config = getConfig(tableId);
      } else {
        config = tableId.config;
        tableId = config ? config.id : '';
      }

      if (!config) {
        return;
      }
      var tableView = config.elem.next();

      if (syncConfig) {
        config.checkDisabled = config.checkDisabled || {};
        config.checkDisabled.enabled = config.checkDisabled.enabled || true;
        config.checkDisabled.data = tableCheck.get(tableId, CHECK_TYPE_DISABLED) || [];
      }
      if (config.checkDisabled && config.checkDisabled.enabled) {
        layui.each(table.cache[tableId], function (index, data) {
          tableView.find('.layui-table-body')
            .find('tr[data-index="' + index + '"]')
            .find('input[name="layTableCheckbox"],[lay-type="layTableRadio"]')
            .prop('disabled', tableCheck.checkDisabled(tableId, data[getPrimaryKey(config)]));
        });
      } else {
        tableCheck.set(tableId, CHECK_TYPE_DISABLED, []);
      }

      tableView.find('input[lay-filter="layTableAllChoose"]').prop('checked', table.checkStatus(tableId).isAll);
      form.render('checkbox', tableView.attr('lay-filter'));
      form.render('radio', tableView.attr('lay-filter'));
    };

  // 对table的全局config进行深拷贝
  tablePlug.set = function (config) {
    $.extend(true, table.config, config || {});
  };

  // 为啥要自己定义一个set去更新table.config而不用table.set？
  // 因为table.set实际是非深拷贝，然后我这里期待的是一个可以根据需要后面根据开发者需要去丰富pageLanguageText的内容的而不是set的时候需要把plug里面写的初始的也全部写上
  tablePlug.set({
    pageLanguageText: {
      // 自定义table的page组件中的多语言支持，实际这个完全可以自己定义，想要显示的文字，但是建议实用为主，真的需要再去定义
      en: {
        jumpTo: 'jump to', // 到第
        page: 'page', // 页
        go: 'go', // 确定
        total: 'total', // 共
        unit: '', // 条（单位，一般也可以不填）
        optionText: 'limit each page' // 条/页
      }
      // 定义中文简写的, (如果需要的话，建议不改，按照原来的就行)
      // 'zh-CN': {
      //
      // }
      // 比如定义中文繁体
      // 'zh-TW': {
      //
      // }
    }
  });

  // 获得某个节点的位置 offsetTop: 是否获得相对top window的位移
  function getPosition(elem, _window, offsetTop) {
    _window = _window || window;
    elem = elem.length ? elem.get(0) : elem;
    var offsetTemp = {};
    if (offsetTop && _window.top !== _window.self) {
      var frameElem = _window.frames.frameElement;
      offsetTemp = getPosition(frameElem, _window.parent, offsetTop);
    }
    var offset = elem.getBoundingClientRect();

    return {
      top: offset.top + (offsetTemp.top || 0),
      left: offset.left + (offsetTemp.left || 0)
    };
  }

  /* 2.5.4已经换成一个会动的加载图标了，所以这个逻辑可以去掉 目前它的位置是在body表格中间的还可以接受暂时不处理 */
  // 修改原始table的loading的逻辑
  // var loading = table.Class.prototype.loading;
  // table.Class.prototype.loading = function (hide) {
  //   var that = this;
  //   loading.call(that, hide);
  //   if (!hide && that.layInit) {
  //     that.layInit.remove();
  //     // 添加一个动画
  //     that.layInit.addClass('layui-anim layui-anim-rotate layui-anim-loop');
  //     if (!that.layMain.height()) {
  //       // 如果当前没有内容，添加一个空的div让它有显示的地方
  //       that.layBox.append($('<div class="' + LOADING + '" style="height: 56px;"></div>'));
  //     }
  //     var offsetHeight = 0;
  //     if (that.layMain.height() - that.layMain.prop('clientHeight') > 0) {
  //       // 如果出现滚动条，要减去滚动条的宽度
  //       offsetHeight = that.getScrollWidth();
  //     }
  //     var thHeightTemp = that.elem.hasClass('vertical') ? 0 : that.layHeader.height();
  //     that.layInit.height(that.layBox.height() - thHeightTemp - offsetHeight).css('marginTop', thHeightTemp + 'px');
  //     that.layBox.append(that.layInit);
  //   }
  // };

  // 目前新增了一个errorView处理异常或者没有数据的时候的显示问题，对应的initTable中的一些逻辑是否还有必要待定 todo
  // 初始化表格的内容
  table.Class.prototype.initTable = function () {
    var that = this;
    var options = that.config;

    that.layFixed.find('tbody').html('');
    that.layFixed.addClass(HIDE);
    that.layTotal.addClass(HIDE);
    that.layPage.addClass(HIDE);

    that.layMain.find('tbody').html('');
    that.layMain.find('.' + NONE).remove();

    that.layHeader.find('input[name="layTableCheckbox"]').prop('checked', false);
    that.renderForm('checkbox');
  };

  // 内部使用，如果没有传reversal, 默认就是按照当前的状态取反更新一下，如果有传入，则以传入的为准
  var reverseTable = function (tableId, reversal) {
    if (!tableId) {
      layui.each(table.thisTable.that, function (key, obj) {
        reverseTable(key, reversal);
      });
    } else {
      var insTemp = getIns(tableId);
      var config = insTemp.config;

      if (typeof reversal !== 'boolean') {
        // 如果不是true/false; 默认就是将当前的状态给重新渲染一下
        reversal = !config.reversal;
      }

      // 设置状态
      config.reversal = reversal;
      // 调用反转方法
      insTemp.reverse();
      // 处理360急速模式下出现的异常问题(临时处理方式)
      insTemp.resize();
    }
  };

  var cacheData = {};
  var addCacheData = function (tableId, data, primaryKey) {
    var options = typeof tableId === 'string' ? getConfig(tableId) : tableId;
    // 如果是url模式并且设置了复选框跨页状态存储就把当前的data给缓存起来
    primaryKey = primaryKey || getPrimaryKey(options);
    if (options.checkStatus && primaryKey) {
      cacheData[options.id] = cacheData[options.id] || {};
      layui.each(data || table.cache[options.id] || [], function (index, data) {
        cacheData[options.id][data[primaryKey]] = data;
      });
    }
    return (data || table.cache[options.id] || []).map(function (value) {
      return value[primaryKey]
    });
  };

  // 渲染完成之后的回调
  var renderDone = function (scroll) {
    var that = this;
    var options = that.config;
    scroll && (that.layBody.scrollTop(0), that.layBody.scrollLeft(0));
    // 同步不可选的状态
    disabledCheck(that);
    // 添加筛选的功能
    addFieldFilter.call(that);

    // if (!that.layMain.find('.' + NONE).length) {
    //   that.layTotal.removeClass(HIDE);
    //   that.layFixLeft.removeClass(HIDE);
    // }

    // 初始化临时数据区
    layui.each(that.tempData, function (index, data) {
      that.addTemp(index + 1, data, null, true);
    });

    // 同步反转状态
    // reverseTable(that.key, !!that.config.reversal);
    options.reversal === true && that.reverse();

    // 动态给checkbox添加lay-filter方便加form的监听处理
    that.layBody.find('input[type="checkbox"][name="layTableCheckbox"]').attr('lay-filter', 'layTableCheckbox');
    // 更新统计行的数据
    // renderTotal(that.config.id);
    renderTotal(that);

    // 打滚动条补丁
    // that.scrollPatch();

    // 如果是url模式并且设置了复选框跨页状态存储就把当前的data给缓存起来
    // var primaryKey = getPrimaryKey(options);
    // if (options.checkStatus && primaryKey) {
    //   cacheData[options.id] = cacheData[options.id] || {};
    //   layui.each(table.cache[options.id], function (index, data) {
    //     cacheData[options.id][data[primaryKey]] = data;
    //   });
    // }
    addCacheData(options);
  };

  /*//获得数据 2.4.5
  table.Class.prototype.pullData = function (curr, refresh) {
    var that = this
      , options = that.config
      , request = options.request
      , response = options.response
      , sort = function () {
      if (typeof options.initSort === 'object') {
        that.sort(options.initSort.field, options.initSort.type);
      }
    };

    that.startTime = new Date().getTime(); //渲染开始时间

    if (options.url) { //Ajax请求
      // 初始化宽度，避免一开始拧成一团
      refresh || that.setColsWidth();
      that.loading();
      var params = {};
      params[request.pageName] = curr;
      params[request.limitName] = options.limit;

      //参数
      var data = $.extend(params, options.where);
      // 请求前对ajax的data进行修改的回调
      if (typeof options.renderRequest === 'function') {
        data = options.renderRequest(data);
      }
      if (options.contentType && options.contentType.indexOf("application/json") == 0) { //提交 json 格式
        data = JSON.stringify(data);
      }

      $.ajax({
        type: options.method || 'get'
        , url: options.url
        , contentType: options.contentType
        , data: data
        , dataType: 'json'
        , headers: options.headers || {}
        , success: function (res) {
          //如果有数据解析的回调，则获得其返回的数据
          if (typeof options.parseData === 'function') {
            res = options.parseData(res) || res;
          }
          //检查数据格式是否符合规范
          if (res[response.statusName] != response.statusCode) {
            that.renderForm();
            // #### 源码修改 #### 直接在源头处理掉一些不太合理的地方避免智能重载后面还需要打补丁
            that.initTable();
            that.layMain.append('<div class="' + NONE + '">' + (
              res[response.msgName] ||
              ('返回的数据不符合规范，正确的成功状态码 (' + response.statusName + ') 应为：' + response.statusCode)
            ) + '</div>');
          } else {
            that.renderData(res, curr, res[response.countName], false, refresh && (options.page ? options.page.count === res[response.countName] : true)), sort();
            options.time = (new Date().getTime() - that.startTime) + ' ms'; //耗时（接口请求+视图渲染）
          }
          refresh ? that.loading(true) : that.setColsWidth();
          typeof options.done === 'function' && options.done(res, curr, res[response.countName]);
        }
        , error: function (e, m) {
          // #### 源码修改 #### 直接在源头处理掉一些不太合理的地方避免智能重载后面还需要打补丁
          that.initTable();
          // that.layMain.html('<div class="'+ NONE +'">数据接口请求异常：'+ m +'</div>');
          that.layMain.append('<div class="' + NONE + '">数据接口请求异常：' + m + '</div>');
          that.renderForm();
          refresh ? that.loading(true) : that.setColsWidth();
        }
      });
    } else if (options.data && options.data.constructor === Array) { //已知数据
      var res = {}
        , startLimit = curr * options.limit - options.limit

      res[response.dataName] = options.data.concat().splice(startLimit, options.limit);
      res[response.countName] = options.data.length;

      that.initTable();
      that.renderData(res, curr, options.data.length, false, refresh && (options.page ? options.page.count === options.data.length : true)), sort();
      refresh ? that.loading(true) : that.setColsWidth();
      typeof options.done === 'function' && options.done(res, curr, res[response.countName]);
    }
  };*/

  //异常提示
  table.Class.prototype.errorView = function(html){
    var that = this
      ,elemNone = that.layMain.find('.'+ NONE)
      ,layNone = $('<div class="'+ NONE +'">'+ (html || 'Error') +'</div>');

    if(elemNone[0]){
      that.layNone.remove();
      elemNone.remove();
    }

    that.layFixed.addClass(HIDE).find('tbody').html('');
    that.layMain.find('tbody').html('');

    that.layMain.append(that.layNone = layNone);
    // 补充异常情况下对page和total的内容处理
    that.layPage && that.layPage.addClass(HIDE).find('>div').html('');
    that.layTotal && that.layTotal.addClass(HIDE).find('tbody').html('');

    table.cache[that.key] = []; //格式化缓存数据
  };

  //获得数据
  table.Class.prototype.pullData = function(curr, refresh){
    var that = this
      ,options = that.config
      ,request = options.request
      ,response = options.response
      ,sort = function(){
      if(typeof options.initSort === 'object'){
        that.sort(options.initSort.field, options.initSort.type);
      }
    };

    that.startTime = new Date().getTime(); //渲染开始时间

    if(options.url){ //Ajax请求
      var params = {};
      params[request.pageName] = curr;
      params[request.limitName] = options.limit;

      //参数
      var data = $.extend(params, options.where);
      // 请求前对ajax的data进行修改的回调
      if (typeof options.renderRequest === 'function') {
        data = options.renderRequest(data);
      }
      if(options.contentType && options.contentType.indexOf("application/json") == 0){ //提交 json 格式
        data = JSON.stringify(data);
      }

      that.loading();

      $.ajax({
        type: options.method || 'get'
        ,url: options.url
        ,contentType: options.contentType
        ,data: data
        ,dataType: 'json'
        ,headers: options.headers || {}
        ,success: function(res){
          //如果有数据解析的回调，则获得其返回的数据
          if(typeof options.parseData === 'function'){
            res = options.parseData(res) || res;
          }
          //检查数据格式是否符合规范
          if(res[response.statusName] != response.statusCode){
            that.renderForm();
            that.errorView(
              res[response.msgName] ||
              ('返回的数据不符合规范，正确的成功状态码应为："'+ response.statusName +'": '+ response.statusCode)
            );
          } else {
            that.renderData(res, curr, res[response.countName], false, refresh && (options.page ? options.page.count === res[response.countName] : true)), sort();
            options.time = (new Date().getTime() - that.startTime) + ' ms'; //耗时（接口请求+视图渲染）
          }
          refresh ? that.loading(true) : that.setColsWidth();
          typeof options.done === 'function' && options.done(res, curr, res[response.countName]);
        }
        ,error: function(e, m){
          that.errorView('数据接口请求异常：'+ m);

          that.renderForm();
          refresh ? that.loading(true) : that.setColsWidth();
        }
      });

    } else if(options.data && options.data.constructor === Array){ //已知数据
      var res = {}
        ,startLimit = curr*options.limit - options.limit

      res[response.dataName] = options.data.concat().splice(startLimit, options.limit);
      res[response.countName] = options.data.length;

      that.renderData(res, curr, res[response.countName], false, refresh && (options.page ? options.page.count === options.data.length : true)), sort();
      refresh ? that.loading(true) : that.setColsWidth();
      typeof options.done === 'function' && options.done(res, curr, res[response.countName]);
    }
  };

  /*// 数据渲染 2.4.5
  table.Class.prototype.renderData = function (res, curr, count, sort, refresh) {
    var that = this
      , options = that.config
      , data = res[options.response.dataName] || []
      , trs = []
      , trs_fixed = []
      , trs_fixed_r = []

      //渲染视图
      , render = function () { //后续性能提升的重点
        if (!sort && that.sortKey) {
          return that.sort(that.sortKey.field, that.sortKey.sort, true);
        }

        var thisCheckedRowIndex;
        layui.each(data, function (i1, item1) {
          var tds = [], tds_fixed = [], tds_fixed_r = []
            , numbers = i1 + options.limit * (curr - 1) + 1; //序号

          if (item1.length === 0) return;
          if (!sort) {
            item1[table.config.indexName] = i1;
          }

          that.eachCols(function (i3, item3) {
            var field = item3.field || i3
              , key = options.index + '-' + item3.key
              , content = item1[field];

            if (content === undefined || content === null) content = '';
            if (item3.colGroup) return;

            //td内容
            var td = ['<td data-field="' + field + '" data-key="' + key + '" ' + function () { //追加各种属性
              var attr = [];
              if (item3.edit) attr.push('data-edit="' + item3.edit + '"'); //是否允许单元格编辑
              if (item3.align) attr.push('align="' + item3.align + '"'); //对齐方式
              if (item3.templet) attr.push('data-content="' + content + '"'); //自定义模板
              if (item3.toolbar) attr.push('data-off="true"'); //行工具列关闭单元格事件
              if (item3.event) attr.push('lay-event="' + item3.event + '"'); //自定义事件
              if (item3.style) attr.push('style="' + item3.style + '"'); //自定义样式
              if (item3.minWidth) attr.push('data-minwidth="' + item3.minWidth + '"'); //单元格最小宽度
              return attr.join(' ');
            }() + ' class="' + function () { //追加样式
              var classNames = [];
              if (item3.hide) classNames.push(HIDE); //插入隐藏列样式
              if (!item3.field) classNames.push('layui-table-col-special'); //插入特殊列样式
              return classNames.join(' ');
            }() + '">'
              , '<div class="layui-table-cell laytable-cell-' + function () { //返回对应的CSS类标识
                return item3.type === 'normal' ? key
                  : (key + ' laytable-cell-' + item3.type);
              }() + '">' + function () {
                var tplData = $.extend(true, {
                  LAY_INDEX: numbers
                }, item1)
                  , checkName = table.config.checkName;

                //渲染不同风格的列
                switch (item3.type) {
                  case 'checkbox':
                    return '<input type="checkbox" name="layTableCheckbox" lay-skin="primary" ' + function () {
                      //如果是全选
                      if (item3[checkName]) {
                        item1[checkName] = item3[checkName];
                        return item3[checkName] ? 'checked' : '';
                      }
                      return tplData[checkName] ? 'checked' : '';
                    }() + '>';
                    break;
                  case 'radio':
                    if (tplData[checkName]) {
                      thisCheckedRowIndex = i1;
                    }
                    return '<input type="radio" name="layTableRadio_' + options.index + '" '
                      + (tplData[checkName] ? 'checked' : '') + ' lay-type="layTableRadio">';
                    break;
                  case 'numbers':
                    return numbers;
                    break;
                }
                ;

                //解析工具列模板
                if (item3.toolbar) {
                  return laytpl($(item3.toolbar).html() || '').render(tplData);
                }
                return item3.templet ? function () {
                  return typeof item3.templet === 'function'
                    ? item3.templet(tplData)
                    : laytpl($(item3.templet).html() || String(content)).render(tplData)
                }() : content;
              }()
              , '</div></td>'].join('');

            tds.push(td);
            if (item3.fixed && item3.fixed !== 'right') tds_fixed.push(td);
            if (item3.fixed === 'right') tds_fixed_r.push(td);
          });

          trs.push('<tr data-index="' + i1 + '">' + tds.join('') + '</tr>');
          trs_fixed.push('<tr data-index="' + i1 + '">' + tds_fixed.join('') + '</tr>');
          trs_fixed_r.push('<tr data-index="' + i1 + '">' + tds_fixed_r.join('') + '</tr>');
        });

        refresh || that.layBody.scrollTop(0);
        // 如果没有数据才需要删除NONE
        !data.length || that.layMain.find('.' + NONE).remove();
        that.layMain.find('tbody').html(trs.join(''));
        that.layFixLeft.find('tbody').html(trs_fixed.join(''));
        that.layFixRight.find('tbody').html(trs_fixed_r.join(''));

        that.renderForm();
        typeof thisCheckedRowIndex === 'number' && that.setThisRowChecked(thisCheckedRowIndex);
        that.syncCheckAll();

        //滚动条补丁
        that.haveInit ? that.scrollPatch() : setTimeout(function () {
          that.scrollPatch();
        }, 50);
        that.haveInit = true;

        layer.close(that.tipsIndex);

        //同步表头父列的相关值
        options.HAS_SET_COLS_PATCH || that.setColsPatch();
        options.HAS_SET_COLS_PATCH = true;
      };

    // 只有在之前出现异常后面才需要先初始化table的内容
    that.layMain.find('.' + NONE).length && that.initTable();

    that.key = options.id || options.index;

    // 初始化选中的数据
    dataRenderChecked(data, options.id, options);

    var dataTemp = table.getTemp(that.key);
    // 存储临时数据
    that.tempData = dataTemp.data;

    table.cache[that.key] = data; //记录数据

    //显示隐藏分页栏
    that.layPage[(count == 0 || (data.length === 0 && curr == 1)) ? 'addClass' : 'removeClass'](HIDE);

    if (data.length === 0) {
      that.renderForm();
      // #### 源码修改 01 #### 处理没有数据的时候fixed模块被删除只能重载没办法重复利用的问题
      // that.layFixed.remove(); 智能reload的话不应该直接remove掉，直接hide掉就可以了
      // that.layFixed.addClass(HIDE);
      that.initTable();
      // that.layMain.find('tbody').html('');
      // that.layMain.find('.' + NONE).remove();
      return that.layMain.append('<div class="' + NONE + '">' + options.text.none + '</div>'), renderDone.call(that, true);
    }

    //排序
    if (sort) {
      return render(), renderDone.call(that);
    }

    render(); //渲染数据

    if (refresh) {
      return renderDone.call(that);
    }
    that.renderTotal(data); //数据合计

    //同步分页状态
    if (options.page) {
      options.page = $.extend({
        elem: 'layui-table-page' + options.index
        , count: count
        , limit: options.limit
        , limits: options.limits || [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , groups: 3
        , layout: ['prev', 'page', 'next', 'skip', 'count', 'limit']
        , prev: '<i class="layui-icon">&#xe603;</i>'
        , next: '<i class="layui-icon">&#xe602;</i>'
        , jump: function (obj, first) {
          if (!first) {
            //分页本身并非需要做以下更新，下面参数的同步，主要是因为其它处理统一用到了它们
            //而并非用的是 options.page 中的参数（以确保分页未开启的情况仍能正常使用）
            that.page = obj.curr; //更新页码
            options.limit = obj.limit; //更新每页条数
            that.loading();
            that.pullData(obj.curr);
          }
          // #### 源码修改02 #### 支持多语言的page
          if (that.config.pageLanguage && !(that.config.pageLanguage === true)) {
            var pageLanguageText;
            if (typeof that.config.pageLanguage === 'string') {
              if (!table.config.pageLanguageText[that.config.pageLanguage]) {
                console.log('找不到' + that.config.pageLanguage + '对应的语言文本定义');
                return;
              }
              pageLanguageText = table.config.pageLanguageText[that.config.pageLanguage];
            } else if (typeof that.config.pageLanguage === 'object') {
              var lanTemp = that.config.pageLanguage.lan;
              if (!lanTemp) {
                return;
              }
              pageLanguageText = $.extend({}, table.config.pageLanguageText[lanTemp], that.config.pageLanguage.text || {});
            } else {
              return;
            }

            if (!pageLanguageText) {
              return;
            }

            // 处理page支持en
            var pageElem = that.layPage.find('>div');
            pageElem.addClass(HIDE);
            var skipElem = pageElem.find('.layui-laypage-skip');
            var skipInput = skipElem.find('input');
            var skipBtn = skipElem.find('button');
            skipElem.html(pageLanguageText['jumpTo'] || 'jump to');
            skipInput.appendTo(skipElem);
            skipElem.append(pageLanguageText['page'] || 'page');
            skipBtn.html(pageLanguageText['go'] || 'go').appendTo(skipElem);

            var countElem = pageElem.find('.layui-laypage-count');
            var countText = countElem.text();
            countElem.html((pageLanguageText['total'] || 'total') + ' ' + countText.split(' ')[1] + (pageLanguageText['unit'] ? ' ' + pageLanguageText['unit'] : ''));

            var limitsElem = pageElem.find('.layui-laypage-limits');
            layui.each(limitsElem.find('option'), function (index, optionElem) {
              optionElem = $(optionElem);
              var textTemp = optionElem.text();
              optionElem.html(textTemp.split(' ')[0] + ' ' + (pageLanguageText['optionText'] || 'limit each page'));
            });
            pageElem.removeClass(HIDE);
          }
        }
      }, options.page);
      options.page.count = count; //更新总条数
      laypage.render(options.page);
    }

    // 渲染完毕做一些处理
    renderDone.call(that, true);
  };*/

  //数据渲染
  table.Class.prototype.renderData = function(res, curr, count, sort, refresh){
    var that = this
      ,options = that.config
      ,data = res[options.response.dataName] || []
      ,trs = []
      ,trs_fixed = []
      ,trs_fixed_r = []

      //渲染视图
      ,render = function(){ //后续性能提升的重点
        var thisCheckedRowIndex;
        if(!sort && that.sortKey){
          return that.sort(that.sortKey.field, that.sortKey.sort, true);
        }
        layui.each(data, function(i1, item1){
          var tds = [], tds_fixed = [], tds_fixed_r = []
            ,numbers = i1 + options.limit*(curr - 1) + 1; //序号

          if(item1.length === 0) return;
          if(!sort){
            item1[table.config.indexName] = i1;
          }

          that.eachCols(function(i3, item3){
            var field = item3.field || i3
              ,key = options.index + '-' + item3.key
              ,content = item1[field];

            if(content === undefined || content === null) content = '';
            if(item3.colGroup) return;

            //td内容
            var td = ['<td data-field="'+ field +'" data-key="'+ key +'" '+ function(){ //追加各种属性
              var attr = [];
              if(item3.edit) attr.push('data-edit="'+ item3.edit +'"'); //是否允许单元格编辑
              if(item3.align) attr.push('align="'+ item3.align +'"'); //对齐方式
              if(item3.templet) attr.push('data-content="'+ content +'"'); //自定义模板
              if(item3.toolbar) attr.push('data-off="true"'); //行工具列关闭单元格事件
              if(item3.event) attr.push('lay-event="'+ item3.event +'"'); //自定义事件
              if(item3.style) attr.push('style="'+ item3.style +'"'); //自定义样式
              if(item3.minWidth) attr.push('data-minwidth="'+ item3.minWidth +'"'); //单元格最小宽度
              return attr.join(' ');
            }() +' class="'+ function(){ //追加样式
              var classNames = [];
              if(item3.hide) classNames.push(HIDE); //插入隐藏列样式
              if(!item3.field) classNames.push('layui-table-col-special'); //插入特殊列样式
              return classNames.join(' ');
            }() +'">'
              ,'<div class="layui-table-cell laytable-cell-'+ function(){ //返回对应的CSS类标识
                return item3.type === 'normal' ? key
                  : (key + ' laytable-cell-' + item3.type);
              }() +'">' + function(){
                var tplData = $.extend(true, {
                  LAY_INDEX: numbers
                }, item1)
                  ,checkName = table.config.checkName;

                //渲染不同风格的列
                switch(item3.type){
                  case 'checkbox':
                    return '<input type="checkbox" name="layTableCheckbox" lay-skin="primary" '+ function(){
                      //如果是全选
                      if(item3[checkName]){
                        item1[checkName] = item3[checkName];
                        return item3[checkName] ? 'checked' : '';
                      }
                      return tplData[checkName] ? 'checked' : '';
                    }() +'>';
                    break;
                  case 'radio':
                    if(tplData[checkName]){
                      thisCheckedRowIndex = i1;
                    }
                    return '<input type="radio" name="layTableRadio_'+ options.index +'" '
                      + (tplData[checkName] ? 'checked' : '') +' lay-type="layTableRadio">';
                    break;
                  case 'numbers':
                    return numbers;
                    break;
                };

                //解析工具列模板
                if(item3.toolbar){
                  return laytpl($(item3.toolbar).html()||'').render(tplData);
                }
                return parseTempData(item3, content, tplData);
              }()
              ,'</div></td>'].join('');

            tds.push(td);
            if(item3.fixed && item3.fixed !== 'right') tds_fixed.push(td);
            if(item3.fixed === 'right') tds_fixed_r.push(td);
          });

          trs.push('<tr data-index="'+ i1 +'">'+ tds.join('') + '</tr>');
          trs_fixed.push('<tr data-index="'+ i1 +'">'+ tds_fixed.join('') + '</tr>');
          trs_fixed_r.push('<tr data-index="'+ i1 +'">'+ tds_fixed_r.join('') + '</tr>');
        });

        refresh || that.layBody.scrollTop(0);
        that.layMain.find('.'+ NONE).remove();
        that.layMain.find('tbody').html(trs.join(''));
        that.layFixLeft.find('tbody').html(trs_fixed.join(''));
        that.layFixRight.find('tbody').html(trs_fixed_r.join(''));

        that.renderForm();
        typeof thisCheckedRowIndex === 'number' && that.setThisRowChecked(thisCheckedRowIndex);
        that.syncCheckAll();

        //滚动条补丁
        that.haveInit ? that.scrollPatch() : setTimeout(function(){
          that.scrollPatch();
        }, 50);
        that.haveInit = true;

        layer.close(that.tipsIndex);

        //同步表头父列的相关值
        options.HAS_SET_COLS_PATCH || that.setColsPatch();
        options.HAS_SET_COLS_PATCH = true;
      };

    // 初始化选中的数据
    dataRenderChecked(data, options.id, options);

    var dataTemp = table.getTemp(that.key);
    // 存储临时数据
    that.tempData = dataTemp.data;

    table.cache[that.key] = data; //记录数据

    //显示隐藏分页栏
    that.layPage[(count == 0 || (data.length === 0 && curr == 1)) ? 'addClass' : 'removeClass'](HIDE);

    if(data.length === 0){
      that.renderForm();
      return that.errorView(options.text.none), renderDone.call(that, true);
    } else {
      that.layFixed.removeClass(HIDE);
    }

    //排序
    if(sort){
      return render(), renderDone.call(that);
    }

    render(); //渲染数据
    if (refresh) {
      return renderDone.call(that);
    }
    that.renderTotal(data); //数据合计
    that.layTotal && that.layTotal.removeClass(HIDE);

    //同步分页状态
    if(options.page){
      options.page = $.extend({
        elem: 'layui-table-page' + options.index
        ,count: count
        ,limit: options.limit
        ,limits: options.limits || [10,20,30,40,50,60,70,80,90]
        ,groups: 3
        ,layout: ['prev', 'page', 'next', 'skip', 'count', 'limit']
        ,prev: '<i class="layui-icon">&#xe603;</i>'
        ,next: '<i class="layui-icon">&#xe602;</i>'
        ,jump: function(obj, first){
          if(!first){
            //分页本身并非需要做以下更新，下面参数的同步，主要是因为其它处理统一用到了它们
            //而并非用的是 options.page 中的参数（以确保分页未开启的情况仍能正常使用）
            that.page = obj.curr; //更新页码
            options.limit = obj.limit; //更新每页条数

            that.pullData(obj.curr);
          }
          // #### 源码修改 #### 支持多语言的page
          if (that.config.pageLanguage && !(that.config.pageLanguage === true)) {
            var pageLanguageText;
            if (typeof that.config.pageLanguage === 'string') {
              if (!table.config.pageLanguageText[that.config.pageLanguage]) {
                console.log('找不到' + that.config.pageLanguage + '对应的语言文本定义');
                return;
              }
              pageLanguageText = table.config.pageLanguageText[that.config.pageLanguage];
            } else if (typeof that.config.pageLanguage === 'object') {
              var lanTemp = that.config.pageLanguage.lan;
              if (!lanTemp) {
                return;
              }
              pageLanguageText = $.extend({}, table.config.pageLanguageText[lanTemp], that.config.pageLanguage.text || {});
            } else {
              return;
            }

            if (!pageLanguageText) {
              return;
            }

            // 处理page支持en
            var pageElem = that.layPage.find('>div');
            pageElem.addClass(HIDE);
            var skipElem = pageElem.find('.layui-laypage-skip');
            var skipInput = skipElem.find('input');
            var skipBtn = skipElem.find('button');
            skipElem.html(pageLanguageText['jumpTo'] || 'jump to');
            skipInput.appendTo(skipElem);
            skipElem.append(pageLanguageText['page'] || 'page');
            skipBtn.html(pageLanguageText['go'] || 'go').appendTo(skipElem);

            var countElem = pageElem.find('.layui-laypage-count');
            var countText = countElem.text();
            countElem.html((pageLanguageText['total'] || 'total') + ' ' + countText.split(' ')[1] + (pageLanguageText['unit'] ? ' ' + pageLanguageText['unit'] : ''));

            var limitsElem = pageElem.find('.layui-laypage-limits');
            layui.each(limitsElem.find('option'), function (index, optionElem) {
              optionElem = $(optionElem);
              var textTemp = optionElem.text();
              optionElem.html(textTemp.split(' ')[0] + ' ' + (pageLanguageText['optionText'] || 'limit each page'));
            });
            pageElem.removeClass(HIDE);
          }
        }
      }, options.page);
      options.page.count = count; //更新总条数
      laypage.render(options.page);
    }
    // 渲染完毕做一些处理
    renderDone.call(that, true);
  };

  /* 固定列高度还有bug */
  var setColsWidth = table.Class.prototype.setColsWidth;
  table.Class.prototype.setColsWidth = function () {
    var that = this;
    // that.layBox.find('.' + LOADING).remove();
    setColsWidth.call(that);

    var options = that.config;
    var tableId = options.id;
    var tableView = that.elem;

    var noneElem = tableView.find('.' + NONE);

    // 如果没有数据的时候表头内容的宽度超过容器的宽度
    that.elem[noneElem.length && (that.layHeader.first().find('.layui-table').width() - 1) > that.layHeader.first().width() ? 'addClass' : 'removeClass']('layui-table-none-overflow');

    //如果多级表头，重新填补填补表头高度
    if (options.cols.length > 1) {
      //补全高度
      // var th = that.layFixed.find(ELEM_HEADER).find('th');
      // 只有有头部的高度的时候计算才有意义
      // var heightTemp = that.layHeader.height();
      // heightTemp = heightTemp / options.cols.length; // 每一个原子tr的高度
      var colsNum = options.cols.length;
      var thBox = that.layBox.find(ELEM_HEADER);
      var thElem = that.layFixed.find(ELEM_HEADER + ' th');
      var matchFlag = false;
      thElem.each(function (index, thCurr) {
        thCurr = $(thCurr);
        var rowspan = parseInt(thCurr.attr('rowspan') || '1');
        // var _thH = heightTemp * (parseInt(thCurr.attr('rowspan') || 1))
        //   - 1 - parseFloat(thCurr.css('padding-top')) - parseFloat(thCurr.css('padding-bottom'));
        // 找到原始的box里面的th的高度,只有行合并小于最大合并数的才需要设置高度
        if (rowspan < colsNum) {
          thCurr.height(thBox.find('th[data-key="' + thCurr.attr('data-key') + '"]').height());
          matchFlag = true;
        }
        // thCurr.height(_thH);
      });
      if (matchFlag) {
        // 修复ie浏览器下因为全行合并的高度导致后面的一些高度异常的问题
        that.layFixed.find('>' + ELEM_HEADER + ' th[rowspan="' + colsNum + '"]').height('auto');
      }
    }

    // that.layBody.scrollTop(0);
    // that.layBody.scrollLeft(0);
    // that.layHeader.scrollLeft(0);
  };

  $(window).resize(function () {
    layer.close(filterLayerIndex);
  });

  //初始化一些参数
  table.Class.prototype.setInit = function (type) {
    var that = this
      , options = that.config;
    var tableId = options.id;

    options.clientWidth = options.width || function () { //获取容器宽度
      //如果父元素宽度为0（一般为隐藏元素），则继续查找上层元素，直到找到真实宽度为止
      var getWidth = function (parent) {
        var width, isNone;
        parent = parent || options.elem.parent();
        width = parent.width();
        try {
          isNone = parent.css('display') === 'none';
        } catch (e) {
        }
        if (parent[0] && (!width || isNone)) return getWidth(parent.parent());
        return width;
      };
      return getWidth();
    }();

    if (type === 'width') return options.clientWidth;

    // 去掉之前的记录
    delete table.thisTable.arrs[tableId];

    if (!tableCheck.check(tableId)) {
      // 如果render的时候设置了checkStatus或者全局设置了默认跨页保存那么重置选中状态
      tableCheck.init(tableId, options.checkStatus ? (options.checkStatus['default'] || []) : []);
    } else {
      if (options.checkStatus && options.checkStatus['default']) {
        // 如果在option里面设置了默认选中的数据
        tableCheck.set(tableId, CHECK_TYPE_ORIGINAL, options.checkStatus['default']);
      }
    }

    if (options.checkDisabled && isArray(options.checkDisabled.data) && options.checkDisabled.data.length) {
      tableCheck.disabled(tableId, isArray(options.checkDisabled.data) ? options.checkDisabled.data : []);
    }

    var record;
    // 如果配置了字段筛选的记忆需要更新字段的hide设置
    if (options.colFilterRecord) {
      record = colFilterRecord.get(tableId, options.colFilterRecord);
      // 把修改hide的逻辑挪到下面做减少遍历
      // var record = colFilterRecord.get(tableId, options.colFilterRecord);
      // $.each(options.cols, function (i, item1) {
      //   $.each(item1, function (j, item2) {
      //
      //     item2.hide = !!record[item2.field];
      //   });
      // });
    } else {
      colFilterRecord.clear(tableId);
    }

    // 封装对col的配置处理
    var initCols = function (i1, item1, i2, item2) {
      //如果列参数为空，则移除
      if (!item2) {
        item1.splice(i2, 1);
        return;
      }

      item2.key = i1 + '-' + i2;
      item2.hide = item2.hide || false;
      item2.colspan = item2.colspan || 1;
      item2.rowspan = item2.rowspan || 1;

      //根据列类型，定制化参数
      that.initOpts(item2);

      // 如果配置了字段筛选的记忆需要更新字段的hide设置
      item2.hide = (record && item2.type === "normal" && item2.field && typeof record[item2.field] === 'boolean') ? record[item2.field] : item2.hide;


      // plug修改，根据配置信息确定是否合并列
      if (!item2.field && !item2.toolbar && (!item2.colspan || item2.colspan === 1) && (tableSpacialColType.indexOf(item2.type) === -1)) {
        item2[COLGROUP] = true;
      } else if (item2[COLGROUP] && !(item2.colspan > 1)) {
        // 如果有乱用colGroup的，明明是一个字段列还给它添加上这个属性的会在这里KO掉，
        item2[COLGROUP] = false;
      }

      //设置列的父列索引
      //如果是组合列，则捕获对应的子列
      if (item2.colGroup || item2.colspan > 1) {
        var childIndex = 0;
        // #### 源码修改 #### 修复复杂表头数据与表头错开的bug
        var indexChild = i1 + (parseInt(item2.rowspan) || 1);
        layui.each(options.cols[indexChild], function (i22, item22) {
          //如果子列已经被标注为{HAS_PARENT}，或者子列累计 colspan 数等于父列定义的 colspan，则跳出当前子列循环
          if (item22.HAS_PARENT || (childIndex >= 1 && childIndex == (item2.colspan || 1))) return;

          item22.HAS_PARENT = true;
          item22.parentKey = i1 + '-' + i2;
          childIndex = childIndex + parseInt(item22.colspan > 1 ? item22.colspan : 1);
          initCols(indexChild, options.cols[indexChild], i22, item22);
        });
        item2.colGroup = true; //标注是组合列
      }

      // //根据列类型，定制化参数
      // that.initOpts(item2);
    };


    //初始化列参数
    layui.each(options.cols, function (i1, item1) {
      if (i1 > 0) {
        return true
      }
      layui.each(item1, function (i2, item2) {
        // 调用解析cols
        initCols(i1, item1, i2, item2);
      });
    });
  };

  var tableInsReload = table.Class.prototype.reload;
  //表格完整重载
  table.Class.prototype.reload = function (options) {
    var that = this;
    table.reload(that.config.id, options, true);
  };

  // 添加一条临时数据
  table.Class.prototype.addTemp = function (numbers, data, callback, notScroll) {
    var that = this;
    var tds_fixed = [], tds_fixed_r = [], tds = [];
    var options = that.config, item1 = data || {};
    numbers = -numbers;
    if (that.layBody.find('tr[data-index="' + numbers + '"]').length) {
      // 已经存在
      return;
    }
    table.cache[that.key][numbers] = item1;
    that.eachCols(function (i3, item3) {
      var field = item3.field || i3
        , key = options.index + '-' + item3.key
        , content = item1[field];

      if (content === undefined || content === null) content = '';
      if (item3.colGroup) return;

      //td内容
      var td = ['<td data-field="' + field + '" data-key="' + key + '" ' + function () { //追加各种属性
        var attr = [];
        if (item3.type === 'normal' && item3.edit !== false) attr.push('data-edit="text"'); //是否允许单元格编辑
        if (item3.align) attr.push('align="' + item3.align + '"'); //对齐方式
        if (item3.templet) attr.push('data-content="' + content + '"'); //自定义模板
        if (item3.toolbar) attr.push('data-off="true"'); //行工具列关闭单元格事件
        if (item3.event) attr.push('lay-event="' + item3.event + '"'); //自定义事件
        if (item3.style) attr.push('style="' + item3.style + '"'); //自定义样式
        if (item3.minWidth) attr.push('data-minwidth="' + item3.minWidth + '"'); //单元格最小宽度
        return attr.join(' ');
      }() + ' class="' + function () { //追加样式
        var classNames = [];
        if (item3.hide) classNames.push(HIDE); //插入隐藏列样式
        if (!item3.field) classNames.push('layui-table-col-special'); //插入特殊列样式
        return classNames.join(' ');
      }() + '">'
        , '<div class="layui-table-cell laytable-cell-' + function () { //返回对应的CSS类标识
          return item3.type === 'normal' ? key
            : (key + ' laytable-cell-' + item3.type);
        }() + '">' + function () {
          var tplData = $.extend(true, {
            LAY_INDEX: numbers
          }, item1);

          //渲染不同风格的列
          switch (item3.type) {
            case 'checkbox':
            case 'radio':
            case 'numbers':
              return '';
              break;
          }

          //解析工具列模板
          if (item3.toolbar) {
            return '';
          }
          // return item3.templet ? function () {
          //   return typeof item3.templet === 'function'
          //     ? item3.templet(tplData)
          //     : laytpl($(item3.templet).html() || String(content)).render(tplData)
          // }() : content;
          return parseTempData(item3, content, tplData);
        }()
        , '</div></td>'].join('');

      tds.push(td);
      if (item3.fixed && item3.fixed !== 'right') tds_fixed.push(td);
      if (item3.fixed === 'right') tds_fixed_r.push(td);
    });

    that.layMain.find('.' + NONE).remove();
    that.elem.removeClass('layui-table-none-overflow');
    // 追加到最后
    that.layMain.find('tbody').append('<tr class="layui-tablePlug-data-temp" data-index="' + numbers + '">' + tds.join('') + '</tr>');
    that.layFixLeft.removeClass(HIDE).find('tbody').append('<tr class="layui-tablePlug-data-temp" data-index="' + numbers + '">' + tds_fixed.join('') + '</tr>');
    that.layFixRight.find('tbody').append('<tr class="layui-tablePlug-data-temp" data-index="' + numbers + '">' + tds_fixed_r.join('') + '</tr>');
    that.renderForm();
    that.resize();
    // 滚动到底部
    notScroll || that.layBody.scrollTop(that.layBody[0].scrollHeight);

    that.layBody.find('tr.layui-tablePlug-data-temp[data-index="' + numbers + '"]')
      .find('td:first-child')
      .append('<div class="close_temp"></div>');

    that.layFixRight.find('.close_temp').remove();

    // 执行回调，传过去两个参数，第一个是当前的table的config,第二个是新增的这个临时的tr的jquery对象
    typeof callback === 'function' && callback.call(that.config, that.layBody.find('tr[data-index="' + numbers + '"]'));
  };

  // 对外提供添加临时数据的接口
  table.addTemp = function (id, data, callback) {
    var ins = getIns(id);
    if (typeof data === 'function') {
      callback = data;
      data = {};
    }
    ins && ins.addTemp(table.getTemp(id).numbers, (data && typeof data === 'object') ? data : {}, callback);
  };

  // 获得临时数据
  table.getTemp = function (id) {
    var data = table.cache[id] || [];
    var dataTemp = [], i = 1;
    for (; ; i++) {
      if (data[-i]) {
        if (isArray(data[-i])) {
          // 无效的数据
        } else {
          dataTemp.push(data[-i]);
        }
      } else {
        break;
      }
    }
    return {
      data: dataTemp,
      numbers: i
    }
  };

  // 清空临时数据
  table.cleanTemp = function (id, index) {
    var ins = getIns(id);
    var dataTemp = table.getTemp(id);
    var data = table.cache[id] || [];
    if (dataTemp.data.length) {
      var numbers = dataTemp.numbers;
      for (var i = 1; i < numbers; i++) {
        if (data[-i] && (index ? -i === index : true)) {
          data[-i] = [];
          if (index) {
            break;
          }
        }
      }
    }
    // 删除节点
    $('div.layui-table-view[lay-id="' + id + '"]').removeClass('has-data-temp-warn')
      .find('tr.layui-tablePlug-data-temp[data-index' + (index ? '="' + index + '"' : '') + ']').remove();
    table.resize(id);
    ins.layBody.scrollTop(ins.layBody[0].scrollHeight);
  };

  $(document).on('click', '.layui-table-view tr.layui-table-hover.layui-tablePlug-data-temp div.close_temp', function (event) {
    layui.stope(event);
    var btnElem = $(this);
    var trElem = btnElem.closest('tr');
    var tableId = trElem.closest('.layui-table-view').attr('lay-id');
    table.cleanTemp(tableId, trElem.data('index'));
  });

  var asyncChild = function (index, cols, i1, item2) {
    //如果是组合列，则捕获对应的子列
    // if (!item2.CHILD_COLS && item2.colGroup) {
    if (item2.colGroup) {
      var childIndex = 0;
      index++
      item2.CHILD_COLS = [];
      // #### 源码修改 #### 修复复杂表头数据与表头错开的bug
      // 找到它的子列所在cols的下标
      var i2 = i1 + (parseInt(item2.rowspan) || 1);
      layui.each(cols[i2], function (i22, item22) {
        //如果子列已经被标注为{PARENT_COL_INDEX}，或者子列累计 colspan 数等于父列定义的 colspan，则跳出当前子列循环
        if (item22.PARENT_COL_INDEX || (childIndex >= 1 && childIndex == (item2.colspan || 1))) return;
        item22.PARENT_COL_INDEX = index;

        item2.CHILD_COLS.push(item22);
        childIndex = childIndex + parseInt(item22.colspan > 1 ? item22.colspan : 1);
        asyncChild(index, cols, i2, item22);
      });
    }
  };

  //遍历表头
  table.eachCols = function (id, callback, cols) {
    var that = this;
    var config = that.thisTable.config[id] || {}
      // , arrs = [], index = 0;
      , arrs = config.cols ? (that.thisTable.arrs[id] || []) : [], index = 0;
    cols = $.extend(true, [], cols || config.cols);
    // arrs = ;
    var cacheFlag;

    if (!arrs.length) {
      //重新整理表头结构
      layui.each(cols, function (i1, item1) {
        if (i1 > 0) {
          return true;
        }
        layui.each(item1, function (i2, item2) {

          // //如果是组合列，则捕获对应的子列
          // if (item2.colGroup) {
          //   var childIndex = 0;
          //   index++
          //   item2.CHILD_COLS = [];
          //   // #### 源码修改 #### 修复复杂表头数据与表头错开的bug
          //   // 找到它的子列
          //   // layui.each(cols[i1 + 1], function(i22, item22){
          //   layui.each(cols[i1 + (parseInt(item2.rowspan) || 1)], function (i22, item22) {
          //     //如果子列已经被标注为{PARENT_COL_INDEX}，或者子列累计 colspan 数等于父列定义的 colspan，则跳出当前子列循环
          //     if (item22.PARENT_COL_INDEX || (childIndex >= 1 && childIndex == (item2.colspan || 1))) return;
          //     item22.PARENT_COL_INDEX = index;
          //
          //     item2.CHILD_COLS.push(item22);
          //     childIndex = childIndex + parseInt(item22.colspan > 1 ? item22.colspan : 1);
          //   });
          // }

          asyncChild(index, cols, i1, item2);

          if (item2.PARENT_COL_INDEX) return; //如果是子列，则不进行追加，因为已经存储在父列中
          arrs.push(item2);

          that.thisTable.arrs[id] = arrs;
        });
      });
    } else {
      console.log('命中缓存!!');
      cacheFlag = true;
    }

    //重新遍历列，如果有子列，则进入递归
    var eachArrs = function (obj) {
      layui.each(obj || arrs, function (i, item) {
        var key = item.key.split('-');

        // 因为加了缓存需要同步他的hide信息
        if (cacheFlag) {
          // 将缓存中的hide跟cols的hide同步
          item.hide = cols[key[0]][key[1]].hide;
          // 将之前设置的选中状态去掉
          item[table.config.checkName] = false;
          // 如果没有设置width默认成自动分配宽度列
          item.width = item.width || 0;
        }
        if (item.CHILD_COLS) return eachArrs(item.CHILD_COLS);
        typeof callback === 'function' && callback(i, item);
      });
    };

    eachArrs();
  };

  // 字段过滤的相关功能
  var addFieldFilter = function () {
    var that = this;
    var tableId = that.key;
    var tableView = that.elem;

    table.eachCols(tableId, function (index, item) {
      if (item.type === 'normal') {
        var field = item.field;
        if (!field) {
          return;
        }
        var thElem = tableView.find('th[data-field="' + field + '"]');
        if (!item.filter) {
          thElem.find('.layui-table-filter').remove();
        } else {
          if (!thElem.find('.layui-table-filter').length) {
            $(LayuiTableColFilter.join('')).insertAfter(thElem.find('.layui-table-cell>span:not(.layui-inline)')).click(function (event) {
              layui.stope(event);
              var filterActive = tableView.find('.layui-table-filter.layui-active');
              if (filterActive.length && filterActive[0] !== this) {
                // 目前只支持单列过滤，多列过滤会存在一些难题，不好统一，干脆只支持单列过滤
                filterActive.removeClass('layui-active');
                that.layBody.find('tr.' + HIDE).removeClass(HIDE);
              }
              var mainElem = tableView.find('.layui-table-main');
              var nodes = [];
              layui.each(mainElem.find('td[data-field="' + field + '"]'), function (index, elem) {
                elem = $(elem);
                var textTemp = elem.text();
                if (nodes.indexOf(textTemp) === -1) {
                  nodes.push(textTemp);
                }
              });
              var layerWidth = 200;
              var layerHeight = 300;
              var btnElem = $(this);
              var btnPosition = getPosition(btnElem.find('.layui-tablePlug-icon-filter'));
              var topTemp = btnPosition.top;
              var leftTemp = btnPosition.left + btnElem.width();
              if (leftTemp + layerWidth > $(document).width()) {
                leftTemp -= (layerWidth + btnElem.width());
              }
              filterLayerIndex = layer.open({
                content: '',
                title: null,
                type: 1,
                // area: [layerWidth + 'px', layerHeight + 'px'],
                area: layerWidth + 'px',
                shade: 0.1,
                closeBtn: 0,
                fixed: false,
                resize: false,
                shadeClose: true,
                offset: [topTemp + 'px', leftTemp + 'px'],
                isOutAnim: false,
                maxmin: false,
                success: function (layero, index) {
                  layero.find('.layui-layer-content').html('<table id="layui-tablePlug-col-filter" lay-filter="layui-tablePlug-col-filter"></table>');
                  table.render({
                    elem: '#layui-tablePlug-col-filter',
                    data: nodes.map(function (value, index1, array) {
                      var nodeTemp = {
                        name: value
                      };
                      nodeTemp[table.config.checkName] = !that.layBody.find('tr.' + HIDE).filter(function (index, item) {
                        return $(item).find('td[data-field="' + field + '"]').text() === value;
                      }).length;
                      return nodeTemp;
                    }),
                    page: false,
                    skin: 'nob',
                    // id: 'layui-tablePlug-col-filter-layer',
                    even: false,
                    height: nodes.length > 8 ? layerHeight : null,
                    size: 'sm',
                    style: 'margin: 0;',

                    cols: [[
                      {type: 'checkbox', width: 40},
                      {
                        field: 'name',
                        title: '全选<span class="table-filter-opt-invert" onclick="layui.tablePlug && layui.tablePlug.tableFilterInvert(this);">反选</span>'
                      }
                    ]]
                  })
                },
                end: function () {
                  btnElem[that.layBody.find('tr.' + HIDE).length ? 'addClass' : 'removeClass']('layui-active');
                }
              });

              // 监听字段过滤的列选择的
              table.on('checkbox(layui-tablePlug-col-filter)', function (obj) {
                if (obj.type === 'all') {
                  that.layBody.find('tr')[obj.checked ? 'removeClass' : 'addClass'](HIDE);
                } else {
                  layui.each(that.layBody.first().find('tr td[data-field="' + field + '"]'), function (index, elem) {
                    elem = $(elem);
                    if (elem.text() === obj.data.name) {
                      var trElem = elem.parent();
                      that.layBody.find('tr[data-index="' + trElem.data('index') + '"]')[obj.checked ? 'removeClass' : 'addClass'](HIDE);
                    }
                  });
                }
                // that.resize();
              });

            });
          } else {
            // thElem.find('.layui-table-filter')[that.layBody.find('tr.' + HIDE).length ? 'addClass' : 'removeClass']('layui-active');
            thElem.find('.layui-table-filter').removeClass('layui-active');
          }
        }
      }
    }, that.config.cols);
  };

  // 表格反转
  table.Class.prototype.reverse = function () {
    var that = this;
    var config = that.config;
    var isVertical = config.reversal;

    that.elem[isVertical ? 'addClass' : 'removeClass']('vertical');
    setTimeout(function () {
      that.layTotal.css({top: isVertical ? that.layTool.outerHeight() - 1 + 'px' : 0});
      that.layMain.css({
        marginLeft: isVertical ? that.layHeader.width() + 'px' : 0,
        marginRight: (isVertical && config.totalRow) ? that.layTotal.width() + 'px' : 0
      });
      isVertical || that.layHeader.scrollLeft(that.layMain.scrollLeft());
    }, 0);
    return that;
  };

  // 调整表格实例resize的逻辑如果表格是反转了需要重新更新一下反转的效果
  // var tableResize = table.Class.prototype.resize;
  // if (!tableResize.modifiedByTablePlug) {
  //   table.Class.prototype.resize = function () {
  //     var that = this;
  //     var ret = tableResize.call(that);
  //     that.config.reversal === true && that.reverse();
  //     return ret;
  //   };
  //   table.Class.prototype.resize.modifiedByTablePlug = true;
  // }

  //遍历表头
  table.Class.prototype.eachCols = function (callback) {
    var that = this;
    // table.eachCols(null, callback, that.config.cols);
    table.eachCols(that.config.id, callback, that.config.cols);
    return that;
  };

  // 记录解析之后的cols arr
  table.thisTable.arrs = {};
  // 改造table.render和reload记录返回的对象
  var tableRender = table.render;
  table.render = function (config) {
    var that = this;
    var insTemp = tableRender.call(that, config);
    var insObj = getIns(insTemp.config.id); // 获得当前的table的实例，对实例内部的方法进行改造
    if (!insObj || !insObj.layMain) {
      console.log('出现render过后还没有找到需要的对象或者节点的情况');
    }
    // 同步滚动条 如果render的节点不存在，就不处理事件
    insObj.layMain && insObj.layMain.off('scroll') // 去掉layMain原始的事件
      .on('scroll', function () {

        var othis = $(this)
          , scrollLeft = othis.scrollLeft()
          , scrollTop = othis.scrollTop();

        insObj.layHeader.scrollLeft(scrollLeft);
        insObj.layTotal.scrollLeft(scrollLeft);
        // 过滤掉鼠标滚动fixed区域而联动滚动main的情况
        // insObj.layFixed.find(ELEM_BODY + ':not(.' + FIXED_SCROLL + ')').scrollTop(scrollTop);
        insObj.layFixed.find(ELEM_BODY + ':not(:hover)').scrollTop(scrollTop);

        layer.close(insObj.tipsIndex);
      });

    // 监听ELEM_BODY的滚动
    insObj.layFixed && insObj.layFixed.find(ELEM_BODY).on('scroll', function () {
      var elemBody = $(this);
      if (elemBody.is(':hover')) {  // 只有当前鼠标的fixed区域才需要处理
        // 同步两个fixed的滚动
        insObj.layFixed.find(ELEM_BODY + ':not(:hover)').scrollTop(elemBody.scrollTop());
        // 联动main的滚动
        insObj.layMain.scrollTop(elemBody.scrollTop());
      }
    });

    if (refresh.timer[insTemp.config.id]) {
      // 存在定时刷新
      refresh.reset(insTemp.config.id);
    }
    return tableIns[insTemp.config.id] = insTemp;
  };

  // 改造table reload
  var tableReload = table.reload;
  var queryParams = (function () {
    // 查询模式的白名单
    var params = ['url', 'method', 'where', 'contentType', 'headers', 'parseData', 'request', 'response', 'data', 'page', 'initSort', 'autoSort', 'reversal'];
    // 查询模式的黑名单
    var params_blacklist = ['id', 'elem', 'cols', 'width', 'height'];
    return {
      // 获得查询的属性
      getParams: function () {
        return $.extend(true, [], params);
      },
      getParamsBlacklist: function () {
        return $.extend(true, [], params_blacklist);
      },
      // 注册查询的属性，方便后面自己有扩展新的功能的时候，有一些配置可以注册成不重载的属性
      registParams: function () {
        // 过渡期处理
        // 智能重载
        console.warn('智能重载相关的部分已经从查询模式白名单模式修改为查询模式参数的黑名单模式，所以该方法基本是启用状态，' +
          '自定义的参数如果不是会修改整个table的框子的可以不用像之前一样要注册一下加入白名单了，使用起来会更加方便一些');
        // var that = this;
        // layui.each(arguments, function (i, value) {
        //   if (isArray(value)) {
        //     that.registParams.apply(that, value);
        //   } else {
        //     if (typeof value === 'string' && params.indexOf(value) === -1) {
        //       params.push(value);
        //     }
        //   }
        // });
      },
      registerBlacklist: function () {
        // 将一些属性加入黑名单
        console.warn('不建议自己调用改方法将参数加入查询参数的黑名单，除非是在非常了解该功能的前提下');
        var that = this;
        layui.each(arguments, function (i, value) {
          if (isArray(value)) {
            that.registerBlacklist.apply(that, value);
          } else {
            if (typeof value === 'string' && params_blacklist.indexOf(value) === -1) {
              params_blacklist.push(value);
            }
          }
        });
      }
      // check: function () {
      //
      // }
    }
  })();

  // 是否启用智能重载模式
  var smartReload = (function () {
    var enable = true; // v0.2.0之后默认开启智能重载
    return {
      enable: function () {
        if (arguments.length) {
          var isEnable = arguments[0];
          if (typeof isEnable !== "boolean") {
            hint.error('如果要开启或者关闭全局的表格智能重载模式，请传入一个true/false为参数');
          } else {
            enable = isEnable;
          }
        } else {
          return enable;
        }
      }
    }
  })();

  // 添加两个目前tablePlug扩展的属性到查询模式白名单中
  // queryParams.registParams('colFilterRecord', 'checkStatus', 'smartReloadModel', 'checkDisabled');

  table.reload = function (tableId, config, shallowCopy) {
    var that = this;
    config = config || {};

    var configOld = getConfig(tableId);
    var configTemp = $.extend(true, {}, getConfig(tableId), config);
    // 如果不记录状态的话就重置目前的选中记录
    if (!configTemp.checkStatus) {
      tableCheck.reset(tableId);
    }
    if (smartReload.enable() && configTemp.smartReloadModel) {

      // 如果开启了智能重载模式
      // 是否为重载模式
      var reloadModel = false;
      if (!!configTemp.page !== !!configOld.page) {
        // 如果是否分页发生了改变
        reloadModel = true;
      }
      // if (!reloadModel) {
      //   // 白名单的校验
      //   var dataParamsTemp = $.extend(true, [], queryParams.getParams());
      //
      //   layui.each(config, function (_key, _value) {
      //     var indexTemp = dataParamsTemp.indexOf(_key);
      //     if (indexTemp === -1) {
      //       return reloadModel = true;
      //     } else {
      //       // 如果匹配到去掉这个临时的属性，下次查找的时候减少一个属性
      //       dataParamsTemp.splice(indexTemp, 1);
      //     }
      //   });
      // }
      if (!reloadModel) {
        // 黑名单的校验
        var dataParamsTemp = queryParams.getParamsBlacklist();

        layui.each(config, function (_key, _value) {
          var indexTemp = dataParamsTemp.indexOf(_key);
          if (indexTemp !== -1) {
            return reloadModel = true;
          }
        });
      }

      if (!reloadModel) {
        var insTemp = getIns(tableId);

        if (typeof config.page === 'object') {
          config.page.curr && (insTemp.page = config.page.curr);
          delete config.elem;
          delete config.jump;
        }
        if(insTemp.config.data && insTemp.config.data.constructor === Array) delete insTemp.config.data;
        shallowCopy ? $.extend(insTemp.config, config) : $.extend(true, insTemp.config, config);
        if (!insTemp.config.page) {
          insTemp.page = 1;
        }
        insTemp.loading();
        insTemp.pullData(insTemp.page);
        return table.thisTable.call(insTemp);
      }
    }

    // 如果是重载
    if (shallowCopy) {
      tableInsReload.call(getIns(tableId), config);
      tableIns[tableId].config = getIns(tableId).config;
    } else {
      var insTemp = tableReload.call(that, tableId, config);
      return tableIns[tableId] = insTemp;
    }
  };

  // 获得table的config
  var getConfig = function (tableId) {
    return table.thisTable.config[tableId] || (tableIns[tableId] && tableIns[tableId].config) || {};
  };

  // 原始的
  var checkStatus = table.checkStatus;
  // 重写table的checkStatus方法
  table.checkStatus = function (tableId, getCacheData) {
    var that = this;
    var statusTemp = checkStatus.call(that, tableId);
    var config = getConfig(tableId);
    if (config && config.checkStatus) {
      // 状态记忆
      statusTemp.status = tableCheck.get(tableId);
      if (getCacheData) {
        statusTemp.dataCache = [];
        var cacheDataTemp = cacheData[config.id];
        layui.each(statusTemp.status[CHECK_TYPE_ADDITIONAL].concat(statusTemp.status[CHECK_TYPE_ORIGINAL]), function (index, id) {
          cacheDataTemp[id] && statusTemp.dataCache.push(table.clearCacheKey(cacheDataTemp[id]));
        });
      }
    }
    if (config && config.checkDisabled) {
      var checkDisabledTemp = config.checkDisabled;
      if (typeof checkDisabledTemp === 'object' && checkDisabledTemp.enabled !== false) {
        var num1 = 0; //可选的数量
        var num2 = 0; //最终选中的数量
        var primaryKey = getPrimaryKey(config);
        var disabledTemp = tableCheck.get(tableId, CHECK_TYPE_DISABLED);
        layui.each(table.cache[tableId], function (index, data) {
          var primaryValue = data[primaryKey];
          if (disabledTemp.indexOf(primaryValue) === -1) {
            num1++;
            if (data[table.config.checkName]) {
              num2++;
            }
          }
        });
        statusTemp.isAll = (num2 > 0 && num1 === num2);
      }
    }
    return statusTemp;
  };

  // 更新复选框的状态
  var updateCheckStatus = function (tableId, value, checked, isRadio) {
    if (!tableCheck.checkDisabled(tableId, value)) {
      tableCheck.update(tableId, value, checked, isRadio);
    } else {
      // 操作了不可选的
      return false;
    }
  };

  var getPrimaryKey = function (config) {
    if (config.primaryKey) {
      return config.primaryKey;
    }
    var keyStatus = config.checkStatus && config.checkStatus.primaryKey,
      keyDisabled = config.checkDisabled && config.checkDisabled.primaryKey;
    if (keyStatus && keyDisabled && keyStatus !== keyDisabled) {
      layui.hint().error('注意：当前表格(' + config.id + ')中checkStatus和checkDisabled都配置了primaryKey,但是他们不是同一个字段，必须保持表格配置中主键是唯一的，建议直接设置在顶层配置中就可以了！')
    }
    return keyDisabled || keyStatus || 'id';
  };

  // 监听所有的表格中的type:'checkbox'注意不要在自己的代码里面也写这个同名的监听，不然会被覆盖，
  table.on('checkbox', function (obj) {

    var tableView = $(this).closest('.layui-table-view');
    // lay-id是2.4.4版本新增的绑定到节点上的当前table实例的id,经过plug的改造render将旧版本把这个id也绑定到视图的div上了。
    var tableId = tableView.attr('lay-id');
    var config = getConfig(tableId);
    if (tableCheck.check(tableId)) {
      var _checked = obj.checked;
      var _data = obj.data;
      var _type = obj.type;

      var primaryKey = getPrimaryKey(config);

      if (_type === 'one') {
        updateCheckStatus(tableId, _data[primaryKey], _checked);
      } else if (_type === 'all') {
        // 全选或者取消全不选
        var renderFlag = false;
        layui.each(layui.table.cache[tableId], function (index, data) {
          var disableFlag = updateCheckStatus(tableId, data[primaryKey], _checked);
          if (disableFlag === false) {
            renderFlag = true;
            // 因为原始的table操作了不可选的复选框需要纠正一下状态
            var checkedTemp = tableCheck.getChecked(tableId).indexOf(data[primaryKey]) !== -1;
            tableView.find('.layui-table-body')
              .find('tr[data-index="' + index + '"]')
              .find('input[name="layTableCheckbox"]').prop('checked', checkedTemp);
            data[table.config.checkName] = checkedTemp;
          }
        });
        // renderFlag && getIns(tableId).renderForm('checkbox');
        renderFlag && form.render('checkbox', tableView.attr('lay-filter'));
      }
    }
  });

  // 单选状态记忆
  table.on('radio()', function (obj) {
    var tableView = obj.tr.closest('.layui-table-view');
    var tableId = tableView.attr('lay-id');
    var config = getConfig(tableId);
    if (tableCheck.check(tableId)) {
      var _checked = obj.checked;
      var _data = obj.data;
      var primaryKey = getPrimaryKey(config);
      updateCheckStatus(tableId, _data[primaryKey], _checked, true);
    }
  });

  // 让被美化的复选框支持原始节点的change事件
  form.on('checkbox', function (data) {
    $(data.elem).change();
  });

  // 表格筛选列的状态记录的封装
  var colFilterRecord = (function () {
    var recodeStoreName = 'tablePlug_col_filter_record';
    var getStoreType = function (recordType) {
      return recordType === 'local' ? 'data' : 'sessionData';
    };
    return {
      // 记录
      set: function (tableId, key, checked, recordType) {
        if (!tableId || !key) {
          return;
        }
        // 默认用sessionStore
        var storeType = getStoreType(recordType);
        var dataTemp = this.get(tableId, recordType);
        dataTemp[key] = !checked;
        layui[storeType](recodeStoreName, {
          key: tableId,
          value: dataTemp
        })
      },
      get: function (tableId, recordType) {
        return layui[getStoreType(recordType)](recodeStoreName)[tableId] || {};
      },
      clear: function (tableId) {
        $.each(['data', 'sessionData'], function (index, type) {
          layui[type](recodeStoreName, {
            key: tableId,
            remove: true
          });
        });
      }
    };
  })();

  // 监听表格筛选的点
  $(document).on('change', 'input[lay-filter="LAY_TABLE_TOOL_COLS"]', function (event) {
    var elem = $(this);
    // var key = elem.data('key');
    var key = elem.attr('name');
    var tableView = elem.closest('.layui-table-view');
    var tableId = tableView.attr('lay-id');
    var config = getConfig(tableId);
    var filterRecord = config.colFilterRecord;
    if (filterRecord) {
      colFilterRecord.set(tableId, key, this.checked, filterRecord);
    } else {
      colFilterRecord.clear(tableId)
    }
  });

  // 缓存当前操作的是哪个表格的哪个tr的哪个td
  $(document).off('mousedown', '.layui-table-grid-down')
    .on('mousedown', '.layui-table-grid-down', function (event) {
      // 记录操作的td的jquery对象
      table._tableTdCurr = $(this).closest('td');
    });

  // 给弹出的详情里面的按钮添加监听级联的触发原始table的按钮的点击事件
  $(document).off('click', '.layui-table-tips-main [lay-event]')
    .on('click', '.layui-table-tips-main [lay-event]', function (event) {
      var elem = $(this);
      var tableTrCurr = table._tableTdCurr;
      if (!tableTrCurr) {
        return;
      }
      var layerIndex = elem.closest('.layui-table-tips').attr('times');
      // 关闭当前的这个显示更多的tip
      layer.close(layerIndex);
      // 找到记录的当前操作的那个按钮
      table._tableTdCurr.find('[lay-event="' + elem.attr('lay-event') + '"]').first().click();
    });

  // 监听统一的toolbar一般用来处理通用的
  table.on('toolbar()', function (obj) {
    var config = obj.config;
    var btnElem = $(this);
    var tableId = config.id;
    var tableView = config.elem.next();
    switch (obj.event) {
      case 'LAYTABLE_COLS':
        // 给筛选列添加全选还有反选的功能
        var panelElem = btnElem.find('.layui-table-tool-panel');
        var checkboxElem = panelElem.find('[lay-filter="LAY_TABLE_TOOL_COLS"]');
        var checkboxCheckedElem = panelElem.find('[lay-filter="LAY_TABLE_TOOL_COLS"]:checked');
        $('<li class="layui-form" lay-filter="LAY_TABLE_TOOL_COLS_FORM">' +
          '<input type="checkbox" lay-skin="primary" lay-filter="LAY_TABLE_TOOL_COLS_ALL" '
          + ((checkboxElem.length === checkboxCheckedElem.length) ? 'checked' : '') + ' title="全选">' +
          '<span class="LAY_TABLE_TOOL_COLS_Invert_Selection">反选</span></li>')
          .insertBefore(panelElem.find('li').first())
          .on('click', '.LAY_TABLE_TOOL_COLS_Invert_Selection', function (event) {
            layui.stope(event);
            // 反选逻辑
            panelElem.find('[lay-filter="LAY_TABLE_TOOL_COLS"]+').click();
          });
        form.render('checkbox', 'LAY_TABLE_TOOL_COLS_FORM');
        break;
    }
  });

  // 监听筛选列panel中的全选
  form.on('checkbox(LAY_TABLE_TOOL_COLS_ALL)', function (obj) {
    $(obj.elem).closest('ul')
      .find('[lay-filter="LAY_TABLE_TOOL_COLS"]' + (obj.elem.checked ? ':not(:checked)' : ':checked') + '+').click();
  });

  // 监听筛选列panel中的单个记录的change
  $(document).on('change', 'input[lay-filter="LAY_TABLE_TOOL_COLS"]', function (event) {
    var elemCurr = $(this);
    // 筛选列单个点击的时候同步全选的状态
    $('input[lay-filter="LAY_TABLE_TOOL_COLS_ALL"]')
      .prop('checked',
        elemCurr.prop('checked') ? (!$('input[lay-filter="LAY_TABLE_TOOL_COLS"]').not(':checked').length) : false);
    form.render('checkbox', 'LAY_TABLE_TOOL_COLS_FORM');
  });

  // 逻辑移到另外的js里面去了

  // 阻止表格中lay-event的事件冒泡
  $(document).on('click', '.layui-table-view tbody [lay-event],.layui-table-view tbody tr [name="layTableCheckbox"]+', function (event) {
    layui.stope(event);
  });

  // 是否让固定列支持鼠标滚动
  var enableTableFixedScroll = function (enabled) {
    enabled = !!enabled;

    $(document).off('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXR + ' ' + ELEM_BODY)
      .off('mouseleave', '.' + ELEM_VIEW + ' ' + ELEM_FIXR + ' ' + ELEM_BODY)
      .off('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXL + ' ' + ELEM_BODY)
      .off('mouseleave', '.' + ELEM_VIEW + ' ' + ELEM_FIXL + ' ' + ELEM_BODY)
      .off('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXED + ' ' + ELEM_HEADER);

    if (enabled) {
      // 给固定列添加一些鼠标的事件监听，用于支持固定列滚动
      $(document).on('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXR + ' ' + ELEM_BODY, function (event) {
        // 处理鼠标移入右侧
        var elem = $(this);
        var elemFixedR = elem.closest(ELEM_FIXR);
        if (elemFixedR.css('right') !== '-1px') {
          // 如果有滚动条的话
          elemFixedR.addClass(FIXED_SCROLL);
        } else {
          setTimeout(function () {
            if (elemFixedR.css('right') !== '-1px') {
              console.log('出现了一开始还没有打滚动条补丁的时候就触发的情况');
              elemFixedR.addClass(FIXED_SCROLL);
            }
          }, 50);
        }
      }).on('mouseleave', '.' + ELEM_VIEW + ' ' + ELEM_FIXR + ' ' + ELEM_BODY, function (event) {
        // 处理鼠标移出右侧
        $(this).closest(ELEM_FIXR).removeClass(FIXED_SCROLL);
      }).on('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXL + ' ' + ELEM_BODY, function (event) {
        // 处理鼠标移入左侧
        var elem = $(this);
        var elemFixedL = elem.closest(ELEM_FIXL);
        elemFixedL.addClass(FIXED_SCROLL);
        var widthOut = elemFixedL.find(ELEM_HEADER).find('table').width();
        var widthIn = elemFixedL.find(ELEM_HEADER).width() + 1;
        elemFixedL.css({width: widthOut + 'px'}).find(ELEM_BODY).css({width: widthIn + 'px'});
      }).on('mouseleave', '.' + ELEM_VIEW + ' ' + ELEM_FIXL + ' ' + ELEM_BODY, function (event) {
        // 处理鼠标移出左侧
        $(this).css({width: 'auto'}).closest(ELEM_FIXL).css({width: 'auto'}).removeClass(FIXED_SCROLL);
      }).on('mouseenter', '.' + ELEM_VIEW + ' ' + ELEM_FIXED + ' ' + ELEM_HEADER, function (event) {
        var elem = $(this);
        elem.closest(ELEM_FIXED).removeClass(FIXED_SCROLL);
      });
    }
  };

  // 默认开启固定列滚动监听的支持
  enableTableFixedScroll(true);


  // 同步表格中是否固定列上的的全选状态，
  form.on('checkbox(layTableAllChoose)', function (obj) {
    var elem = $(obj.elem);
    var tableView = elem.closest('.' + ELEM_VIEW);
    form.render('checkbox',
      tableView.attr('lay-filter'),
      tableView.find('[lay-filter="layTableAllChoose"]' + (obj.elem.checked ? ':not(:checked)' : ':checked')).prop('checked', obj.elem.checked)
    );
  });

  // 同步表格中是否固定列上的的复选框状态，
  form.on('checkbox(layTableCheckbox)', function (obj) {
    var elem = $(obj.elem);
    var tableView = elem.closest('.' + ELEM_VIEW);
    var trElem = tableView.find('tr[data-index="' + elem.closest('tr').data('index') + '"]');
    form.render('checkbox',
      tableView.attr('lay-filter'),
      trElem.find('[lay-filter="layTableCheckbox"]' + (obj.elem.checked ? ':not(:checked)' : ':checked')).prop('checked', obj.elem.checked)
    );
  });

  // 将cache的数据重新渲染一下
  var renderData = function (activeIndex, sort) {
    var that = this;
    var resTemp = {};
    var options = that.config;
    var scrollTop = that.layMain.scrollTop();
    var scrollLeft = that.layMain.scrollLeft();

    activeIndex = activeIndex >= 0 ? activeIndex : that.layBody.find('tr.' + ELEM_CLICK).data('index');
    resTemp[options.response.statusName] = options.response.statusCode;
    resTemp[options.response.msgName] = '数据更新';
    resTemp[options.response.dataName] = table.cache[options.id];
    resTemp[options.response.countName] = that.count || (options.page ? options.page.count : options.data.length);
    if (sort) {
      // 如果是移动了数据，会取消当前的排序规则，以当前的顺序为准进行调整，如果还需要排序
      // delete that.sortKey;
      that.layHeader.find('.layui-table-sort[lay-sort]').attr('lay-sort', '');
    }
    that.renderData(resTemp, that.page, resTemp[options.response.countName], sort);

    // 滚动到之前的位置
    that.layBody.scrollTop(scrollTop);
    that.layMain.scrollLeft(scrollLeft);

    // 执行done回调保证跟初始化一样
    typeof options.done === 'function' && options.done(resTemp, that.page, resTemp[options.response.countName]);

    activeIndex >= 0 && setTimeout(function () {
      that.setThisRowChecked(activeIndex);
    }, 0);

    return that;
  };

  // 表格记录顺序调整
  var move = function (tableId, from, to) {
    var that = this;
    if ((!from && from !== 0) || (!to && to !== 0) || from < 0 || to < 0 || from === to) {
      return that;
    }
    var tableObj = getIns(tableId);
    if (tableObj) {
      var options = tableObj.config;
      var dataTemp = table.cache[tableId];
      if (!dataTemp || !dataTemp[from] || !dataTemp[to]) {
        // 没有找到对应的数据
        return that;
      }
      dataTemp.splice(to, 0, dataTemp.splice(from, 1)[0]);

      renderData.call(tableObj, to, true);
    }
    return that;
  };

  // 更新表格的数据
  var update = function (tableId, dataIndex, data) {
    var that = this;

    if (!tableId) {
      console.warn('tableId不能为空');
      return that;
    }

    if (dataIndex && (typeof dataIndex === 'object' || isArray(dataIndex))) {
      // 第二个参数如果是要修改的数据的话
      data = dataIndex;
      dataIndex = '';
    }
    if (dataIndex === null || isNaN(dataIndex)) {
      // 如果dataIndex不是数值就作废
      dataIndex = '';
    }

    if (data && (typeof data !== 'object' && !isArray(data))) {
      // 存在data，但是格式不对
      console.warn('data格式必须是对象或者数组');
      return that;
    }

    var tableObj = getIns(tableId);
    if (tableObj) {
      var dataTemp = table.cache[tableId];
      var options = tableObj.config;
      if (!data) {
        // 没有传data默认为重新渲染一下cache里面的数据
        renderData.call(tableObj);
        return that;
      }

      if (isArray(data)) {
        // 如果是一次性更新多条记录
        dataIndex = '';
      }
      if (dataIndex !== '') {
        // 更新指定的某一条记录
        $.extend(true, dataTemp[dataIndex], data);
      } else {
        if (!isArray(data)) {
          data = [data];
        }
        if (!options.primaryKey) {
          // 没有设置主键的表格按照顺序update进去
          $.extend(true, dataTemp, data);
        } else {
          // 如果设置了主键按照主键一致更新进去
          // 遍历更新的数据
          layui.each(data, function (index, obj) {
            if (!obj[options.primaryKey]) {
              // 等价 continue
              return;
            }
            // 遍历缓存数据找到匹配的
            layui.each(dataTemp, function (indexTemp, objTemp) {
              if (objTemp[options.primaryKey] === obj[options.primaryKey]) {
                $.extend(true, dataTemp[indexTemp], obj);
                // 等价 break;
                return true;
              }
            });
          });
        }
      }
      renderData.call(tableObj);
    }
    return that;
  };

  // 新增记录
  var add = function (tableId, data) {
    var that = this;

    if (!tableId) {
      console.warn('tableId不能为空');
      return that;
    }

    if (!data) {
      console.warn('data不能为空');
      return that;
    }

    var tableObj = getIns(tableId);
    if (tableObj) {
      var options = tableObj.config;
      if (options.url) {
        // 如果是url模式的话直接reload会更加合理
        table.reload(tableId);
        return that;
      } else {
        if (isArray(options.data)) {
          if (typeof data !== 'object' && !isArray(data)) {
            console.warn('data必须是对象或者数组');
            return that;
          }
          // data 模式
          if (!isArray(data)) {
            data = [data];
          }
          var primaryKey = getPrimaryKey(options);
          layui.each(data, function (index, _data) {
            if (primaryKey) {
              // 如果有设置主键
              if (!_data[primaryKey]) {
                // 缺少主键的记录,临时生成一个，一般来说不建议缺少主键的值，这个add的功能常用在请求接口之后返回新增的数据了希望添加到data中
                // 如果新增走的不是先请求接口的建议还是用addTemp添加临时数据的形式
                _data[primaryKey] = 'idTemp_' + new Date().getTime() + '_' + Math.round(Math.random() * 1000000)
              }
            }
            options.data.push(_data)
          });
          table.reload(tableId);
        }
        return that;
      }
    }
  };

  // 删除记录
  var del = function (tableId, data) {
    var that = this;
    if (!tableId) {
      console.warn('tableId不能为空');
      return that;
    }
    if (!data && data !== 0) {
      console.warn('data不能为空');
      return that;
    }

    var tableObj = getIns(tableId);
    if (tableObj) {
      var options = tableObj.config;
      var optionsTemp = {};
      var countTemp = tableObj.count || (options.page ? options.page.count : options.data.length);
      var primaryKey = getPrimaryKey(options);
      if (options.url) {
        if (options.page) {
          if (isArray(data)) {
            countTemp -= data.length;
          } else {
            countTemp -= 1;
          }
          optionsTemp.page = {};
          var pagesTemp = Math.ceil(countTemp / options.page.limit);
          optionsTemp.page.curr = countTemp === 0 ? 1 : (tableObj.page > pagesTemp ? (pagesTemp || 1) : tableObj.page);
        }
        layui.each(data, function (index, item) {
          var idTemp = typeof item === 'object' ? item[primaryKey] : item;
          // 将选中的记录给去掉如果有的话
          tableCheck.update(tableId, idTemp, false);
        });
        // 如果是url模式的话直接reload会更加合理
        table.reload(tableId, optionsTemp);
        return that;
      } else {
        if (isArray(options.data)) {
          // data 模式
          if (typeof data !== 'object' && !isArray(data)) {
            if (isNaN(data)) {
              return that;
            }
            var nodeDel;
            // 删除某个下标
            if (options.page) {
              // 分页的
              if (data < options.page.limit) {
                nodeDel = options.data.splice((tableObj.page - 1) * options.page.limit + data, 1);
              }
            } else {
              nodeDel = options.data.splice(data, 1);
            }
            if (!nodeDel.length) {
              // 传过来的下标有问题，没有删除到数据
              return that;
            }
            countTemp -= 1;
            tableCheck.update(tableId, nodeDel[0][primaryKey], false);
          } else {
            if (!isArray(data)) {
              data = [data];
            }
            layui.each(data, function (index, _data) {
              if (primaryKey) {
                // 如果有设置主键
                if (_data && typeof _data === 'object' && !_data[primaryKey]) {
                  // 缺少主键的记录
                  return;
                }
              }
              layui.each(options.data, function (index, value) {
                // 传过来的data支持id集合和数据对象集合
                if (_data === value[primaryKey] || _data[primaryKey] === value[primaryKey]) {
                  options.data.splice(index, 1);
                  tableCheck.update(tableId, value[primaryKey], false);
                  countTemp -= 1;
                  return true;
                }
              });
            });
          }
          if (options.page) {
            optionsTemp.page = {};
            var pagesTemp = Math.ceil(countTemp / options.page.limit);
            optionsTemp.page.curr = countTemp === 0 ? 1 : (tableObj.page > pagesTemp ? (pagesTemp || 1) : tableObj.page);
          }
          table.reload(tableId, optionsTemp);
        }
        return that;
      }
    }
  };

  // 定时刷新某个表格
  var refresh = function (tableId, time) {
    var that = this;
    var timer = refresh.timer;
    if (tableId === false) {
      // 取消所有的定时刷新
      layui.each(timer, function (_id, _timer) {
        // clearInterval(_timer);
        refresh.clear(_id);
      });
      // refresh.timer = {};
      return that;
    }
    if (!tableId) {
      console.warn('tableId不能为空');
      return that;
    }
    var tableObj = getIns(tableId);
    if (!tableObj) {
      console.warn('找不到id为', tableId, '的实例');
      return that;
    }
    var options = tableObj.config;

    if (time === false) {
      // 清除当前表格的定时刷新
      // clearInterval(timer[tableId]);
      // delete timer[tableId];
      refresh.clear(tableId);
      return that;
    }

    if (!time && time !== 0) {
      // 单次刷新
      tableObj.pullData(tableObj.page, true);
      return that;
    }

    if (time === true || time < 50) {
      time = 50; // 默认最高频率为50毫秒一次
    }

    // clearInterval(timer[tableId]);
    refresh.clear(tableId);
    timer[tableId] = {
      time: time,
      index: setInterval(function () {
        if (!$(document).find(options.elem).length) {
          // 如果当前表格已经不存在
          refresh.call(that, tableId, false);
        } else {
          // tableObj.pullData(tableObj.page, true)
          refresh(tableId);
        }
      }, time)
    };

  };
  refresh.timer = {};
  refresh.clear = function (tableId) {
    if (refresh.timer[tableId]) {
      clearInterval(refresh.timer[tableId].index);
      var time = refresh.timer[tableId].time;
      delete refresh.timer[tableId];
      return time;
    }
  };
  refresh.reset = function (tableId) {
    refresh(tableId, refresh.clear(tableId));
  };


  // 渲染统计行
  var renderTotal = function (tableId, field, value) {
    var that = this;
    if (!tableId) {
      console.warn('tableId不能为空');
      return that;
    }
    var tableObj;
    if (typeof tableId === 'string') {
      tableObj = getIns(tableId);
    } else {
      tableObj = tableId;
      tableId = tableObj.key || (tableObj.config ? tableObj.config.id : '');
    }

    if (!tableObj || !tableId) {
      console.warn('找不到id为', tableId, '的实例');
      return that;
    }
    var options = tableObj.config;
    if (!options.totalRow) {
      return that;
    }

    var totalElem = tableObj.layTotal;
    if (field) {
      totalElem.find('td[data-field="' + field + '"] div.layui-table-cell').html(value || '');
      return that;
    }
    table.eachCols(tableId, function (index, item) {
      if (item.totalRow && !item.totalRowText && item.field) {
        var fieldTemp = item.field;
        var totalFormat = item.totalFormat || 'sum';
        var dataTemp = $.extend([], table.cache[tableId]);
        var res;
        if (typeof totalFormat === 'function') {
          res = totalFormat.call(options, tableId, dataTemp, fieldTemp);
        } else {
          res = 0;
          switch (totalFormat) {
            case 'sum':
              layui.each(dataTemp, function (index, data) {
                res += parseFloat(data[fieldTemp]) || 0;
              });
              break;
          }
        }
        res && totalElem.find('td[data-field="' + fieldTemp + '"] div.layui-table-cell').html(res || '');
      }
    });

  };


  $.extend(tablePlug, {
    CHECK_TYPE_ADDITIONAL: CHECK_TYPE_ADDITIONAL
    , CHECK_TYPE_REMOVED: CHECK_TYPE_REMOVED
    , CHECK_TYPE_ORIGINAL: CHECK_TYPE_ORIGINAL
    , tableCheck: tableCheck
    , colFilterRecord: colFilterRecord  // 表格字段筛选记忆功能的封装
    , getConfig: getConfig  // 表格复选列的方法封装
    , getIns: function (tableId) { // 获得某个表格render返回的实例的封装
      return tableIns[tableId];
    }
    , disabledCheck: function (tableId, data) {  // 同步表格中的某些不可点击的节点
      var that = this;
      tableCheck.disabled(tableId, data || []);
      disabledCheck.call(that, tableId, true);
    }
    , dataRenderChecked: dataRenderChecked
    // , getObj: getIns  // 得到当前table的实际的实例
    , queryParams: queryParams // 表格查询模式的配置封装
    , smartReload: smartReload // 全局设置一个是否开启智能重载模式
    // 反选
    , tableFilterInvert: function (elem) {
      elem = $(elem);
      var tableView = elem.closest('.layui-table-view'),
        tableId = tableView.attr('lay-id');
      if (!tableId) {
        return;
      }
      var checkStatus = table.checkStatus(tableId);
      if (checkStatus.isAll) {
        // 以前全选了反选既为全不选，直接点击一下全选这个复选框就可以了
        tableView.find('[lay-filter="layTableAllChoose"]+').click();
      } else {
        if (!tableView.find('tbody [name="layTableCheckbox"]:checked').length) {
          // 如果一个都没有选中也是直接点击全选按钮
          tableView.find('[lay-filter="layTableAllChoose"]+').click();
        } else {
          layui.each(tableView.find('tbody [name="layTableCheckbox"]'), function (index, item) {
            $(item).next().click();
          });
        }
      }
    }
    , getPosition: getPosition
    , reverseTable: reverseTable
    , move: move
    , moveUp: function (tableId, index) {
      return move.call(this, tableId, index, index - 1);
    }
    , moveDown: function (tableId, index) {
      return move.call(this, tableId, index, index + 1);
    }
    , update: update
    , addData: add
    , del: del
    , refresh: refresh
    , renderTotal: renderTotal
    , enableTableFixedScroll: enableTableFixedScroll
  });

  //外部接口
  exports(modelName, tablePlug);
});


