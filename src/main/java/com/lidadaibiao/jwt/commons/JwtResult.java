package com.lidadaibiao.jwt.commons;

import io.jsonwebtoken.Claims;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 9:55
 * 结果对象
 */
public class JwtResult {

    /**
     * 错误编码
     */
    private Integer errCode;
    /**
     * 成功与否，代表结果的状态
     */
    private boolean success;
    /**
     * header  payload  Signature 中的payload中的实体数据
     */
    private Claims claims;
    public JwtResult(){
        super();
    }
    public JwtResult(Integer errCode, boolean success, Claims claims) {
        super();
        this.errCode = errCode;
        this.success = success;
        this.claims = claims;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "JwtResult{" +
                "errCode=" + errCode +
                ", success=" + success +
                ", claims=" + claims +
                '}';
    }
}
