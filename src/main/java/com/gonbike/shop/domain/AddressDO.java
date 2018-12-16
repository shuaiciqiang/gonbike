package com.gonbike.shop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 10:51:02
 */
public class AddressDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String addressId;
	//用户id
	private Long userId;
	//用户名
	private String userName;
	//电话
	private String tel;
	//省份id
	private String stateId;
	//省份
	private String state;
	//城市id
	private String cityId;
	//城市
	private String city;
	//区域id
	private String districtId;
	//地区
	private String district;
	//详细地址
	private String streetName;
	//邮编
	private String zip;
	//是否设置为默认地址(0为默认)
	private Integer isDefault;
	//接收人姓名
	private String receName;
	//接收人电话
	private String receTel;

	/**
	 * 设置：
	 */
	public void setAddressId(String addressId) {
		if (addressId!=null){
			this.addressId = addressId.replace("undefined","");
		}else {
			this.addressId = addressId;
		}
	}
	/**
	 * 获取：
	 */
	public String getAddressId() {
		return addressId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：省份id
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * 获取：省份id
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * 设置：省份
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：省份
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：城市id
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：城市id
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：区域id
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	/**
	 * 获取：区域id
	 */
	public String getDistrictId() {
		return districtId;
	}
	/**
	 * 设置：地区
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：地区
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：详细地址
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * 获取：详细地址
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * 设置：邮编
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * 获取：邮编
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * 设置：是否设置为默认地址(0为默认)
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：是否设置为默认地址(0为默认)
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置：接收人姓名
	 */
	public void setReceName(String receName) {
		this.receName = receName;
	}
	/**
	 * 获取：接收人姓名
	 */
	public String getReceName() {
		return receName;
	}
	/**
	 * 设置：接收人电话
	 */
	public void setReceTel(String receTel) {
		this.receTel = receTel;
	}
	/**
	 * 获取：接收人电话
	 */
	public String getReceTel() {
		return receTel;
	}
}
