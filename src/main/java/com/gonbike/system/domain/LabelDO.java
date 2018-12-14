package com.gonbike.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 17:02:21
 */
public class LabelDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//标签代码
	private String labelCode;
	//标签名称
	private String labelName;
	//首页显示的商品数
	private Integer indexQuery;
	//创建人ID
	private Long createUserId;
	//创建人
	private String createUser;
	//创建日期
	private String createDate;
	//最后修改人ID
	private Long modifyUserId;
	//最后修改人
	private String modifyUser;
	//最后修改日期
	private String modifyDate;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：标签代码
	 */
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	/**
	 * 获取：标签代码
	 */
	public String getLabelCode() {
		return labelCode;
	}
	/**
	 * 设置：标签名称
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 * 获取：标签名称
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * 设置：首页显示的商品数
	 */
	public void setIndexQuery(Integer indexQuery) {
		this.indexQuery = indexQuery;
	}
	/**
	 * 获取：首页显示的商品数
	 */
	public Integer getIndexQuery() {
		return indexQuery;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人ID
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建日期
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：最后修改人ID
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * 获取：最后修改人ID
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * 设置：最后修改人
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/**
	 * 获取：最后修改人
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * 设置：最后修改日期
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：最后修改日期
	 */
	public String getModifyDate() {
		return modifyDate;
	}
}
