layui.config({
    base: "/lib/layui/extend/",
}).use(["zadmin", "tabRightMenu"], function () {
    var zadmin = layui.zadmin;
    var tabRightMenu = layui.tabRightMenu;

    // 渲染 tab 右键菜单.
    tabRightMenu.render({
        filter: "lay-tab",
        pintabIDs: ["home"],
        width: 110,
    });

    setTimeout(function() {
        zadmin.removeLoading();
    }, 500);
});

/**
 * 显示错误信息
 * @param msg： 错误信息
 */
function showError(msg) {
    layer.msg(msg, {icon: 2});
}

/**
 * 处理 ajax 请求结果
 * @param result： ajax 返回的结果
 * @param fn： 成功的处理函数 ( 传入data: fn(result.data) )
 */
function handlerResult(result, fn) {
    // 成功执行操作，失败提示原因
    if (result.code === 0) {
        fn(result.data);
    } else {
        showError(result.msg);
    }
}

function getWeekList() {
    var today = new Date();
    var data = [];
    for (var i = 0; i < 7; i++) {
        var temp = new Date(today.getTime() - 1000 * 60 * 60 * 24 * i);
        data[6 - i] = dateFormat("yyyy-MM-dd", temp);
    }
    return data;
}

function dateFormat(fmt, date) {
    var o = {
        "M+": date.getMonth() + 1,                      //月份
        "d+": date.getDate(),                           //日
        "h+": date.getHours(),                          //小时
        "m+": date.getMinutes(),                        //分
        "s+": date.getSeconds(),                        //秒
        "q+": Math.floor((date.getMonth() + 3) / 3),    //季度
        "S": date.getMilliseconds()                     //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 公共打开父页面 tab 方法.
 * @param title
 * @param url
 */
function openTab(title, url) {
    parent.layui.zadmin.tabAdd({
        id: url,
        title: title,
        url: url
    });
}

/**
 * 全局 AJAX error 处理事件.
 */
$(document).ajaxError(function(event, response){
    console.log("错误响应状态码: ",response.status);
    console.log("错误响应结果: ",response.responseJSON);
    showError(response.responseJSON.msg);
});

/**
 * 禁用 ajax 缓存
 */
$.ajaxSetup({
    cache: false
});

/**
 * 获取 URL 中查询条件部分的参数
 * @param name      参数名称
 * @returns         参数值
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}