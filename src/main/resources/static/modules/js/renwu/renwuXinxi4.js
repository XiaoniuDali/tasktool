// alert(window.parent.AES_KEY);//

var $validator;
jQuery(document).ready(function() {

    //新增任务信息表单初始化
    // Spinner
    // var spinner = jQuery('#renwuNdxs').spinner();
    // spinner.spinner('value', 0);

    // layui.use('slider', function(){
    //     var slider = layui.slider;
    //
    //     //渲染
    //     slider.render({
    //         elem: '#renwuNdxs'  //绑定元素
    //     });
    // });

    layui.use('rate', function(){
        var rate = layui.rate;

        //渲染
        var ins1 = rate.render({
            elem: '#renwuNdxs',  //绑定元素
            setText: function(value){
            var arrs = {
                '1': '简单'
                ,'2': '非常简单'
                ,'3': '中等'
                ,'4': '好'
            };
            this.span.text(arrs[value] || ( value + "星"));
        }

        });
    });

    $("select[name='renwuLeixing']").select2();
    $("select[name='renwuLeixing2']").select2();

    //预计开始时间选择器初始化
    jQuery('#yjKssjDate').datepicker();
    jQuery('#yjKssjTime').timepicker({showMeridian: false});

    //预计结束时间选择器初始化
    jQuery('#yjJssjDate').datepicker();
    jQuery('#yjJssjTime').timepicker({showMeridian: false});


    // Form Toggles
    jQuery('.toggle').toggles({on: true});

    // Tags Input
    jQuery('#tags').tagsInput({width:'auto'});
    jQuery('#jiandurenTags').tagsInput({width:'auto'});

    //校验

    $validator = jQuery("#renwu-xinxi-form").validate({
        highlight: function(element) {
            jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function(element) {
            jQuery(element).closest('.form-group').removeClass('has-error');
        }
    });



    // This will empty first option in select to enable placeholder
    jQuery('select option:first-child').text('');

    // Select2
    jQuery("select").select2({
        minimumResultsForSearch: -1
    });

    // Wizard With Form Validation
    jQuery('#valWizard').bootstrapWizard({
        onTabShow: function(tab, navigation, index) {
            tab.prevAll().addClass('done');
            tab.nextAll().removeClass('done');
            tab.removeClass('done');

            var $total = navigation.find('li').length;
            var $current = index + 1;

            if($current >= $total) {
                $('#valWizard').find('.wizard .next').addClass('hide');
                $('#valWizard').find('.wizard .finish').removeClass('hide');
            } else {
                $('#valWizard').find('.wizard .next').removeClass('hide');
                $('#valWizard').find('.wizard .finish').addClass('hide');
            }
        },
        onTabClick: function(tab, navigation, index) {
            return false;
        },
        onNext: function(tab, navigation, index) {
            console.log(index);
            if (1 == index){
                if (! $validator.form()) {
                    $validator.focusInvalid();
                    return false;
                }

                if($("input[name='id']").val() != 0 && $("input[name='zhuangtai']").val() == 1){//如果是返回修改新增的任务信息

                } else if($("input[name='id']").val() == 0){ //如果是新增任务
                    $.ajax({
                        url:"/renwuXinxi/addRenwu",
                        type: "post",
                        async: false,
                        dataType: "json",
                        data: $("#renwu-xinxi-form").serialize(),
                        success: function(response){
                            if(response){
                                $("input[name='id']").val(response.id);
                                $("input[name='zhuangtai']").val(response.zhuangtai);
                            }
                            console.log(response);
                        }
                    })
                }


            }
            if(2 == index){

                var jlszData = jlszTable.cache['demo'];
                var data = {
                    renwuId: $("input[name='id']").val(),
                    jlxList: jlszData
                }
                data = JSON.stringify(data);

                $.ajax({
                    url: '/renwuJlx/doAddList',
                    type: 'post',
                    data: data,
                    dataType: 'json',
                    contentType:"application/json;charset=UTF-8",
                    success: function (res) {
                        console.log(res);
                    }
                });


            }
        }
    });

    // Wizard With Form Validation
    var $validator = jQuery("#valWizard").validate({
        highlight: function(element) {
            jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function(element) {
            jQuery(element).closest('.form-group').removeClass('has-error');
        }
    });

    jQuery('#basicTable').DataTable({
        responsive: true
    });

});

//关闭时提示用户确认关闭。
function closePageHandler(){
    var msg = {
        type: 2,//用户关闭页面前进行询问
        msg: '确定要撒手人寰，中道崩殂？',//询问语句
        yesBtnValue: '确定',
        noBtnValue: '取消',
        yes: function(){//用户选择确定关闭时要做的处理
            // alert($("input[name='id']").val());
            var renwuId = $("input[name='id']").val();
            $.ajax({
                url: "/renwuXinxi/delRenwuAndXiangguan",
                type: "post",
                data:{id: renwuId},
                dataType: "json",
                async: false,
                success: function(result){
                    console.log(result);
                }
            })
        },
        no: function(){

        }
    }
    return msg;
}

function saveRenwuXinxi(){
    //验证表单
    if($validator.form()){
        $.ajax({
            url:"/renwuXinxi/addRenwu",
            type: "post",
            dataType: "json",
            data: $("#renwu-xinxi-form").serialize(),
            success: function(response){
                console.log(response);
            }
        })
    }
}

function createCombox(data) {
    var _html = '<select style="width:100%;">';
    data.forEach(function (ele, index) {
        _html += '<option>' + ele + '</option>';
    });
    _html += '</select>';
    return _html;
}

var jlszTable;
layui.use('table', function(){
    jlszTable = layui.table;
    var form = layui.form;
    //第一个实例
    jlszTable.render({
        elem: '#demo'
        ,height: 312
        ,url: '/renwuJiangliShezhi/list'
        // ,page: true
        ,data: []
        ,cols: [
            [
                {type: 'checkbox'}
                ,{field: 'jiangliLiyou', title: '奖励理由', width:220, sort: true}
                ,{field: 'jiangliTiaojian', title: '奖励条件', width:220, sort: true}
                // ,{field: 'jiangliWzlx', title: '奖励物质类型', width:120, sort: true}
                // ,{field: 'jiangliWzdw', title: '物质单位', width:80, sort: true}
                ,{field: 'jiangliShuliang', title: '数量', width:80, edit: true}
                ,{field: 'shifouAwcdlq', title:'按完成度领取奖励', width:180, templet: '#shifouAwcdlqTpl', unresize: true}
                ,{
                    field: 'jiangliWzlx',
                    title: '奖励物质类型',
                    align: 'center',
                    width: 200,
                    templet: function (d) {
                        // 模板的实现方式也是多种多样，这里简单返回固定的
                        return '<select name="jiangliWzlx" lay-filter="testSelect" lay-verify="required" data-value="' + d.jiangliWzlx + '" >\n' +
                            '        <option value=""></option>\n' +
                            '        <option value="1" '+ (d.jiangliWzlx == 1 ? 'selected' : '') +'>金豆</option>\n' +
                            '        <option value="2" '+ (d.jiangliWzlx == 2 ? 'selected' : '') +'>自由时间</option>\n' +
                            '        <option value="3" '+ (d.jiangliWzlx == 3 ? 'selected' : '') +'>达成小心愿</option>\n' +
                            '      </select>';
                    }
                }
                , { field: 'jiangliWzdw', title: '物质单位', minWidth: 130 ,templet: '#selectJiangliWzdw' }
                // ,{fixed: 'right', title: '操作', width:190, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]
        ]
    });

    // //监听工具条
    // jlszTable.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    //     var data = obj.data; //获得当前行数据
    //     var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    //     var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
    //
    //     if(layEvent === 'detail'){ //查看
    //         //do somehing
    //     } else if(layEvent === 'del'){ //删除
    //         layer.confirm('真的删除行么', function(index){
    //             obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
    //             layer.close(index);
    //             //向服务端发送删除指令
    //         });
    //     } else if(layEvent === 'edit'){ //编辑
    //         //do something
    //
    //         //同步更新缓存对应的值
    //         obj.update({
    //             dictLabel: '123'
    //             ,title: 'xxx'
    //         });
    //     } else if(layEvent === 'LAYTABLE_TIPS'){
    //         layer.alert('Hi，头部工具栏扩展的右侧图标。');
    //     }
    //
    // });

    //监听锁定操作  从方法中的console.log语句就可以看出，这个方法废了我多少心血
    form.on('checkbox(shifouAwcdlqFilter)', function(obj){
        // console.log(obj)
        // console.log(this)
        // console.log(obj.othis);



        var tableData = jlszTable.cache['demo'];

        var elem = $(obj.elem);
        var trElem = elem.parents('tr');
        // console.log(trElem);
        // console.log(trElem.data('index')); //被点击那行的下标，第一行为0，第二行为1以此类推
        // console.log(elem.attr('name'));  //输出shifouAwcdlq 该值是引入的下拉控件的name值
        // console.log(obj.value)

        // 更新到表格的缓存数据中，才能在获得选中行等等其他的方法中得到更新之后的值
        tableData[trElem.data('index')][elem.attr('name')] = obj.elem.checked ? 1 : 0;
        // console.log(tableData)
    });

    // 监听修改update到表格中
    form.on('select(testSelect)', function (data) {
        debugger;
        var elem = $(data.elem);
        var trElem = elem.parents('tr');
        var tableData = jlszTable.cache['demo'];
        // 更新到表格的缓存数据中，才能在获得选中行等等其他的方法中得到更新之后的值
        tableData[trElem.data('index')][elem.attr('name')] = data.value;
        // 其他的操作看需求 TODO
    });


    form.on('select(jiangliWzdwFilter)', function (obj) {
        var elem = $(obj.elem);
        var trElem = elem.parents('tr');

        var tableData = jlszTable.cache['demo'];
        // 更新到表格的缓存数据中，才能在获得选中行等等其他的方法中得到更新之后的值
        tableData[trElem.data('index')][elem.attr('name')] = obj.value;
        form.render('select');
    })

});




//扩展jquery的格式化方法
$.fn.parseForm=function(){
    var serializeObj={};
    var array=this.serializeArray();
    var str=this.serialize();
    $(array).each(function(){
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    return serializeObj;
};