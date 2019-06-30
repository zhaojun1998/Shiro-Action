layui.define(["element", "layer"], function (exports) {
    var $ = jQuery = layui.jquery;
    var element = layui.element;
    var layer = layui.layer;

    var tabLayFilter = "lay-tab";
    var navLayFilter = "lay-nav";
    var rememberTab = true;
    var tabList = [];
    var tabsSelector = ".layui-pagetabs .layui-tab-title li[lay-id]";

    var zadmin = {
        tabAdd: function (o) {
            var id = o.id;
            var url = o.url;
            var title = o.title;
            if (!this.tabExist(id)) {
                // 新增一个Tab项
                element.tabAdd(tabLayFilter, {
                    id: id,
                    title: title,
                    content: "<iframe data-frame-id='" + id + "' class='layui-iframe' src='" + url + "'></iframe>"
                });
                if (rememberTab) {
                    tabList.push({
                        id: id,
                        title: title,
                        url: url
                    });
                    sessionStorage.setItem("tabs", JSON.stringify(tabList));
                }
            }
            element.tabChange(tabLayFilter, id);
        },
        tabChange: function(id) {
            element.tabChange(tabLayFilter, id);
        },
        tabDelete: function (id) {
            element.tabDelete(tabLayFilter, id);
        },
        tabExist: function(id) {
            var isExist = false;
            $.each($(tabsSelector), function () {
                if ($(this).attr("lay-id") === id) {
                    isExist = true;
                    return false;
                }
            });
            return isExist;
        },
        flexible: function () {
            var elem = $(".layui-layout-admin");
            var flag = elem.hasClass("admin-nav-mini");
            if (flag) {
                elem.removeClass("admin-nav-mini");
            } else {
                elem.addClass("admin-nav-mini");
            }
        },
        refresh: function () {
            // 硬核刷新法 ~~~
            var iframes = $(".layui-layout-admin .layui-tab .layui-tab-item.layui-show .layui-iframe");
            iframes[0].src = iframes[0].src;
        },
        github: function() {
            window.open('https://github.com/zhaojun1998/Shiro-Action');
        },
        clear: function () {
            layer.confirm("确认清空标签缓存吗?", {icon: 3, title:"提示"}, function(index){
                sessionStorage.setItem("tabs", null);
                sessionStorage.setItem("currentTabId", "main");
                layer.close(index);
                layer.msg("清理成功");
            });
        },
        buildBreadcrumb: function (obj, isLast, html) {

            obj = obj ? obj : $(".layui-side-scroll .layui-this a[lay-url]");
            html = html ? html : "";

            if (typeof isLast === "undefined") {
                isLast = true;
            }

            var currentBreadcurmbHTML;
            var currentNavText = $(obj).first().find("cite").html();

            // falg 为 true 表示最底级的导航.
            if (isLast) {
                currentBreadcurmbHTML =  "<a><cite>" + currentNavText + "</cite></a>";
            } else {
                currentBreadcurmbHTML =  "<a href='#'>" + currentNavText + "</a><span lay-separator=''>/</span>";
            }
            html = currentBreadcurmbHTML + html;

            var parent = $(obj).parents("dd.layui-nav-itemed");

            if (parent.length === 0) {
                parent = $(obj).parents("li.layui-nav-itemed");
            }

            // 递归查找父导航, 直到没有父导航.
            if (parent.length !== 0) {
                return this.buildBreadcrumb(parent, false, html);
            }
            $("body div.layui-layout-admin div.layui-header ul span.layui-breadcrumb").html(html);
        },
        removeLoading: function () {
            $(".page-loading").hide();
        }
    };

    // 获取页面上所有的标有 lay-event 的元素, 点击时对应相应的事件.
    $(document).on("click", "*[lay-event]", function () {
        var event = $(this).attr("lay-event");
        typeof zadmin[event] === "function" && zadmin[event].apply(this);
    });

    element.on("nav(" + navLayFilter + ")", function(elem) {
        // 如果点击的目录还有子目录就不做任何操作.
        if ($(elem).find("span.layui-nav-more").length === 0) {
            var obj = $(this);

            var title = obj.find("cite").html();
            var id = obj.attr("lay-id");
            var url = obj.attr("lay-url");

            var tabs = $(".layui-pagetabs .layui-tab-title li[lay-id]");

            zadmin.tabAdd({
                id: id,
                title: title,
                url: url
            });
        }
    });

    // 点击标签卡定位菜单
    element.on("tab(" + tabLayFilter +")", function(elem) {
        var id = $(this).attr("lay-id");
        var navElem = $(".layui-nav[lay-filter='" + navLayFilter + "']"); //菜单导航元素
        //移除所有选中、获取当前tab选择导航、标注选中样式、展开条目
        navElem.find("li, dd").removeClass("layui-this").find("a[lay-id='" + id + "']").parent().first().addClass("layui-this").parents("li,dd").addClass("layui-nav-itemed");

        zadmin.buildBreadcrumb();
        if (rememberTab) {
            sessionStorage.setItem("currentTabId", id);
        }
    });

    // 监听 tab 删除事件.
    element.on("tabDelete(" + tabLayFilter + ")", function(elem){
        tabList.splice(elem.index - 1, 1);
        if (rememberTab) {
            sessionStorage.setItem("tabs", JSON.stringify(tabList));
        }
    });


    // layui 导航互斥效果 (手风琴).
    // $("div.layui-side-scroll .layui-nav-item").click(function() {
    //     var flag = false;
    //     if ($(this).hasClass("layui-nav-itemed")) {
    //         flag = true;
    //     }
    //     $("div.layui-side-scroll .layui-nav-item").removeClass("layui-nav-itemed").removeClass("layui-this");
    //     if($(this).has("dl").length){//如果有子菜单，显示下拉样式
    //         if (flag) {
    //             $(this).addClass("layui-nav-itemed");
    //         }
    //     }else{//如果没有子菜单，显示菜单项样式
    //         $(this).addClass("layui-this");
    //     }
    // });

    // 页面加载完后, 打开存储的标签卡.
    $(document).ready(function() {
        if (rememberTab) {
            var tabs = JSON.parse(sessionStorage.getItem("tabs"));
            var currentTabId = sessionStorage.getItem("currentTabId");
            for (var i = 0; tabs != null && i < tabs.length; i++) {
                zadmin.tabAdd({
                    id: tabs[i].id,
                    title: tabs[i].title,
                    url: tabs[i].url
                });
            }
            zadmin.tabChange(currentTabId);
        }
    });

    // 移动端模式下, 点击遮罩收缩导航.
    $(".site-mobile-shade").click(function () {
        zadmin.flexible();
    });

    exports('zadmin', zadmin);
});