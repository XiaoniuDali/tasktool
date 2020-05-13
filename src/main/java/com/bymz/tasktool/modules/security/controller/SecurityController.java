package com.bymz.tasktool.modules.security.controller;

import com.bymz.tasktool.modules.security.util.RsaUtil;
import com.bymz.tasktool.modules.security.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping("/getRsaPublicKey")
    @ResponseBody
    public Map<String, Object> getRsaPublicKey(){
        Map<String, Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("rsaPublicKey", RsaUtil.publicKey);
        return result;
    }

    @RequestMapping("/receiveUserAesKey")
    @ResponseBody
    public Map<String, Object> receiveUserAesKey(String aesKey, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        //用RSA算法的私钥解密出AES算法的密钥
        try{
            aesKey = RsaUtil.decrypt(aesKey, RsaUtil.getPrivateKey(RsaUtil.privateKey));
            request.getSession().setAttribute(SecurityUtil.SEESION_KEY_AES_KEY, aesKey);

            result.put("status", "ok");
            result.put("msg", "system success receive user aes key!");
        }catch (Exception e){
            result.put("status", "error");
            result.put("msg", "system receive user aes key error!");
            result.put("error", e.getMessage());
        }
        return result;
    }
}
