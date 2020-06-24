package com.bymz.tasktool.modules.renwu.controller;

import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.entity.RenwuJlx;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliShezhiService;
import com.bymz.tasktool.modules.renwu.service.RenwuJlxService;
import com.bymz.tasktool.modules.renwu.vo.RenwuJlxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/renwuJlx")
public class RenwuJlxController {

    @Autowired
    private RenwuJlxService service;

    @RequestMapping("/toAdd")
    public String addRenwu(){
        return "/renwu/editRenwuXinxi";
    }

    @RequestMapping("/toRenwuJiangliShezhiSelect")
    public String toRenwuJiangliShezhiSelect(){
        return "/renwu/renwuJiangliSelect";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        List<RenwuJlx> list = service.list();
//        result.put("page", page);
        result.put("code", 0);
        result.put("data", list);
        return result;
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public Object doAdd(RenwuJlx entity){
        return service.save(entity);
    }

    @RequestMapping("/doAddList")
    @ResponseBody
    public Object doAddList(@RequestBody RenwuJlxVo jiangliShezhiVo){
        ArrayList<RenwuJlx> jlxList = jiangliShezhiVo.getJlxList();
        for (RenwuJlx jlx: jlxList){
            jlx.setRenwuId(jiangliShezhiVo.getRenwuId());
        }
        return service.saveBatch(jlxList);
//        return jlxList;
    }

}
