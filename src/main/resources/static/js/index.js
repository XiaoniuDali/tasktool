/**
 项目JS主入口
 以依赖layui的layer和form模块为例
 **/
layui.define(['layer', 'form'], function(exports){
    var layer = layui.layer
        ,form = layui.form;

    // layer.msg('Hello World');
    //
    //
    //
    // layer.open({
    //     type: 2,
    //     title: 'layer mobile页',
    //     shadeClose: true,
    //     shade: 0.8,
    //     area: ['380px', '90%'],
    //     content: 'mobile/' //iframe的url
    // });

    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});