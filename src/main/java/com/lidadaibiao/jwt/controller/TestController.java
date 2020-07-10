package com.lidadaibiao.jwt.controller;

import com.lidadaibiao.jwt.commons.JwtResult;
import com.lidadaibiao.jwt.commons.ResponseData;
import com.lidadaibiao.jwt.pojo.LtUser;
import com.lidadaibiao.jwt.pojo.LtUserSubject;
import com.lidadaibiao.jwt.service.LtService;
import com.lidadaibiao.jwt.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Lidadaibiao
 * @date 2020/7/10 - 11:23
 */
@Controller
@RequestMapping("/testlocalStorage")
public class TestController extends BaseController{

    @Autowired
    LtService ltService;
    @RequestMapping("/testAll")
    @ResponseBody
    public Object testAll(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        JwtResult result = JwtUtils.validateJwtToken(token);
        ResponseData responseData = new ResponseData();

        if(result.isSuccess()){
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());
            // 重新生成token，就是为了重置token的有效期。
            String newToken = JwtUtils.createJwtToken(result.getClaims().getId(),result.getClaims().getIssuer(),result.getClaims().getSubject(),EFFECTIVE_TIME);
            responseData.setToken(newToken);
            return responseData;
        }else{
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }

    }

    @RequestMapping("/testLogin")
    @ResponseBody
    public Object login(String userName, String passWord){

        HashMap<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("passWord",passWord);
        ResponseData responseData = new ResponseData();
        LtUser ltUser = ltService.getUserByUserNameAndPassWord(map);
        // 认证用户信息。本案例中访问静态数据。
        if(null!=ltUser){
            LtUserSubject subject = new LtUserSubject(userName,ltUser.getUserId());
            String token = JwtUtils.createJwtToken(UUID.randomUUID().toString(),"lidadaibiao-test-jwt",JwtUtils.subjectToString(subject),EFFECTIVE_TIME);
            responseData.setCode(200);
            responseData.setData(null);
            responseData.setMsg("登录成功");
            responseData.setToken(token);
        }else{
            responseData.setCode(500);
            responseData.setData(null);
            responseData.setMsg("登录失败");
            responseData.setToken(null);
        }
        return responseData;
    }
}
