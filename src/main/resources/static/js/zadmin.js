 layui.use(['element', 'layer'], function () {
    let $ = jQuery = layui.jquery;
    let element = layui.element;
    let layer = layui.layer;

    let rememberTab = true;
    let tabList = [];

    // 标签卡公共方法.
    let activeTab = {
        tabAdd: function(id, title, url) {
            //新增一个Tab项
            element.tabAdd("lay-tab", {
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
        },
        tabChange: function(id) {
            //切换到指定Tab项
            element.tabChange("lay-tab", id);
        },
        tabDelete: function(id) {
            element.tabDelete("lay-tab", id);
        },
        tabDeleteAll: function(ids) {
            $.each(ids, function (i, item) {
                element.tabDelete("lay-tab", item);
            })
        }
    };

    // 获取页面上所有的标有 lay-event 的元素, 点击时对应相应的事件.
    $(document).on("click", "*[lay-event]", function () {
        let event = $(this).attr("lay-event");
        typeof active[event] === "function" && active[event].apply(this);
    });

    // 框架事件
    let active = {
        // 导航收起, 展开动作
        flexible: function() {
            let elem = $(".layui-layout-admin");
            let flag = elem.hasClass("admin-nav-mini");
            if (flag) {
                elem.removeClass("admin-nav-mini");
            } else {
                elem.addClass("admin-nav-mini");
            }
        },
        refresh: function() {
            // 硬核刷新法 ~~~
            let iframes = $(".layui-layout-admin .layui-tab .layui-tab-item.layui-show .layui-iframe");
            iframes[0].src = iframes[0].src;
        },
        clear: function() {
            layer.confirm("确认清空标签缓存吗?", {icon: 3, title:"提示"}, function(index){
                sessionStorage.setItem("tabs", null);
                sessionStorage.setItem("currentTabId", "main");
                layer.close(index);
                layer.msg("清理成功");
            });
        }
    };

    element.on('nav(test)', function(elem) {
        // 如果点击的目录还有子目录就不做任何操作.
        if ($(elem).find("span.layui-nav-more").length === 0) {
            let obj = $(this);

            let title = obj.find("cite").html();
            let id = obj.attr("lay-id");
            let url = obj.attr("lay-url");

            let tabs = $(".layui-tab-title li[lay-id]");

            if (tabs.length <= 0) {
                activeTab.tabAdd(id, title, url);
            } else {
                // 如已存在打开的 tab 页, 则直接切换过去.
                let isOpen = false;

                $.each($(tabs), function () {
                    if ($(this).attr("lay-id") === id) {
                        isOpen = true;
                    }
                });

                if (isOpen === false) {
                    activeTab.tabAdd(id, title, url);
                }
            }
            activeTab.tabChange(id);
        }
    });

    /**
     * 构建面包屑的 HTML
     * @param {object}  obj   当前标签的
     * @param {boolean} flag  是否最底层的导航
     * @param {Text}    html  存放面包屑 HTML的变量
     */
    function buildBreadcrumb(obj, flag, html) {
        if (typeof obj === "undefined") {
            obj = $(".layui-side-scroll .layui-this a[lay-url]");
        }

        if (typeof flag === "undefined") {
            flag = true;
        }

        let currentBreadcurmbHTML;
        let currentNavText = $(obj).first().find("cite").html();

        // falg 为 true 表示最底级的导航.
        if (flag) {
            currentBreadcurmbHTML =  "<a><cite>" + currentNavText + "</cite></a>";
        } else {
            currentBreadcurmbHTML =  "<a href='#'>" + currentNavText + "</a><span lay-separator=''>/</span>";
        }
        html = currentBreadcurmbHTML + html;

        let parent = $(obj).parents("dd.layui-nav-itemed");

        if (parent.length === 0) {
            parent = $(obj).parents("li.layui-nav-itemed");
        }

        // 递归查找父导航, 直到没有父导航.
        if (parent.length !== 0) {
            return buildBreadcrumb(parent, false, html);
        }
        $("body div.layui-layout-admin div.layui-header ul span.layui-breadcrumb").html(html);
    }


    //绑定右键菜单
    function customRightClick() {
        let rightMenu = $(".rightmenu");
        $(document).click(function () {
            rightMenu.hide();
        });
        //桌面点击右击
        $(document).on("contextmenu", ".layui-tab-title li", function (e) {
            rightMenu.show();
            let popupmenu = $(".rightmenu");
            rightMenu.find("li").attr("data-id", $(this).attr("lay-id"));
            let l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu
                .width()) : e.clientX;
            let t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu
                .height()) : e.clientY;
            popupmenu.css({
                left: l,
                top: t
            }).show();
            return false;
        });
    }

    customRightClick();

    // 点击右键菜单的功能时.
    $(".rightmenu li").click(function () {
        let event = $(this).attr("data-type");
        let currentRightClickId = $(this).attr("data-id");

        let tabtitle = $(".layui-tab-title li");

        if (event === "closethis") {
            if (currentRightClickId !== "home") {
                activeTab.tabDelete(currentRightClickId);
            }
        } else if (event === "closeall") {
            let ids = [];
            $.each(tabtitle, function (i) {
                if (i !== 0) {
                    ids[i] = $(this).attr("lay-id");
                }
            });
            activeTab.tabDeleteAll(ids);
        } else if (event === "closeothers") {
            let ids = [];
            $.each(tabtitle, function (i) {
                let id = $(this).attr("lay-id");
                if (i !== 0 && id !== currentRightClickId) {
                    ids[i] = id;
                }
            });
            activeTab.tabDeleteAll(ids);
        } else if (event === "closeleft") {
            let ids = [];
            $.each(tabtitle, function (i) {
                let id = $(this).attr("lay-id");
                if (i !== 0) {
                    if (id === currentRightClickId) {
                        return false;
                    }
                    ids[i] = id;
                }
            });
            activeTab.tabDeleteAll(ids);
        } else if (event === "closeright") {
            let flag = false;
            let ids = [];
            $.each(tabtitle, function (i) {
                let id = $(this).attr("lay-id");

                if (id === currentRightClickId) {
                    flag = true;
                    return true;
                }

                if (i !== 0 && flag) {
                    ids[i] = id;
                }
            });
            activeTab.tabDeleteAll(ids);
        }

        $(".rightmenu").hide();
    });


    // 点击标签卡定位菜单
    element.on("tab(lay-tab)", function(elem) {
        let filter = "test"; //菜单
        let id = $(this).attr("lay-id");
        let navElem = $(".layui-nav[lay-filter='" + filter + "']"); //菜单导航元素
        //移除所有选中、获取当前tab选择导航、标注选中样式、展开条目
        navElem.find("li, dd").removeClass("layui-this").find("a[lay-id='" + id + "']").parent().first().addClass("layui-this").parents("li,dd").addClass("layui-nav-itemed");

        buildBreadcrumb();
        if (rememberTab) {
            sessionStorage.setItem("currentTabId", id);
        }
    });

    element.on("tabDelete(lay-tab)", function(elem){
        tabList.splice(elem.index - 1, 1);
        if (rememberTab) {
            sessionStorage.setItem("tabs", JSON.stringify(tabList));
        }
    });


    // 页面加载完后, 打开存储的标签卡.
    $(document).ready(function() {
        if (rememberTab) {
            let tabs = JSON.parse(sessionStorage.getItem("tabs"));
            for (let i = 0; tabs != null && i < tabs.length; i++) {
                activeTab.tabAdd(tabs[i].id, tabs[i].title, tabs[i].url);
            }
            activeTab.tabChange(sessionStorage.getItem("currentTabId"));
        }
    });
});

