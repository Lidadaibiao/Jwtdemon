package com.lidadaibiao.jwt.controller;

import com.lidadaibiao.jwt.commons.JwtResult;
import com.lidadaibiao.jwt.commons.ResponseData;
import com.lidadaibiao.jwt.pojo.LtActive;
import com.lidadaibiao.jwt.pojo.LtUser;
import com.lidadaibiao.jwt.pojo.LtUserSubject;
import com.lidadaibiao.jwt.service.LtService;
import com.lidadaibiao.jwt.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 11:06
 * 控制器
 */
@Controller
public class LtUserController extends BaseController{

        @Autowired
        private LtService ltService;

    /**
     * 根据登录用户查询对应活动
     * @param request
     * @param response
     * @return
     */
        @RequestMapping("/showActive")
        public Object insertUser(HttpServletRequest request,HttpServletResponse response)
        {
            String token = getToken(request);
            String userId = (String)request.getSession().getAttribute("userId");
            //验证token
            Cookie cookie = null;
        /*
            Claims claims = result.getClaims();
            String subject = claims.getSubject();
            */
            //String userId  =request.getParameter("userId");

            ResponseData responseData = new ResponseData();
            JwtResult result = validateToken(token);
            if (result.isSuccess()){
                /**
                 * 操作
                 */
                   List<LtActive> listActive = ltService.getActiveByUserId(userId);
                   request.setAttribute("listActive",listActive);
                    //刷新token ,为了重置token有效期
                    String newToken = flushToken(result);
                    responseData.setToken(newToken);
                    responseData.setMsg("成功");
                    cookie = new Cookie("token",newToken);
                    response.addCookie(cookie);
                    return "tolist";
            }else {
                fileDate(responseData);
                request.setAttribute("responseData",responseData);
                return "index";
            }
        }

        @RequestMapping("/testAll")
        @ResponseBody
        public Object testAll(HttpServletRequest request,HttpServletResponse response){
            /**
             * 取出token
             */
            String token = getToken(request);
            //验证token
            JwtResult result = validateToken(token);
            ResponseData responseData = new ResponseData();
            Cookie cookie = null;
            if (result.isSuccess()){
                String newToken = flushToken(result);
                responseData.setCode(200);
                responseData.setMsg("用户登录成功");
                responseData.setData(result.getClaims().getSubject());
                //刷新token ,为了重置token有效期
                responseData.setToken(newToken);
                cookie = new Cookie("token",newToken);
                response.addCookie(cookie);
                return responseData;
            }else {
                return fileDate(responseData);
            }
        }
        @RequestMapping("/login")
        //@ResponseBody
        public Object login(HttpServletRequest request, HttpServletResponse response){
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            HashMap<String,String> map = new HashMap<>();
            map.put("userName",userName);
            map.put("passWord",passWord);
            ResponseData responseData = new ResponseData();
            LtUser ltUser = ltService.getUserByUserNameAndPassWord(map);

            Cookie cookie = null;
            if ( null!=ltUser ){
                request.setAttribute("ltUser",ltUser);
                request.getSession().setAttribute("userId",ltUser.getUserId());
                request.getSession().setAttribute("userName",ltUser.getUserName());
               LtUserSubject ltUserSubject = new LtUserSubject(userName,ltUser.getUserId());
               String token = JwtUtils.createJwtToken(UUID.randomUUID().toString(),"lidadaibiao-test-jwt",JwtUtils.subjectToString(ltUserSubject),EFFECTIVE_TIME);
               responseData.setCode(200);
               responseData.setToken(token);
               responseData.setMsg("登录成功");
               cookie = new Cookie("token",token);
               response.addCookie(cookie);
               responseData.setData(null);
                return "tolist";
            }else {
                responseData.setData(null);
                responseData.setToken(null);
                responseData.setCode(500);
                responseData.setMsg(ERRO_PWD_NAME);
                request.setAttribute("responseData",responseData);
                return "index";
            }

        }
    @RequestMapping("/doDelete")
    @ResponseBody
    public Object doDelete(HttpServletRequest request, HttpServletResponse response){
        String activeId = request.getParameter("activeId");
        String token = getToken(request);
        //验证token
        JwtResult result = validateToken(token);
        ///
        ResponseData responseData = new ResponseData();
        ///
        Cookie cookie = null;
        if (result.isSuccess()){
            int res = ltService.delActiveById(activeId);
            responseData.setCode(200);
            responseData.setMsg("删除成功");
            responseData.setData(result.getClaims().getSubject());
            //刷新token ,为了重置token有效期
            String newToken = flushToken(result);
            responseData.setToken(newToken);
            cookie = new Cookie("token",newToken);
            response.addCookie(cookie);
            return responseData;
        }else {
            fileDate(responseData);
            request.setAttribute("responseData",responseData);
            return responseData;
        }
    }

    @RequestMapping("/doInsert")
    public Object doInsert(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("userId");
        request.setAttribute("userId",userId);
        String token = getToken(request);
        //验证token
        JwtResult result = validateToken(token);
        ///
        ResponseData responseData = new ResponseData();
        ///
        Cookie cookie = null;
        if (result.isSuccess()){
            String newToken = flushToken(result);
            responseData.setCode(200);
            responseData.setMsg("验证成功");
            responseData.setData(result.getClaims().getSubject());
            responseData.setToken(newToken);
            cookie = new Cookie("token",newToken);
            response.addCookie(cookie);
            return "edit";
        }else {
            fileDate(responseData);
            request.setAttribute("responseData",responseData);
            return "index";
        }
    }
    @RequestMapping("/toInsert")
    public Object toInsert(HttpServletRequest request,HttpServletResponse response){
        //String userId = request.getParameter("userId");
        String userId = (String)request.getSession().getAttribute("userId");
        request.setAttribute("userId",userId);
        String activeName = request.getParameter("activeName");
        String activeAddress = request.getParameter("activeAddress");
        String token = getToken(request);
        //验证token
        JwtResult result = validateToken(token);
        ///
        ResponseData responseData = new ResponseData();
        ///
        Cookie cookie = null;
        if (result.isSuccess()){
            //刷新token ,为了重置token有效期
            String newToken = flushToken(result);
            int res = ltService.insertActive(new LtActive(getUUID(),activeName,activeAddress,userId));
            responseData.setCode(200);
            responseData.setMsg("验证成功");
            responseData.setData(result.getClaims().getSubject());
            responseData.setToken(newToken);
            cookie = new Cookie("token",newToken);
            response.addCookie(cookie);
            return "tolist";
        }else {
            fileDate(responseData);
            request.setAttribute("responseData",responseData);
            return "index";
        }
    }


}
