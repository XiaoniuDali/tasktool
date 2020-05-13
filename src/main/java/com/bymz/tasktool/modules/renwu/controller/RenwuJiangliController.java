package com.bymz.tasktool.modules.renwu.controller;

import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliService;
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
@RequestMapping("/renwuJiangli")
public class RenwuJiangliController {

    @Autowired
    private RenwuJiangliService service;

    @RequestMapping("/toAdd")
    public String addRenwu(){
        return "/renwu/editRenwuXinxi";
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public Object doAdd(RenwuJiangli entity){
        reset(entity);
        return service.save(entity);
    }

    private void reset(RenwuJiangli entity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        entity.setRenwuId(1);
        entity.setRenwuJlszId(1);
//        renwuXinxi.setRenwuLeixing(1);
//        renwuXinxi.setSfwGzrw(0);
//        renwuXinxi.setSfwDsrw(0);
//        renwuXinxi.setYxj(1);
//        try {
//            renwuXinxi.setYjKssj(format.parse("2020-05-10 19:30"));
//            renwuXinxi.setYjJssj(format.parse("2020-05-10 20:30"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        renwuXinxi.setSfyxDrzx(0);
//        renwuXinxi.setZhuangtai(1);
//        renwuXinxi.setRenwuCjr(1);
//        renwuXinxi.setRenwuCjsj(new Date());

    }
}
