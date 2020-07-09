// alert(window.parent.AES_KEY);//


jQuery(document).ready(function() {

    //新增任务信息表单初始化
    // Spinner
    var spinner = jQuery('#renwuNdxs').spinner();
    spinner.spinner('value', 0);
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

    // Wizard With Form Validation
    var $validator = jQuery("#basicWizard").validate({
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

    // Basic Wizard
    jQuery('#basicWizard').bootstrapWizard({
        onTabShow: function(tab, navigation, index) {
            // console.log(tab);
            // console.log(navigation)
            // console.log(index)
            tab.prevAll().addClass('done');
            tab.nextAll().removeClass('done');
            tab.removeClass('done');

            var $total = navigation.find('li').length;
            var $current = index + 1;

            if($current >= $total) {
                $('#basicWizard').find('.wizard .next').addClass('hide');
                $('#basicWizard').find('.wizard .finish').removeClass('hide');
            } else {
                $('#basicWizard').find('.wizard .next').removeClass('hide');
                $('#basicWizard').find('.wizard .finish').addClass('hide');
            }
        },
        onNext: function(a){
            console.log("22222222222"+ a)
            return false;
        }
    });

    // Progress Wizard
    jQuery('#progressWizard').bootstrapWizard({
        onTabShow: function(tab, navigation, index) {
            tab.prevAll().addClass('done');
            tab.nextAll().removeClass('done');
            tab.removeClass('done');

            var $total = navigation.find('li').length;
            var $current = index + 1;

            if($current >= $total) {
                $('#progressWizard').find('.wizard .next').addClass('hide');
                $('#progressWizard').find('.wizard .finish').removeClass('hide');
            } else {
                $('#progressWizard').find('.wizard .next').removeClass('hide');
                $('#progressWizard').find('.wizard .finish').addClass('hide');
            }

            var $percent = ($current/$total) * 100;
            $('#progressWizard').find('.progress-bar').css('width', $percent+'%');
        }
    });

    // Wizard With Disabled Tab Click
    jQuery('#tabWizard').bootstrapWizard({
        onTabShow: function(tab, navigation, index) {
            tab.prevAll().addClass('done');
            tab.nextAll().removeClass('done');
            tab.removeClass('done');

            var $total = navigation.find('li').length;
            var $current = index + 1;

            if($current >= $total) {
                $('#tabWizard').find('.wizard .next').addClass('hide');
                $('#tabWizard').find('.wizard .finish').removeClass('hide');
            } else {
                $('#tabWizard').find('.wizard .next').removeClass('hide');
                $('#tabWizard').find('.wizard .finish').addClass('hide');
            }
        },
        onTabClick: function(tab, navigation, index) {
            return false;
        }
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
            var $valid = jQuery('#valWizard').valid();
            if (!$valid) {
                $validator.focusInvalid();
                return false;
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


    // This will submit the basicWizard form
    jQuery('.panel-wizard').submit(function() {
        alert('This will submit the form wizard');
        return false // remove this to submit to specified action url
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