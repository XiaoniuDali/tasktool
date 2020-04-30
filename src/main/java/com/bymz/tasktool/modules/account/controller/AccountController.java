package com.bymz.tasktool.modules.account.controller;

import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import com.bymz.tasktool.modules.sys.shiro.AesUtils;
import com.bymz.tasktool.modules.sys.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @RequestMapping("/register")
    public String register(Account account, ModelMap modelMap){
        modelMap.put("key", AesUtils.getKey(128).substring(0, 16));
        modelMap.put("account", account);
        //向模板中添加属性
        modelMap.put("hello","helloweb");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "account/register";
    }

    /**
     * 保存注册用户信息
     * @param modelMap
     * @return
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Map<String, Object> doRegister(Account account, String key, ModelMap modelMap){
        Map<String, Object> result = new HashMap<>();
        //解密加密的密码
        String password = AesUtils.decrypt(account.getPassword(), key);
        account.setPassword(password);

        // return模板文件的名称，对应src/main/resources/templates/index.html

        //对用户设置的密码进行加密，设置加密用的salt
        PasswordHelper.encryptPassword(account);

        service.save(account);
        result.put("status", 1);
        result.put("massage", "ok");
        //注册成功跳转到首页   又或是跳转到登入页
        return result;
    }
}