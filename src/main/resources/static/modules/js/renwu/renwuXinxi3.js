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

                // layui.use(["layer"], function(){
                //     var loading = layer.load(1, {
                //         shade: [0.5, '#fff'],
                //         time: 22*1000
                //     });

                    $.ajax({
                        url:"/renwuXinxi/addRenwu",
                        type: "post",
                        async: false,
                        dataType: "json",
                        data: $("#renwu-xinxi-form").serialize(),
                        success: function(response){
                            console.log(response);
                            // layer.close(loading);
                        }
                    })
                // });




            }
            if(2 == index){
                console.log(editTableObj.data());
                editTableObj.data().each(function (d) {
                    console.log(d);
                    d.counter++;
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


layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 312
        // ,url: '/test/getAccountData'
        // ,page: true
        ,data: []
        ,cols: [
            [
                {field: 'jiangliLiyou', title: '奖励理由', width:120, sort: true, fixed: 'left'}
                ,{field: 'jiangliTiaojian', title: '奖励条件', width:120, sort: true, fixed: 'left'}
                ,{field: 'jiangliWzlx', title: '奖励物质类型', width:120, sort: true, fixed: 'left'}
                ,{field: 'jiangliWzdw', title: '物质单位', width:80, sort: true, fixed: 'left'}
                ,{field: 'jiangliShuliang', title: '数量', width:80, edit: true}
                ,{fixed: 'right', title: '操作', width:190, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]
        ]
        ,toolbar: '#toolbarDemo'
    });


    //监听事件
    table.on('toolbar(test)', function(obj){
        // var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                layer.open({
                    type: 2,
                    content: '/renwuJiangliShezhi/toRenwuJiangliShezhiSelect',
                    area: ['800px', '600px'],
                    cancel: function(index, layero){
                        var iframeName = layero.find('iframe')[0]['name'];
                        var iframeWin = window[iframeName];
                        var oldData =  table.cache["demo"];
                        console.log(oldData);
                        console.log(iframeWin.selectObj);

                        iframeWin.selectObj.forEach(function(obj){
                            oldData.push(obj);
                        });
                        console.log(oldData)
                        table.reload('demo',{
                            data : oldData
                        });
                    }
                });



                break;
            case 'delete':
                layer.msg('删除');
                break;
            case 'update':
                layer.msg('编辑');
                break;
            case 'save':
                // layer.msg('编辑');
                var oldData =  table.cache["demo"];

                var jsonData = {
                    intValue: 2333,
                    doubleValue: 2.333,
                    stringValue: '2333',
                    dateValue: '2020-06-19 10:23:33',
                    accounts: [
                        {userId: '11',username: 'cao'},
                        {userId: '2233',username: 'cao'}
                    ]
                }

                // var data = JSON.stringify({accounts: oldData});
                var data = JSON.stringify(jsonData);

                console.log(data);

                var formData = $("#form_01").parseForm();
                formData.accounts = [
                    {userId: '11',username: 'cao'},
                    {userId: '2233',username: 'cao'}
                ];
                data = JSON.stringify(formData);
                console.log(JSON.stringify(formData));

                $.ajax({
                    url: '/test/saveAccounts',
                    type: 'post',
                    data: data,
                    dataType: 'json',
                    contentType:"application/json;charset=UTF-8",
                    success: function (res) {
                        console.log(res);
                    }
                });
                break;
        };
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'detail'){ //查看
            //do somehing
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something

            //同步更新缓存对应的值
            obj.update({
                dictLabel: '123'
                ,title: 'xxx'
            });
        } else if(layEvent === 'LAYTABLE_TIPS'){
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }

    });
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