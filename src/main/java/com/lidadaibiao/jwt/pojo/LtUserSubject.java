package com.lidadaibiao.jwt.pojo;

import java.io.Serializable;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 10:16
 *  作为Subject数据使用。也就是payload中保存的public claims
 */
public class LtUserSubject implements Serializable {
    /**
     * 用户主键ID USER_ID
     */
    private String userId;

    /**
     * 姓名 USER_NAME
     */
    private String userName;
    public LtUserSubject(){
        super();
    }
    public LtUserSubject( String userName,String userId) {
        super();
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
