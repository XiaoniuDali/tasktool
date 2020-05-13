package com.bymz.tasktool.modules.renwu.controller;

import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliService;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliShezhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/renwuJiangliShezhi")
public class RenwuJiangliShezhiController {

    @Autowired
    private RenwuJiangliShezhiService service;

    @RequestMapping("/toAdd")
    public String addRenwu(){
        return "/renwu/editRenwuXinxi";
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public Object doAdd(RenwuJiangliShezhi entity){
        reset(entity);
        return service.save(entity);
    }

    private void reset(RenwuJiangliShezhi entity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        entity.setAccountId(1);
        entity.setJiangliLiyou("按时完成任务奖励");
        entity.setJiangliTiaojian("在预计完成时间之前完成任务");
        entity.setJiangliWzlx(3);
        entity.setJiangliShuliang(100);
        entity.setJiangliWzdw(3);
        entity.setShifouAwcdlq(1);
        entity.setDuixianFangshi(1);
        entity.setJiesuanClassName("com.bymz.tasktool.modules.renwu.service.impl.AnshiWanchengJiangliJiesuan");
        entity.setJiangliMiaoshu("按时完成任务得到的奖励");
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
