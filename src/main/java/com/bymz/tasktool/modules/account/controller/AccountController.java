package com.bymz.tasktool.modules.account.controller;

import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import com.bymz.tasktool.modules.security.util.AesUtils;
import com.bymz.tasktool.modules.security.util.SecurityUtil;
import com.bymz.tasktool.modules.sys.shiro.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/account")
public class AccountController {


    @Autowired
    private AccountService service;

    @RequestMapping("/register")
    public String register(Account account, ModelMap modelMap){
        modelMap.put("account", account);
        return "account/register";
    }

    /**
     *
     * @param account
     * @return
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Map<String, Object> doRegister(Account account, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        //解密加密的密码
        String aesKey = SecurityUtil.getAesKey(request);
        String password = AesUtils.decrypt(account.getPassword(), aesKey);

        account.setPassword(password);

        //对用户设置的密码进行加密
        PasswordHelper.encryptPassword(account);

//        service.save(account);
        result.put("status", 1);
        result.put("massage", "ok");
        //注册成功跳转到首页   又或是跳转到登入页
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Account findById(Account account, String aesKey, ModelMap modelMap){
        account.setUserId(1l);
        account.setUsername("test user");
        account.setStatus(1);
        return  account;
    }
}