/**
 * Layui图标选择器
 * @author wujiawei0926@yeah.net
 * @version 1.1
 */

layui.define(['laypage', 'form'], function (exports) {
    "use strict";

    var IconPicker =function () {
        this.v = '1.1';
    }, _MOD = 'iconPicker',
        _this = this,
        $ = layui.jquery,
        laypage = layui.laypage,
        form = layui.form,
        BODY = 'body',
        TIPS = '请选择图标';

    /**
     * 渲染组件
     */
    IconPicker.prototype.render = function(options){
        var opts = options,
            // DOM选择器
            elem = opts.elem,
            // 数据类型：fontClass/unicode
            type = opts.type == null ? 'fontClass' : opts.type,
            // 是否分页：true/false
            page = opts.page == null ? true : opts.page,
            // 每页显示数量
            limit = opts.limit == null ? 12 : opts.limit,
            // 是否开启搜索：true/false
            search = opts.search == null ? true : opts.search,
            // 每个图标格子的宽度：'43px'或'20%'
            cellWidth = opts.cellWidth,
            // 点击回调
            click = opts.click,
            // 渲染成功后的回调
            success = opts.success,
            // json数据
            data = {},
            // 唯一标识
            tmp = new Date().getTime(),
            // 是否使用的class数据
            isFontClass = opts.type === 'fontClass',
            // 初始化时input的值
            ORIGINAL_ELEM_VALUE = $(elem).val(),
            TITLE = 'layui-select-title',
            TITLE_ID = 'layui-select-title-' + tmp,
            ICON_BODY = 'layui-iconpicker-' + tmp,
            PICKER_BODY = 'layui-iconpicker-body-' + tmp,
            PAGE_ID = 'layui-iconpicker-page-' + tmp,
            LIST_BOX = 'layui-iconpicker-list-box',
            selected = 'layui-form-selected',
            unselect = 'layui-unselect';

        var a = {
            init: function () {
                data = common.getData[type]();

                a.hideElem().createSelect().createBody().toggleSelect();
                a.preventEvent().inputListen();
                common.loadCss();
                
                if (success) {
                    success(this.successHandle());
                }

                return a;
            },
            successHandle: function(){
                var d = {
                    options: opts,
                    data: data,
                    id: tmp,
                    elem: $('#' + ICON_BODY)
                };
                return d;
            },
            /**
             * 隐藏elem
             */
            hideElem: function () {
                $(elem).hide();
                return a;
            },
            /**
             * 绘制select下拉选择框
             */
            createSelect: function () {
                var oriIcon = '<i class="iconfont">';
                
                // 默认图标
                if(ORIGINAL_ELEM_VALUE === '') {
                    if(isFontClass) {
                        ORIGINAL_ELEM_VALUE = 'layui-icon-picker-home';
                    } else {
                        ORIGINAL_ELEM_VALUE = '&#xe617;';
                    }
                }

                if (isFontClass) {
                    oriIcon = '<i class="iconfont '+ ORIGINAL_ELEM_VALUE +'">';
                } else {
                    oriIcon += ORIGINAL_ELEM_VALUE; 
                }
                oriIcon += '</i>';

                var selectHtml = '<div class="layui-iconpicker layui-unselect layui-form-select" id="'+ ICON_BODY +'">' +
                    '<div class="'+ TITLE +'" id="'+ TITLE_ID +'">' +
                        '<div class="layui-iconpicker-item">'+
                            '<span class="layui-iconpicker-icon layui-unselect">' +
                                oriIcon +
                            '</span>'+
                            '<i class="layui-edge"></i>' +
                        '</div>'+
                    '</div>' +
                    '<div class="layui-anim layui-anim-upbit" style="">' +
                        '123' +
                    '</div>';
                $(elem).after(selectHtml);
                return a;
            },
            /**
             * 展开/折叠下拉框
             */
            toggleSelect: function () {
                var item = '#' + TITLE_ID + ' .layui-iconpicker-item,#' + TITLE_ID + ' .layui-iconpicker-item .layui-edge';
                a.event('click', item, function (e) {
                    var $icon = $('#' + ICON_BODY);
                    if ($icon.hasClass(selected)) {
                        $icon.removeClass(selected).addClass(unselect);
                    } else {
                        // 隐藏其他picker
                        $('.layui-form-select').removeClass(selected);
                        // 显示当前picker
                        $icon.addClass(selected).removeClass(unselect);
                    }
                    e.stopPropagation();
                });
                return a;
            },
            /**
             * 绘制主体部分
             */
            createBody: function () {
                // 获取数据
                var searchHtml = '';

                if (search) {
                    searchHtml = '<div class="layui-iconpicker-search">' +
                        '<input class="layui-input">' +
                        '<i class="iconfont layui-icon-picker-search"></i>' +
                        '</div>';
                }

                // 组合dom
                var bodyHtml = '<div class="layui-iconpicker-body" id="'+ PICKER_BODY +'">' +
                    searchHtml +
                        '<div class="'+ LIST_BOX +'"></div> '+
                     '</div>';
                $('#' + ICON_BODY).find('.layui-anim').eq(0).html(bodyHtml);
                a.search().createList().check().page();

                return a;
            },
            /**
             * 绘制图标列表
             * @param text 模糊查询关键字
             * @returns {string}
             */
            createList: function (text) {
                var d = data,
                    l = d.length,
                    pageHtml = '',
                    listHtml = $('<div class="layui-iconpicker-list">')//'<div class="layui-iconpicker-list">';

                // 计算分页数据
                var _limit = limit, // 每页显示数量
                    _pages = l % _limit === 0 ? l / _limit : parseInt(l / _limit + 1), // 总计多少页
                    _id = PAGE_ID;

                // 图标列表
                var icons = [];

                for (var i = 0; i < l; i++) {
                    var obj = d[i];

                    // 判断是否模糊查询
                    if (text && obj.indexOf(text) === -1) {
                        continue;
                    }

                    // 是否自定义格子宽度
                    var style = '';
                    if (cellWidth !== null) {
                        style += ' style="width:' + cellWidth + '"';
                    }

                    // 每个图标dom
                    var icon = '<div class="layui-iconpicker-icon-item" title="'+ obj +'" '+ style +'>';
                    if (isFontClass){
                        icon += '<i class="iconfont '+ obj +'"></i>';
                    } else {
                        icon += '<i class="iconfont">'+ obj.replace('amp;', '') +'</i>';
                    }
                    icon += '</div>';

                    icons.push(icon);
                }

                // 查询出图标后再分页
                l = icons.length;
                _pages = l % _limit === 0 ? l / _limit : parseInt(l / _limit + 1);
                for (var i = 0; i < _pages; i++) {
                    // 按limit分块
                    var lm = $('<div class="layui-iconpicker-icon-limit" id="layui-iconpicker-icon-limit-' + tmp + (i+1) +'">');

                    for (var j = i * _limit; j < (i+1) * _limit && j < l; j++) {
                        lm.append(icons[j]);
                    }

                    listHtml.append(lm);
                }

                // 无数据
                if (l === 0) {
                    listHtml.append('<p class="layui-iconpicker-tips">无数据</p>');
                }

                // 判断是否分页
                if (page){
                    $('#' + PICKER_BODY).addClass('layui-iconpicker-body-page');
                    pageHtml = '<div class="layui-iconpicker-page" id="'+ PAGE_ID +'">' +
                        '<div class="layui-iconpicker-page-count">' +
                        '<span id="'+ PAGE_ID +'-current">1</span>/' +
                        '<span id="'+ PAGE_ID +'-pages">'+ _pages +'</span>' +
                        ' (<span id="'+ PAGE_ID +'-length">'+ l +'</span>)' +
                        '</div>' +
                        '<div class="layui-iconpicker-page-operate">' +
                        '<i class="layui-icon" id="'+ PAGE_ID +'-prev" data-index="0" prev>&#xe603;</i> ' +
                        '<i class="layui-icon" id="'+ PAGE_ID +'-next" data-index="2" next>&#xe602;</i> ' +
                        '</div>' +
                        '</div>';
                }


                $('#' + ICON_BODY).find('.layui-anim').find('.' + LIST_BOX).html('').append(listHtml).append(pageHtml);
                return a;
            },
            // 阻止Layui的一些默认事件
            preventEvent: function() {
                var item = '#' + ICON_BODY + ' .layui-anim';
                a.event('click', item, function (e) {
                    e.stopPropagation();
                });
                return a;
            },
            // 分页
            page: function () {
                var icon = '#' + PAGE_ID + ' .layui-iconpicker-page-operate .layui-icon';

                $(icon).unbind('click');
                a.event('click', icon, function (e) {
                   var elem = e.currentTarget,
                       total = parseInt($('#' +PAGE_ID + '-pages').html()),
                       isPrev = $(elem).attr('prev') !== undefined,
                       // 按钮上标的页码
                       index = parseInt($(elem).attr('data-index')),
                       $cur = $('#' +PAGE_ID + '-current'),
                       // 点击时正在显示的页码
                       current = parseInt($cur.html());

                    // 分页数据
                    if (isPrev && current > 1) {
                        current=current-1;
                        $(icon + '[prev]').attr('data-index', current);
                    } else if (!isPrev && current < total){
                        current=current+1;
                        $(icon + '[next]').attr('data-index', current);
                    }
                    $cur.html(current);

                    // 图标数据
                    $('#'+ ICON_BODY + ' .layui-iconpicker-icon-limit').hide();
                    $('#layui-iconpicker-icon-limit-' + tmp + current).show();
                    e.stopPropagation();
                });
                return a;
            },
            /**
             * 搜索
             */
            search: function () {
                var item = '#' + PICKER_BODY + ' .layui-iconpicker-search .layui-input';
                a.event('input propertychange', item, function (e) {
                    var elem = e.target,
                        t = $(elem).val();
                    a.createList(t);
                });
                return a;
            },
            /**
             * 点击选中图标
             */
            check: function () {
                var item = '#' + PICKER_BODY + ' .layui-iconpicker-icon-item';
                a.event('click', item, function (e) {
                    var el = $(e.currentTarget).find('.iconfont'),
                        icon = '';
                    if (isFontClass) {
                        var clsArr = el.attr('class').split(/[\s\n]/),
                            cls = clsArr[1],
                            icon = cls;
                        $('#' + TITLE_ID).find('.layui-iconpicker-item .iconfont').html('').attr('class', clsArr.join(' '));
                    } else {
                        var cls = el.html(),
                            icon = cls;
                        $('#' + TITLE_ID).find('.layui-iconpicker-item .iconfont').html(icon);
                    }

                    $('#' + ICON_BODY).removeClass(selected).addClass(unselect);
                    $(elem).val(icon).attr('value', icon);
                    // 回调
                    if (click) {
                        click({
                            icon: icon
                        });
                    }

                });
                return a;
            },
            // 监听原始input数值改变
            inputListen: function(){
                var el = $(elem);
                a.event('change', elem, function(){
                    var value = el.val();
                })
                // el.change(function(){
                    
                // });
                return a;
            },
            event: function (evt, el, fn) {
                $(BODY).on(evt, el, fn);
            }
        };

        var common = {
            /**
             * 加载样式表
             */
            loadCss: function () {
                var css = '.layui-iconpicker {max-width: 280px;}.layui-iconpicker .layui-anim{display:none;position:absolute;left:0;top:42px;padding:5px 0;z-index:899;min-width:100%;border:1px solid #d2d2d2;max-height:300px;overflow-y:auto;background-color:#fff;border-radius:2px;box-shadow:0 2px 4px rgba(0,0,0,.12);box-sizing:border-box;}.layui-iconpicker-item{border:1px solid #e6e6e6;width:90px;height:38px;border-radius:4px;cursor:pointer;position:absolute;}.layui-iconpicker-icon{border-right:1px solid #e6e6e6;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;display:block;width:60px;height:100%;float:left;text-align:center;background:#fff;transition:all .3s;}.layui-iconpicker-icon i{line-height:38px;font-size:18px;}.layui-iconpicker-item > .layui-edge{left:70px;}.layui-iconpicker-item:hover{border-color:#D2D2D2!important;}.layui-iconpicker-item:hover .layui-iconpicker-icon{border-color:#D2D2D2!important;}.layui-iconpicker.layui-form-selected .layui-anim{display:block;}.layui-iconpicker-body{padding:6px;}.layui-iconpicker .layui-iconpicker-list{background-color:#fff;border:1px solid #ccc;border-radius:4px;}.layui-iconpicker .layui-iconpicker-icon-item{display:inline-block;width:21.1%;line-height:36px;text-align:center;cursor:pointer;vertical-align:top;height:36px;margin:4px;border:1px solid #ddd;border-radius:2px;transition:300ms;}.layui-iconpicker .layui-iconpicker-icon-item i.layui-icon{font-size:17px;}.layui-iconpicker .layui-iconpicker-icon-item:hover{background-color:#eee;border-color:#ccc;-webkit-box-shadow:0 0 2px #aaa,0 0 2px #fff inset;-moz-box-shadow:0 0 2px #aaa,0 0 2px #fff inset;box-shadow:0 0 2px #aaa,0 0 2px #fff inset;text-shadow:0 0 1px #fff;}.layui-iconpicker-search{position:relative;margin:0 0 6px 0;border:1px solid #e6e6e6;border-radius:2px;transition:300ms;}.layui-iconpicker-search:hover{border-color:#D2D2D2!important;}.layui-iconpicker-search .layui-input{cursor:text;display:inline-block;width:86%;border:none;padding-right:0;margin-top:1px;}.layui-iconpicker-search .layui-icon{position:absolute;top:11px;right:4%;}.layui-iconpicker-tips{text-align:center;padding:8px 0;cursor:not-allowed;}.layui-iconpicker-page{margin-top:6px;margin-bottom:-6px;font-size:12px;padding:0 2px;}.layui-iconpicker-page-count{display:inline-block;}.layui-iconpicker-page-operate{display:inline-block;float:right;cursor:default;}.layui-iconpicker-page-operate .layui-icon{font-size:12px;cursor:pointer;}.layui-iconpicker-body-page .layui-iconpicker-icon-limit{display:none;}.layui-iconpicker-body-page .layui-iconpicker-icon-limit:first-child{display:block;}';
                var $style = $('head').find('style[iconpicker]');
                if ($style.length === 0) {
                    $('head').append('<style rel="stylesheet" iconpicker>'+css+'</style>');
                }
            },
            /**
             * 获取数据
             */
            getData: {
                fontClass: function () {
                    var arr = ["layui-icon-picker-sisternode","layui-icon-picker-deleterow","layui-icon-picker-translate","layui-icon-picker-insertrowleft","layui-icon-picker-formatpainter-fill","layui-icon-picker-insertrowright","layui-icon-picker-formatpainter","layui-icon-picker-solit-cells","layui-icon-picker-table1","layui-icon-picker-insertrowabove","layui-icon-picker-insertrowbelow","layui-icon-picker-rotate-right","layui-icon-picker-rotate-left","layui-icon-picker-subnode","layui-icon-picker-merge-cells","layui-icon-picker-deletecolumn","layui-icon-picker-expand","layui-icon-picker-collapse","layui-icon-picker-clear","layui-icon-picker-eye-close","layui-icon-picker-eyeclose-fill","layui-icon-picker-plus","layui-icon-picker-woman","layui-icon-picker-swap-left","layui-icon-picker-swap-right","layui-icon-picker-step-forward","layui-icon-picker-step-backward","layui-icon-picker-login","layui-icon-picker-retweet","layui-icon-picker-search","layui-icon-picker-fast-forward","layui-icon-picker-forward","layui-icon-picker-fast-backward","layui-icon-picker-caret-left","layui-icon-picker-caret-right","layui-icon-picker-caret-up","layui-icon-picker-backward","layui-icon-picker-caret-down","layui-icon-picker-appstoreadd","layui-icon-picker-whatsapp","layui-icon-picker-switchuser","layui-icon-picker-videocameraadd","layui-icon-picker-shortcut-fill","layui-icon-picker-verified","layui-icon-picker-signal-fill","layui-icon-picker-comment","layui-icon-picker-audiostatic","layui-icon-picker-bug","layui-icon-picker-bug-fill","layui-icon-picker-robot-fill","layui-icon-picker-zoomin","layui-icon-picker-robot","layui-icon-picker-audio-fill","layui-icon-picker-audio","layui-icon-picker-apartment","layui-icon-picker-zoomout","layui-icon-picker-zhihu-square-fill","layui-icon-picker-weibo-square-fill","layui-icon-picker-taobao-square-fill","layui-icon-picker-slack-square-fill","layui-icon-picker-sketch-square-fill","layui-icon-picker-twitter-square-fill","layui-icon-picker-reddit-square-fill","layui-icon-picker-QQ-square-fill","layui-icon-picker-linkedin-fill","layui-icon-picker-medium-square-fill","layui-icon-picker-IE-square-fill","layui-icon-picker-instagram-fill","layui-icon-picker-google-square-fill","layui-icon-picker-googleplus-square-f","layui-icon-picker-facebook-fill","layui-icon-picker-dropbox-square-fill","layui-icon-picker-dribbble-square-fill","layui-icon-picker-codepen-square-fill","layui-icon-picker-amazon-square-fill","layui-icon-picker-behance-square-fill","layui-icon-picker-CodeSandbox-square-f","layui-icon-picker-dingtalk-square-fill","layui-icon-picker-alipay-square-fill","layui-icon-picker-reddit-circle-fill","layui-icon-picker-zhihu-circle-fill","layui-icon-picker-weibo-circle-fill","layui-icon-picker-taobao-circle-fill","layui-icon-picker-twitter-circle-fill","layui-icon-picker-slack-circle-fill","layui-icon-picker-sketch-circle-fill","layui-icon-picker-dingtalk-circle-fill","layui-icon-picker-google-circle-fill","layui-icon-picker-IE-circle-fill","layui-icon-picker-QQ-circle-fill","layui-icon-picker-medium-circle-fill","layui-icon-picker-googleplus-circle-f","layui-icon-picker-dribbble-circle-fill","layui-icon-picker-github-fill","layui-icon-picker-dropbox-circle-fill","layui-icon-picker-CodeSandbox-circle-f","layui-icon-picker-codepen-circle-fill","layui-icon-picker-amazon-circle-fill","layui-icon-picker-behance-circle-fill","layui-icon-picker-aliwangwang-fill","layui-icon-picker-alipay-circle-fill","layui-icon-picker-chrome-fill","layui-icon-picker-wechat-fill","layui-icon-picker-yahoo-fill","layui-icon-picker-Youtube-fill","layui-icon-picker-yuque-fill","layui-icon-picker-weibo","layui-icon-picker-skype-fill","layui-icon-picker-twitter","layui-icon-picker-QQ","layui-icon-picker-windows-fill","layui-icon-picker-HTML-fill","layui-icon-picker-apple-fill","layui-icon-picker-android-fill","layui-icon-picker-dingtalk","layui-icon-picker-dropbox","layui-icon-picker-Gitlab-fill","layui-icon-picker-Youtube","layui-icon-picker-yuque","layui-icon-picker-windows","layui-icon-picker-reddit","layui-icon-picker-instagram","layui-icon-picker-dribbble","layui-icon-picker-Gitlab","layui-icon-picker-sketch","layui-icon-picker-android","layui-icon-picker-apple","layui-icon-picker-aliwangwang","layui-icon-picker-codepen","layui-icon-picker-chrome","layui-icon-picker-CodeSandbox","layui-icon-picker-skype","layui-icon-picker-facebook","layui-icon-picker-yahoo","layui-icon-picker-linkedin","layui-icon-picker-HTML","layui-icon-picker-zhihu","layui-icon-picker-taobao","layui-icon-picker-alipay","layui-icon-picker-slack","layui-icon-picker-amazon","layui-icon-picker-IE","layui-icon-picker-google","layui-icon-picker-medium","layui-icon-picker-googleplus","layui-icon-picker-behance","layui-icon-picker-ant-cloud","layui-icon-picker-antdesign","layui-icon-picker-alibabacloud","layui-icon-picker-alibaba","layui-icon-picker-sliders-fill","layui-icon-picker-boxplot-fill","layui-icon-picker-build-fill","layui-icon-picker-golden-fill","layui-icon-picker-USB-fill","layui-icon-picker-setting-fill","layui-icon-picker-shop-fill","layui-icon-picker-printer-fill","layui-icon-picker-car-fill","layui-icon-picker-mail-fill","layui-icon-picker-crown-fill","layui-icon-picker-error-fill","layui-icon-picker-camera-fill","layui-icon-picker-bank-fill","layui-icon-picker-tags-fill","layui-icon-picker-wrench-fill","layui-icon-picker-tag-fill","layui-icon-picker-thunderbolt-fill","layui-icon-picker-rocket-fill","layui-icon-picker-pushpin-fill","layui-icon-picker-edit-fill","layui-icon-picker-phone-fill","layui-icon-picker-highlight-fill","layui-icon-picker-api-fill","layui-icon-picker-alert-fill","layui-icon-picker-unlock-fill","layui-icon-picker-star-fill","layui-icon-picker-unlike-fill","layui-icon-picker-lock-fill","layui-icon-picker-like-fill","layui-icon-picker-eye-fill","layui-icon-picker-experiment-fill","layui-icon-picker-customerservice-fill","layui-icon-picker-cloud-fill","layui-icon-picker-location-fill","layui-icon-picker-trophy-fill","layui-icon-picker-home-fill","layui-icon-picker-hourglass-fill","layui-icon-picker-gift-fill","layui-icon-picker-funnelplot-fill","layui-icon-picker-fire-fill","layui-icon-picker-filter-fill","layui-icon-picker-bell-fill","layui-icon-picker-bulb-fill","layui-icon-picker-sound-fill","layui-icon-picker-video-fill","layui-icon-picker-skin-fill","layui-icon-picker-shopping-fill","layui-icon-picker-rest-fill","layui-icon-picker-medicinebox-fill","layui-icon-picker-moneycollect-fill","layui-icon-picker-flag-fill","layui-icon-picker-notification-fill","layui-icon-picker-delete-fill","layui-icon-picker-contacts-fill","layui-icon-picker-read-fill","layui-icon-picker-fund-fill","layui-icon-picker-creditcard-fill","layui-icon-picker-idcard-fill","layui-icon-picker-image-fill","layui-icon-picker-calendar-check-fill","layui-icon-picker-sever-fill","layui-icon-picker-container-fill","layui-icon-picker-database-fill","layui-icon-picker-folder-open-fill","layui-icon-picker-folder-fill","layui-icon-picker-folder-add-fill","layui-icon-picker-reconciliation-fill","layui-icon-picker-batchfolding-fill","layui-icon-picker-snippets-fill","layui-icon-picker-file-copy-fill","layui-icon-picker-diff-fill","layui-icon-picker-file-image-fill","layui-icon-picker-file-pdf-fill","layui-icon-picker-file-zip-fill","layui-icon-picker-file-word-fill","layui-icon-picker-file-unknown-fill","layui-icon-picker-file-ppt-fill","layui-icon-picker-file-text-fill","layui-icon-picker-file-markdown-fill","layui-icon-picker-file-excel-fill","layui-icon-picker-file-fill","layui-icon-picker-file-add-fill","layui-icon-picker-file-exclamation-fil","layui-icon-picker-securityscan-fill","layui-icon-picker-insurance-fill","layui-icon-picker-propertysafety-fill","layui-icon-picker-safetycertificate-f","layui-icon-picker-redenvelope-fill","layui-icon-picker-book-fill","layui-icon-picker-tablet-fill","layui-icon-picker-mobile-fill","layui-icon-picker-appstore-fill","layui-icon-picker-layout-fill","layui-icon-picker-control-fill","layui-icon-picker-wallet-fill","layui-icon-picker-save-fill","layui-icon-picker-detail-fill","layui-icon-picker-project-fill","layui-icon-picker-interation-fill","layui-icon-picker-calculator-fill","layui-icon-picker-calendar-fill","layui-icon-picker-carryout-fill","layui-icon-picker-accountbook-fill","layui-icon-picker-plus-square-fill","layui-icon-picker-right-square-fill","layui-icon-picker-up-square-fill","layui-icon-picker-play-square-fill","layui-icon-picker-left-square-fill","layui-icon-picker-codelibrary-fill","layui-icon-picker-close-square-fill","layui-icon-picker-minus-square-fill","layui-icon-picker-down-square-fill","layui-icon-picker-check-square-fill","layui-icon-picker-message-fill","layui-icon-picker-dashboard-fill","layui-icon-picker-piechart-circle-fil","layui-icon-picker-heart-fill","layui-icon-picker-YUAN-circle-fill","layui-icon-picker-trademark-circle-fil","layui-icon-picker-time-circle-fill","layui-icon-picker-warning-circle-fill","layui-icon-picker-stop-fill","layui-icon-picker-smile-fill","layui-icon-picker-Pound-circle-fill","layui-icon-picker-play-circle-fill","layui-icon-picker-meh-fill","layui-icon-picker-poweroff-circle-fill","layui-icon-picker-Dollar-circle-fill","layui-icon-picker-compass-fill","layui-icon-picker-CI-circle-fill","layui-icon-picker-copyright-circle-fil","layui-icon-picker-frown-fill","layui-icon-picker-EURO-circle-fill","layui-icon-picker-question-circle-fill","layui-icon-picker-plus-circle-fill","layui-icon-picker-right-circle-fill","layui-icon-picker-up-circle-fill","layui-icon-picker-info-circle-fill","layui-icon-picker-close-circle-fill","layui-icon-picker-minus-circle-fill","layui-icon-picker-down-circle-fill","layui-icon-picker-left-circle-fill","layui-icon-picker-check-circle-fill","layui-icon-picker-thunderbolt","layui-icon-picker-fire","layui-icon-picker-stop","layui-icon-picker-gift","layui-icon-picker-desktop","layui-icon-picker-drag","layui-icon-picker-crown","layui-icon-picker-bg-colors","layui-icon-picker-pause","layui-icon-picker-small-dash","layui-icon-picker-rollback","layui-icon-picker-question","layui-icon-picker-minus","layui-icon-picker-line","layui-icon-picker-enter","layui-icon-picker-close","layui-icon-picker-dash","layui-icon-picker-ellipsis","layui-icon-picker-check","layui-icon-picker-column-width","layui-icon-picker-code","layui-icon-picker-italic","layui-icon-picker-number","layui-icon-picker-underline","layui-icon-picker-strikethrough","layui-icon-picker-line-height","layui-icon-picker-infomation","layui-icon-picker-font-size","layui-icon-picker-exclaimination","layui-icon-picker-font-colors","layui-icon-picker-bold","layui-icon-picker-pic-left","layui-icon-picker-pic-right","layui-icon-picker-pic-center","layui-icon-picker-align-left","layui-icon-picker-align-center","layui-icon-picker-align-right","layui-icon-picker-orderedlist","layui-icon-picker-unorderedlist","layui-icon-picker-menu","layui-icon-picker-outdent","layui-icon-picker-indent","layui-icon-picker-rise","layui-icon-picker-stock","layui-icon-picker-swap","layui-icon-picker-fall","layui-icon-picker-sort-ascending","layui-icon-picker-sort-descending","layui-icon-picker-download","layui-icon-picker-vertical-align-top","layui-icon-picker-totop","layui-icon-picker-vertical-align-middl","layui-icon-picker-vertical-align-botto","layui-icon-picker-colum-height","layui-icon-picker-upload","layui-icon-picker-arrowdown","layui-icon-picker-arrowleft","layui-icon-picker-arrowup","layui-icon-picker-arrowright","layui-icon-picker-doubleright","layui-icon-picker-doubleleft","layui-icon-picker-fullscreen-exit","layui-icon-picker-fullscreen","layui-icon-picker-down","layui-icon-picker-up","layui-icon-picker-left","layui-icon-picker-right","layui-icon-picker-verticalleft","layui-icon-picker-verticalright","layui-icon-picker-arrawsalt","layui-icon-picker-shrink","layui-icon-picker-fork","layui-icon-picker-branches","layui-icon-picker-share","layui-icon-picker-mr","layui-icon-picker-scissor","layui-icon-picker-tags","layui-icon-picker-wrench","layui-icon-picker-tag","layui-icon-picker-shake","layui-icon-picker-phone","layui-icon-picker-pushpin","layui-icon-picker-percentage","layui-icon-picker-man","layui-icon-picker-link","layui-icon-picker-monitor","layui-icon-picker-highlight","layui-icon-picker-disconnect","layui-icon-picker-api","layui-icon-picker-key","layui-icon-picker-edit","layui-icon-picker-attachment","layui-icon-picker-wifi","layui-icon-picker-heatmap","layui-icon-picker-gold","layui-icon-picker-star","layui-icon-picker-error","layui-icon-picker-block","layui-icon-picker-heart","layui-icon-picker-creditcard","layui-icon-picker-idcard","layui-icon-picker-table","layui-icon-picker-mail","layui-icon-picker-image","layui-icon-picker-fund","layui-icon-picker-qrcode","layui-icon-picker-radarchart","layui-icon-picker-sound","layui-icon-picker-notification","layui-icon-picker-video","layui-icon-picker-cloud-sync","layui-icon-picker-cloud-download","layui-icon-picker-cloud","layui-icon-picker-cloud-upload","layui-icon-picker-cloud-server","layui-icon-picker-read","layui-icon-picker-printer","layui-icon-picker-car","layui-icon-picker-gateway","layui-icon-picker-cluster","layui-icon-picker-camera","layui-icon-picker-barcode","layui-icon-picker-laptop","layui-icon-picker-sliders","layui-icon-picker-build","layui-icon-picker-boxplot","layui-icon-picker-select","layui-icon-picker-scan","layui-icon-picker-calendar","layui-icon-picker-calendar-check","layui-icon-picker-carryout","layui-icon-picker-contacts","layui-icon-picker-accountbook","layui-icon-picker-deploymentunit","layui-icon-picker-folder-add","layui-icon-picker-folder-open","layui-icon-picker-folder","layui-icon-picker-shopping","layui-icon-picker-rocket","layui-icon-picker-shop","layui-icon-picker-medicinebox","layui-icon-picker-moneycollect","layui-icon-picker-flag","layui-icon-picker-customerservice","layui-icon-picker-lock","layui-icon-picker-unlock","layui-icon-picker-unlike","layui-icon-picker-like","layui-icon-picker-funnelplot","layui-icon-picker-filter","layui-icon-picker-bank","layui-icon-picker-home","layui-icon-picker-skin","layui-icon-picker-USB","layui-icon-picker-rest","layui-icon-picker-trophy","layui-icon-picker-bell","layui-icon-picker-experiment","layui-icon-picker-bulb","layui-icon-picker-hourglass","layui-icon-picker-delete","layui-icon-picker-alert","layui-icon-picker-insurance","layui-icon-picker-safetycertificate","layui-icon-picker-propertysafety","layui-icon-picker-securityscan","layui-icon-picker-Batchfolding","layui-icon-picker-diff","layui-icon-picker-audit","layui-icon-picker-snippets","layui-icon-picker-file-copy","layui-icon-picker-file-text","layui-icon-picker-file-zip","layui-icon-picker-file","layui-icon-picker-file-word","layui-icon-picker-file-ppt","layui-icon-picker-file-unknown","layui-icon-picker-file-markdown","layui-icon-picker-file-image","layui-icon-picker-file-pdf","layui-icon-picker-file-exclamation","layui-icon-picker-file-excel","layui-icon-picker-file-add","layui-icon-picker-fileprotect","layui-icon-picker-solution","layui-icon-picker-filesearch","layui-icon-picker-filesync","layui-icon-picker-file-exception","layui-icon-picker-reconciliation","layui-icon-picker-filedone","layui-icon-picker-book","layui-icon-picker-redenvelope","layui-icon-picker-tablet","layui-icon-picker-mobile","layui-icon-picker-sever","layui-icon-picker-database","layui-icon-picker-container","layui-icon-picker-pointmap","layui-icon-picker-barchart","layui-icon-picker-linechart","layui-icon-picker-areachart","layui-icon-picker-team","layui-icon-picker-user","layui-icon-picker-addteam","layui-icon-picker-deleteuser","layui-icon-picker-deleteteam","layui-icon-picker-adduser","layui-icon-picker-radius-setting","layui-icon-picker-radius-upright","layui-icon-picker-radius-upleft","layui-icon-picker-radius-bottomright","layui-icon-picker-radius-bottomleft","layui-icon-picker-border-horizontal","layui-icon-picker-border-verticle","layui-icon-picker-border-inner","layui-icon-picker-border-right","layui-icon-picker-border-left","layui-icon-picker-border-bottom","layui-icon-picker-border-top","layui-icon-picker-border-outer","layui-icon-picker-border","layui-icon-picker-check-square","layui-icon-picker-interation","layui-icon-picker-calculator","layui-icon-picker-up-square","layui-icon-picker-wallet","layui-icon-picker-project","layui-icon-picker-right-square","layui-icon-picker-plus-square","layui-icon-picker-minus-square","layui-icon-picker-detail","layui-icon-picker-codelibrary","layui-icon-picker-control","layui-icon-picker-play-square","layui-icon-picker-left-square","layui-icon-picker-layout","layui-icon-picker-down-square","layui-icon-picker-close-square","layui-icon-picker-appstore","layui-icon-picker-Import","layui-icon-picker-save","layui-icon-picker-export","layui-icon-picker-edit-square","layui-icon-picker-location","layui-icon-picker-eye","layui-icon-picker-setting","layui-icon-picker-piechart","layui-icon-picker-logout","layui-icon-picker-poweroff","layui-icon-picker-issuesclose","layui-icon-picker-dashboard","layui-icon-picker-message","layui-icon-picker-reloadtime","layui-icon-picker-reload","layui-icon-picker-redo","layui-icon-picker-undo","layui-icon-picker-transaction","layui-icon-picker-sync","layui-icon-picker-warning-circle","layui-icon-picker-up-circle","layui-icon-picker-YUAN","layui-icon-picker-earth","layui-icon-picker-timeout","layui-icon-picker-time-circle","layui-icon-picker-trademark","layui-icon-picker-smile","layui-icon-picker-right-circle","layui-icon-picker-Pound","layui-icon-picker-question-circle","layui-icon-picker-play-circle","layui-icon-picker-plus-circle","layui-icon-picker-meh","layui-icon-picker-minus-circle","layui-icon-picker-copyright","layui-icon-picker-EURO","layui-icon-picker-down-circle","layui-icon-picker-left-circle","layui-icon-picker-info-circle","layui-icon-picker-frown","layui-icon-picker-close-circle","layui-icon-picker-compass","layui-icon-picker-Dollar","layui-icon-picker-CI","layui-icon-picker-check-circle"];
                    return arr;
                },
                unicode: function () {
                    return ["&amp;#xe6c9;","&amp;#xe67b;","&amp;#xe67a;","&amp;#xe678;","&amp;#xe679;","&amp;#xe677;","&amp;#xe676;","&amp;#xe675;","&amp;#xe673;","&amp;#xe66f;","&amp;#xe9aa;","&amp;#xe672;","&amp;#xe66b;","&amp;#xe668;","&amp;#xe6b1;","&amp;#xe702;","&amp;#xe66e;","&amp;#xe68e;","&amp;#xe674;","&amp;#xe669;","&amp;#xe666;","&amp;#xe66c;","&amp;#xe66a;","&amp;#xe667;","&amp;#xe7ae;","&amp;#xe665;","&amp;#xe664;","&amp;#xe716;","&amp;#xe656;","&amp;#xe653;","&amp;#xe663;","&amp;#xe6c6;","&amp;#xe6c5;","&amp;#xe662;","&amp;#xe661;","&amp;#xe660;","&amp;#xe65d;","&amp;#xe65f;","&amp;#xe671;","&amp;#xe65e;","&amp;#xe659;","&amp;#xe735;","&amp;#xe756;","&amp;#xe65c;","&amp;#xe715;","&amp;#xe705;","&amp;#xe6b2;","&amp;#xe6af;","&amp;#xe69c;","&amp;#xe698;","&amp;#xe657;","&amp;#xe65b;","&amp;#xe65a;","&amp;#xe681;","&amp;#xe67c;","&amp;#xe601;","&amp;#xe857;","&amp;#xe655;","&amp;#xe770;","&amp;#xe670;","&amp;#xe63d;","&amp;#xe63e;","&amp;#xe654;","&amp;#xe652;","&amp;#xe651;","&amp;#xe6fc;","&amp;#xe6ed;","&amp;#xe688;","&amp;#xe645;","&amp;#xe64f;","&amp;#xe64e;","&amp;#xe64b;","&amp;#xe62b;","&amp;#xe64d;","&amp;#xe64a;","&amp;#xe64c;","&amp;#xe650;","&amp;#xe649;","&amp;#xe648;","&amp;#xe647;","&amp;#xe646;","&amp;#xe644;","&amp;#xe62a;","&amp;#xe643;","&amp;#xe63f;","&amp;#xe642;","&amp;#xe641;","&amp;#xe640;","&amp;#xe63c;","&amp;#xe63b;","&amp;#xe63a;","&amp;#xe639;","&amp;#xe638;","&amp;#xe637;","&amp;#xe636;","&amp;#xe635;","&amp;#xe634;","&amp;#xe633;","&amp;#xe632;","&amp;#xe631;","&amp;#xe630;","&amp;#xe62f;","&amp;#xe62e;","&amp;#xe62d;","&amp;#xe62c;","&amp;#xe629;","&amp;#xe628;","&amp;#xe625;","&amp;#xe623;","&amp;#xe621;","&amp;#xe620;","&amp;#xe61f;","&amp;#xe61c;","&amp;#xe60b;","&amp;#xe619;","&amp;#xe61a;","&amp;#xe603;","&amp;#xe602;","&amp;#xe617;","&amp;#xe615;","&amp;#xe614;","&amp;#xe613;","&amp;#xe612;","&amp;#xe611;","&amp;#xe60f;","&amp;#xe60e;","&amp;#xe60d;","&amp;#xe60c;","&amp;#xe60a;","&amp;#xe609;","&amp;#xe605;","&amp;#xe607;","&amp;#xe606;","&amp;#xe604;","&amp;#xe600;","&amp;#xe658;","&amp;#x1007;","&amp;#x1006;","&amp;#x1005;","&amp;#xe608;"];
                }
            }
        };

        a.init();
        return new IconPicker();
    };

    /**
     * 选中图标
     * @param filter lay-filter
     * @param iconName 图标名称，自动识别fontClass/unicode
     */
    IconPicker.prototype.checkIcon = function (filter, iconName){
        var el = $('*[lay-filter='+ filter +']'),
            p = el.next().find('.layui-iconpicker-item .iconfont'),
            c = iconName;

        if (c.indexOf('#xe') > 0){
            p.html(c);
        } else {
            p.html('').attr('class', 'iconfont ' + c);
        }
        el.attr('value', c).val(c);
    };

    var iconPicker = new IconPicker();
    exports(_MOD, iconPicker);
});