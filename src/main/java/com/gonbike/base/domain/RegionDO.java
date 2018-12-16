package com.gonbike.base.domain;

/**
 * Created by Shuaige on 2018/12/16.
 */
public class RegionDO {
    private Integer id;
    private String name;
    private Integer parentId;
    private String shortName;
    private String shortCode;
    /***
     * 级别，1是省，2市，3区县
     */
    private Integer levelType;
    private String cityCode;
    private String zipCode;
    private String mergerName;
    /**
     * 东经多少度
     */
    private String eastLongitude;
    /**
     * 北纬多少度
     */
    private String northLatitude;
    /**
     * 全球坐标，经纬度
     */
    private String position;
    private String memoCode;
    /***
     * 0有效1无效
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
    /***
     * 级别，1是省，2市，3区县
     */
    public Integer getLevelType() {
        return levelType;
    }
    /***
     * 级别，1是省，2市，3区县
     */
    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }
    /**
     * 东经多少度
     */
    public String getEastLongitude() {
        return eastLongitude;
    }
    /**
     * 东经多少度
     */
    public void setEastLongitude(String eastLongitude) {
        this.eastLongitude = eastLongitude;
    }
    /**
     * 北纬多少度
     */
    public String getNorthLatitude() {
        return northLatitude;
    }
    /**
     * 北纬多少度
     */
    public void setNorthLatitude(String northLatitude) {
        this.northLatitude = northLatitude;
    }
    /**
     * 全球坐标，经纬度
     */
    public String getPosition() {
        return position;
    }
    /**
     * 全球坐标，经纬度
     */
    public void setPosition(String position) {
        this.position = position;
    }

    public String getMemoCode() {
        return memoCode;
    }

    public void setMemoCode(String memoCode) {
        this.memoCode = memoCode;
    }
    /***
     * 0有效1无效
     */
    public Integer getStatus() {
        return status;
    }
    /***
     * 0有效1无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
