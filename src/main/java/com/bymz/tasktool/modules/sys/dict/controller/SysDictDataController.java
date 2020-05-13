package com.bymz.tasktool.modules.sys.dict.controller;

import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dictData")
public class SysDictDataController {
    @Autowired
    private SysDictDataDao dao;

    @Autowired
    private SysDictDataService service;

    @RequestMapping("/queryPage")
    @ResponseBody
    public Map<String, Object> queryPage(@RequestParam Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        PageData page = service.queryPage(params);
        result.put("page", page);
        return result;
    }


    @RequestMapping("/getAll")
    @ResponseBody
    public List<SysDictData> getAll(){
        return dao.selectList(null);
    }

    @RequestMapping("/findByDictType")
    @ResponseBody
    public List<SysDictData> findByDictType(String dictType){
        return service.queryByDictType(dictType);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public SysDictData findById(String id){
        return dao.selectById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysDictData update(String id){
        SysDictData sysDictData = dao.selectById(id);
        sysDictData.setUpdateDate(new Date());
        service.updateById(sysDictData);
        return sysDictData;
    }
}
