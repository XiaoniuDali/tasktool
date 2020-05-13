package com.bymz.tasktool.modules.renwu.controller;

import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;
import com.bymz.tasktool.modules.renwu.service.RenwuXinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/renwuXinxi")
public class RenwuXinxiController {

    @Autowired
    private RenwuXinxiService service;

    @RequestMapping("/toEditRenwu")
    public String editRenwu(){
        return "/renwu/editRenwuXinxi";
    }

    @RequestMapping("/toAddRenwu")
    public String addRenwu(){
        return "/renwu/AddRenwuXinxi";
    }
    @RequestMapping("/addRenwu")
    @ResponseBody
    public Object addRenwu(RenwuXinxi renwuXinxi, List<RenwuChuli> renwuChuliList){
        resetRenwuXinxi(renwuXinxi);
        return service.addRenwu(renwuXinxi);
    }

    private void resetRenwuXinxi(RenwuXinxi renwuXinxi) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        renwuXinxi.setRenwuMingcheng("今晚锻炼一小时");
        renwuXinxi.setRenwuMubiao("锻炼一个小时，单人运动");
        renwuXinxi.setRenwuNdxs(5);
        renwuXinxi.setRenwuLeixing(1);
        renwuXinxi.setSfwGzrw(0);
        renwuXinxi.setSfwDsrw(0);
        renwuXinxi.setYxj(1);
        try {
            renwuXinxi.setYjKssj(format.parse("2020-05-10 19:30"));
            renwuXinxi.setYjJssj(format.parse("2020-05-10 20:30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        renwuXinxi.setSfyxDrzx(0);
        renwuXinxi.setZhuangtai(1);
        renwuXinxi.setRenwuCjr(1);
        renwuXinxi.setRenwuCjsj(new Date());

    }
}
