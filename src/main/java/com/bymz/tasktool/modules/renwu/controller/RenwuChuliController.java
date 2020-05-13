package com.bymz.tasktool.modules.renwu.controller;

import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.service.RenwuChuliService;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/renwuChuli")
public class RenwuChuliController {

    @Autowired
    private RenwuChuliService service;

    @RequestMapping("/toAdd")
    public String addRenwu(){
        return "/renwu/editRenwuXinxi";
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public Object doAdd(RenwuChuli entity){
        reset(entity);
        return service.save(entity);
    }

    @RequestMapping("/wanchengRenwu")
    @ResponseBody
    public Object wanchengRenwu(long renwuId, long accountId, String wanchengShijian, int pingfen){
        return service.wanchengRenwu(renwuId, accountId, wanchengShijian, pingfen);
    }

    @RequestMapping("/querenWanchengRenwu")
    @ResponseBody
    public Object querenWanchengRenwu(long renwuId, long accountId, String wanchengShijian, int pingfen){
        return service.querenWanchengRenwu(renwuId, accountId, wanchengShijian, pingfen);
    }
    private void reset(RenwuChuli entity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        entity.setRenwuId(1);
        entity.setRenwuId(1);
        entity.setAccountId(1);
        entity.setJindu(0);

//        renwuXinxi.setRenwuLeixing(1);
//        renwuXinxi.setSfwGzrw(0);
//        renwuXinxi.setSfwDsrw(0);
//        renwuXinxi.setYxj(1);
        try {
            entity.setJieshouShijian(format1.parse("2020-05-10 12:30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        renwuXinxi.setSfyxDrzx(0);
//        renwuXinxi.setZhuangtai(1);
//        renwuXinxi.setRenwuCjr(1);
//        renwuXinxi.setRenwuCjsj(new Date());

    }
}
