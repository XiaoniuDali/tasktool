package com.bymz.tasktool.modules.sys.dict.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dictData")
public class SysDictDataController {
    @Autowired
    private SysDictDataDao dao;

    @Autowired
    private SysDictDataService service;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<SysDictData> getAll(){
        Wrapper wrapper = new Wrapper() {
            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        };
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
