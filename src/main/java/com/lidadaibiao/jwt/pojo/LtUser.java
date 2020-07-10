package com.lidadaibiao.jwt.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 10:11

 * 实体类
 */
public class LtUser implements Serializable {

    /**
     * 用户主键ID USER_ID
     */
    private String userId;


    /**
     * 姓名 USER_NAME
     */
    private String userName;

    /**
     * 密码 PASS_WORD
     */
    private String passWord;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LtUser ltUser = (LtUser) o;
        return Objects.equals(userId, ltUser.userId) &&
                Objects.equals(userName, ltUser.userName) &&
                Objects.equals(passWord, ltUser.passWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, passWord);
    }

    public LtUser(String userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "LtUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
