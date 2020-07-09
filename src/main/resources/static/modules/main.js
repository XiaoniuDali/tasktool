var main_panel_body_min_height = 0;

function initConstVar(){
    console.log("-------------ininConstVar----------------------------")
    var mainBodyHeight = document.getElementById("main_content").offsetHeight;
    console.log(mainBodyHeight);
    mainBodyHeight = 2122;
    var headHeight = $("#headerwrapper").css("height");
    headHeight = document.getElementById("headerwrapper").offsetHeight;
    console.log(headHeight);

    var leftPanelHeight = $("#left_panel").css("height");
    leftPanelHeight = document.getElementById("left_panel").offsetHeight;
    console.log(leftPanelHeight);

    var pageTabHeight = $("#page_tab_div").css("height");
    pageTabHeight = document.getElementById("page_tab_div").offsetHeight;
    console.log(pageTabHeight);

    main_panel_body_min_height = mainBodyHeight - headHeight - pageTabHeight;
    console.log(main_panel_body_min_height);
}


function initSize(){
    $("#page_body").css("height", main_panel_body_min_height);
}


function setIframeSize(iframe) {console.log(iframe)
    if (iframe) {

        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            console.log(iframe.height);
            document.getElementById("main_panel").width =
                document.getElementById('main_wrapper').offsetWidth - document.getElementById('left_panel').offsetWidth;
        }
    }
};



window.onload = function () {
    openNewPage('/renwuXinxi/toAddRenwu2', '新增任务');
    initConstVar();
    initSize();
    // setIframeSize(document.getElementById('iframe_0'));

};

/**
 * 显示左边隐藏的tab
 * @param num
 */
function showLeftTab(num){
    var i = 0;debugger;
    $("span.page-tab").each(function(){

        if("none" != $(this).css("display")){
            console.log("block  true");
            $(this).css("display", "none");
            i ++;
        }
        if(i == num){
            return false;
        }
    });

    var tabList = $($("span.page-tab").toArray().reverse());
    i = 0;
    tabList.each(function(){
        console.log()
        if("none" == $(this).css("display")){
            console.log("block  true");
            $(this).css("display", "");
            i ++;
        }
        if(i == num){
            return false;
        }
    });
}

var pageIndex = 0;
function openNewPage(url, title){
    pageIndex ++;
    var pageTabHtml = "<span class='page-tab' id='page-tab-"+ pageIndex +"' ><span class='page_title'>"+ title +"</span>" +
        "<a href='#' onclick='closePage("+ pageIndex +")'><i class='glyphicon glyphicon-remove'></i></a></span>";




    $("#tab_list").append(pageTabHtml);

    // var $pageTabDiv =
    var pageTabDivWidth = document.getElementById("page_tab_div").offsetWidth;
    // console.log(pageTabDivWidth);

    var currentLength  = 0;
    $("span.page-tab").each(function(){
        currentLength += $(this).get(0).offsetWidth;
        // console.log(currentLength);
    });

    if(currentLength > pageTabDivWidth){
        //隐藏第一个显示的标签
        $("span.page-tab").each(function(){
            console.log($(this).css("display"));
            if("none" != $(this).css("display")){
                // console.log("block  true");
                // $(this).css("display", "none");
                $(this).hide(200);
                return false;
            }
        });

        $("#show-left-page-btn").show();
    }


    //创建一个新窗口
    var iframeHtml = "<div class='page_body' id='page_body_"+ pageIndex +"' style='width: 100%; height: 100%;'>" +
        "<iframe id='iframe_" + pageIndex +"'  name='iframe_"+ pageIndex +"' style='width: 100%; height: 100%;' " +
        " frameborder='0' src='"+ url +"' > </iframe></div>";
    $("#page_body").append(iframeHtml);

    showPageByIndex(pageIndex);

    // setIframeSize(document.getElementById('iframe_'+ pageIndex));
    //
}

/**
 * 关闭指定的页面
 * @param index
 */
function closePage(index){
    var iframeWindow = document.getElementById("iframe_"+ index).contentWindow;

    // 检查该关闭的页面是否有关闭前提示
    if(iframeWindow.closePageHandler){//
        var msg = iframeWindow.closePageHandler();
        //询问框
        if(msg.type == 2){//询问
            layer.confirm(msg.msg, {
                btn: [msg.yesBtnValue, msg.noBtnValue] //按钮
            }, function(i){//选择确定时的处理
                msg.yes();
                doClosePage(index);
                layer.close(i);//关闭询问框
            }, function(){//选择取消的处理方式
                msg.no();
            });
        }

    }else{
        //没有提示的页面直接关闭页面
        doClosePage(index);
    }
}

function doClosePage(index){
    $("#page-tab-"+index).remove();
    $("#page_body_"+ index).remove();

    if(index > 1){
        showPageByIndex(index - 1);
    }else {
        if(index < pageIndex){
            showPageByIndex(index + 1);
        }
    }
}

function showPageByIndex(index){
    for(var i = 0; i < pageIndex; i ++){
        if(i != index){
            $("#page_body_"+ i).hide();
            $("#iframe_"+ i).hide();
        }
    }
    $("#page_body_"+ index).show();
    $("#iframe_"+ index).show();
}

layui.config({
    base: '/static/js/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('index'); //加载入口
