package com.gonbike.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 18:33:10
 */
public class CategoriesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String name;
	//
	private String keywords;
	//
	private String frontDesc;
	//
	private Integer parentId;
	//
	private Integer sortOrder;
	//
	private Integer showIndex;
	//
	private Integer isShow;
	//
	private String bannerUrl;
	//
	private String iconUrl;
	//
	private String imageUrl;
	//
	private String wapBannerUrl;
	//
	private String level;
	//
	private Integer type;
	//
	private String frontName;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 获取：
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：
	 */
	public void setFrontDesc(String frontDesc) {
		this.frontDesc = frontDesc;
	}
	/**
	 * 获取：
	 */
	public String getFrontDesc() {
		return frontDesc;
	}
	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：
	 */
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}
	/**
	 * 获取：
	 */
	public Integer getShowIndex() {
		return showIndex;
	}
	/**
	 * 设置：
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 设置：
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * 获取：
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}
	/**
	 * 设置：
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 获取：
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * 设置：
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置：
	 */
	public void setWapBannerUrl(String wapBannerUrl) {
		this.wapBannerUrl = wapBannerUrl;
	}
	/**
	 * 获取：
	 */
	public String getWapBannerUrl() {
		return wapBannerUrl;
	}
	/**
	 * 设置：
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}
	/**
	 * 获取：
	 */
	public String getFrontName() {
		return frontName;
	}
}
