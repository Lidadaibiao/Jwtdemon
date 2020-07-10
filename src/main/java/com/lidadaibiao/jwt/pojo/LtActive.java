package com.lidadaibiao.jwt.pojo;

import java.io.Serializable;

/**
 * @author Lidadaibiao
 * @date 2020/7/9 - 14:58
 */
public class LtActive implements Serializable{
    private String activeId;
    private String activeName;
    private String activeAddress;
    private String userId;

    public LtActive() {
    }

    public LtActive(String activeId, String activeName, String activeAddress, String userId) {
        this.activeId = activeId;
        this.activeName = activeName;
        this.activeAddress = activeAddress;
        this.userId = userId;
    }

    public String getActiveId() {
        return activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getActiveAddress() {
        return activeAddress;
    }

    public void setActiveAddress(String activeAddress) {
        this.activeAddress = activeAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LtActive{" +
                "activeId='" + activeId + '\'' +
                ", activeName='" + activeName + '\'' +
                ", activeAddress='" + activeAddress + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
