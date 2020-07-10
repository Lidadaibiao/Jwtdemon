package com.lidadaibiao.jwt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lidadaibiao.jwt.commons.JwtResult;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Signature;
import java.util.Date;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 10:19
 * JWT工具
 */
public class JwtUtils {
    // 服务器的key。用于做加解密的key数据。 如果可以使用客户端生成的key。当前定义的常亮可以不使用。
    private static final String JWT_SECERT = "test_jwt_secert" ;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final int JWT_ERRCODE_EXPIRE = 666;//Token过期
    public static final int JWT_ERRCODE_FAIL = 888;//验证不通过

    public static SecretKey generKey()
    {
        try {
            //得到一个byte[]类型的key
            byte[] encodedKey = JWT_SECERT.getBytes("UTF-8");
            //加密的密匙
            SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length-1,"AES");//对encodedKey进行AES方式加密
            return key;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签发JWT
     * 该方法用于创建token
     * @param id JWT唯一身份标识，主要用来作为一次性token,防止重放攻击
     * @param iss  jwt签发者，也就是谁去生成的token
     * @param subject 作为Subject数据使用。也就是payload中保存的public claims
     * @param ept token有效期
     * @return
     */
    public static String createJwtToken(String id,String iss,String subject,long ept){
        //加密算法
        SignatureAlgorithm signatureAlgorithm  =SignatureAlgorithm.HS256;
        //当前时间
        long nowMills = System.currentTimeMillis();
        Date nowTime = new Date(nowMills);
        //拿到秘钥
        SecretKey key = generKey();
        //创建JWT构建器，开始生成Token
        JwtBuilder builder = Jwts.builder().setId(id).setIssuer(iss).setSubject(subject).setIssuedAt(nowTime).signWith(signatureAlgorithm,key);
        if (ept>=0) {
            long eptMills = nowMills+ept;
            Date eptDate = new Date(eptMills);//刷新token失效时间
            builder.setExpiration(eptDate);
        }
        return builder.compact();//生成token
    }

    /**
     * 验证token
     * @param jwtTokenStr  被验证的jwttoken
     * @return
     */
    public static JwtResult validateJwtToken(String jwtTokenStr){
        JwtResult checkResut = new JwtResult();
        Claims claims = null;
        try {
            claims = parseJwt(jwtTokenStr);
            checkResut.setSuccess(true);
            checkResut.setClaims(claims);
        } catch (ExpiredJwtException e) { // token超时
            checkResut.setErrCode(JWT_ERRCODE_EXPIRE);
            checkResut.setSuccess(false);
        } catch (SignatureException e) { // 校验失败
            checkResut.setErrCode(JWT_ERRCODE_FAIL);
            checkResut.setSuccess(false);
        } catch (Exception e) {
            checkResut.setErrCode(JWT_ERRCODE_FAIL);
            checkResut.setSuccess(false);
        }
        return checkResut;
    }

    /**
     * 解析jwt字符串
     * @param jwtTokenStr
     * @return
     */
    public static Claims parseJwt(String jwtTokenStr)
    {
        SecretKey secretKey = generKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtTokenStr).getBody();
    }
    /**
     *Json转换成字符串
     */
    public static String subjectToString(Object subject) {
        try {
           return MAPPER.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
