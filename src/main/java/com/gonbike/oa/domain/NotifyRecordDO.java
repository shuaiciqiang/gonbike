package com.gonbike.oa.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 通知通告发送记录
 *
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-10-10 11:08:06
 */
public class NotifyRecordDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *  编号
     */
    private Long id;
    //通知通告ID
    private Long notifyId;
    //接受人
    private String userId;
    //阅读标记
    private Integer isRead;
    //阅读时间
    private String readDate;

    /**
     * 设置：编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：通知通告ID
     */
    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * 获取：通知通告ID
     */
    public Long getNotifyId() {
        return notifyId;
    }

    /**
     * 设置：接受人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：接受人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置：阅读标记
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * 获取：阅读标记
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * 设置：阅读时间
     */
    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    /**
     * 获取：阅读时间
     */
    public String getReadDate() {
        return readDate;
    }

    @Override
    public String toString() {
        return "NotifyRecordDO{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", userId=" + userId +
                ", isRead=" + isRead +
                ", readDate=" + readDate +
                '}';
    }
}
