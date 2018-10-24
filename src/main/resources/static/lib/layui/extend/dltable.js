/**
 @Name：dltable普通表格
 @Author：beijiyi
 @version: 0.1
 码云地址：https://gitee.com/beijiyi/tree_table_treegrid_based_on_layui
 在线demo：http://beijiyi.com/index_list.html
 */
layui.config({
    base: '/design/extend/'
}).extend({
    treeGrid:'treeGrid'
}).define(['laytpl', 'laypage','treeGrid', 'layer', 'form'], function(exports){
    "use strict";
    var $ = layui.jquery;
    var treeGrid = layui.treeGrid;
    var MOD_NAME='dltable';
    var dltable=$.extend({},treeGrid);
    dltable._render=dltable.render;
    dltable.render=function(param){//重写渲染方法
        param.isTree=false;//普通表格
        param.isPage=true;//默认分页
        dltable._render(param);
    };
    exports(MOD_NAME, dltable);
});