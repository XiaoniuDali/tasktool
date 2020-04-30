package com.bymz.tasktool.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("getJsonData")
    @ResponseBody
    public Object getJsonData(){
        Map<String, Object> data = new HashMap<>();
        data.put("string value", "value1");
        data.put("double value", 2232.33);
        data.put("date value", new Date());
        return data;
    }


}
