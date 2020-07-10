package com.lidadaibiao.jwt.controller;

import com.lidadaibiao.jwt.commons.JwtResult;
import com.lidadaibiao.jwt.commons.ResponseData;
import com.lidadaibiao.jwt.util.JwtUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Lidadaibiao
 * @date 2020/7/9 - 20:53
 */
public class BaseController {
    public static final long EFFECTIVE_TIME=1*10*1000;
    public static final String HAS_EXPIRED="用户登录已过期，请重新登录";
    public static final String ERRO_PWD_NAME="用户密码或用户名错误，请重新输入";

    public static String getToken(HttpServletRequest request){
        /**
         * 取出token
         */
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }
        return token;
    }
    public static JwtResult validateToken(String token)
    {
        return JwtUtils.validateJwtToken(token);
    }
    public static String flushToken(JwtResult result){
        return JwtUtils.createJwtToken(result.getClaims().getId(),result.getClaims().getIssuer(),result.getClaims().getSubject(),EFFECTIVE_TIME);
    }
    public static String getUUID(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
    public static ResponseData fileDate(ResponseData responseData){
        responseData.setData(null);
        responseData.setToken(null);
        responseData.setCode(500);
        responseData.setMsg(HAS_EXPIRED);
        return responseData;
    }
}
