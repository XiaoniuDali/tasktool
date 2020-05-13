package com.bymz.tasktool.modules.security.util;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {
    public final static String SEESION_KEY_AES_KEY = "aesKey";

    /**
     * 根据用户请求，获取到用户的AES密钥
     * @param request
     * @return
     */
    public static String getAesKey(HttpServletRequest request){
        return request.getSession().getAttribute(SEESION_KEY_AES_KEY).toString();
    }
}
