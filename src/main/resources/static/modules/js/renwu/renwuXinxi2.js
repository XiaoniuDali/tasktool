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



});

//关闭时如果提示用户确认关闭。
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