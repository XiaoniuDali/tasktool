package com.bymz.tasktool.modules.test.controller;

import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.test.from.AccountFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private SysDictDataDao dictDataDao;

    @Autowired
    private AccountService service;

    @RequestMapping("toFromDemo")
    public String toFromDemo(ModelMap model){
        Account account = new Account();
        account.setStatus(2);
        model.put("account", account);
        List<SysDictData> sysStatus = dictDataDao.queryByDictType("sys_status");
        model.put("sysStatus", sysStatus);
        return "/test/from_demo";
    }

    /*
    试试用新电脑提交代码！
     */
    @RequestMapping("getJsonData")
    @ResponseBody
    public Object getJsonData(){
        Map<String, Object> data = new HashMap<>();
        data.put("string value", "value1");
        data.put("double value", 2232.33);
        data.put("date value", new Date());
        return data;
    }

    @RequestMapping("toLayuiTable")
    public String toLayuiTable(){
        return "/test/layui_table";
    }

    @RequestMapping("toEditLayuiTable")
    public String toEditLayuiTable(){
        return "/test/layui_table_demo";
    }

    @RequestMapping("getAccountData")
    @ResponseBody
    public Object getAccountData(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("count",3);
        data.put("data", service.list());
        return data;
    }

    @RequestMapping("saveAccounts")
    @ResponseBody
    public Object saveAccounts(@RequestBody AccountFrom from){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("count",3);
        return data;
    }
}
