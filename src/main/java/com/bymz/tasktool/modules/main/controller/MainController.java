package com.bymz.tasktool.modules.main.controller;

import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import com.bymz.tasktool.modules.sys.shiro.AesUtils;
import com.bymz.tasktool.modules.sys.shiro.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public String index(ModelMap modelMap){
        //向模板中添加属性
        modelMap.put("hello","helloweb");
        // return模板文件的名称，对应src/main/resources/templates/index.html

        boolean loginStatus = true;
        //如果用户已经登入，重定向到首页
        if(true == loginStatus){
            return "redirect:/welcome";
        }else{
            return "redirect:/login";
        }

    }


    @RequestMapping("/welcome")
    public String hello(ModelMap modelMap){
        //向模板中添加属性
        modelMap.put("hello","helloweb");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping("/login")
    public String login(Account account, ModelMap modelMap){
        modelMap.put("account", account);
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
//        modelMap.put("key", randomNumberGenerator.nextBytes().toHex());
//        modelMap.put("key", "1234123412ABCDEF");
        modelMap.put("key", AesUtils.getKey(128).substring(0, 16));
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Map<String, Object> doLogin(Account account, String key, ModelMap modelMap){
        Map<String, Object> result = new HashMap<>();
        String password = AesUtils.decrypt(account.getPassword(), key);

        UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            result.put("status", 0);
            result.put("massage", "password error!");
            return result;
        } catch (UnknownAccountException uae) {
            result.put("status", 0);
            result.put("massage", "username not find!");
            return result;
        }

//        User user = userService.findUserByName(username);
        subject.getSession().setAttribute("user", account);
        //验证用户提交的登入信息，完成登入

        boolean loginStatus = true;

        result.put("status", 1);
        result.put("massage", "ok");
        return result;
    }

    @RequestMapping("/logout")
    public String logout(Account account, ModelMap modelMap){
        PasswordHelper.encryptPassword(account);
        UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), account.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";

    }

}
