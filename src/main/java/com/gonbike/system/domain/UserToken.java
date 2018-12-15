package com.gonbike.system.domain;

import com.gonbike.common.utils.HelpUtil;

import java.io.Serializable;

/**
 * @author Shuaige 77509028@qq.com
 */
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String token;


    private String createTime;
    private String expireTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        if (createTime==null){
            return HelpUtil.getDateTime();
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
