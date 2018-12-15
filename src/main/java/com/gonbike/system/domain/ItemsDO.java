package com.gonbike.system.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;



/**
 * 商品表
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 19:09:08
 */
public class ItemsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//商品id，同时也是商品编号
	private Long id;
	//商品标题
	private String title;
	//供应商ID
	private Integer supId;
	//供应商名称
	private String supName;
	//商品卖点
	private String sellPoint;
	//商品价格，单位为：分
	private Integer price;
	//库存数量
	private Integer num;
	//售卖数量限制
	private Integer limitNum;
	//商品图片主图
	private String image;
	//所属类目，叶子类目
	private Long cid;
	//版本号
	private Integer verId;
	//商品状态，1-正常，2-下架，3-删除
	private Integer status;
	//创建人ID
	private Long createUserId;
	//创建人
	private String createUser;
	//创建时间
	private String createTime;
	//修改人ID
	private Long modifyUserId;
	//修改人
	private String modifyUser;
	//更新时间
	private String modifyTime;
	//已字节的形式保存富文本内容
	private byte[] textByte;

	/**
	 * 该商品ID对应的标签集合，是逗号分割的字符串
	 */
	private String itemLabels;
	/**
	 * 该商品ID对应的标签集合，是逗号分割的字符串
	 */
	public String getItemLabels() {
		return itemLabels;
	}
	/**
	 * 该商品ID对应的标签集合，是逗号分割的字符串
	 */
	public void setItemLabels(String itemLabels) {
		this.itemLabels = itemLabels;
	}

	/***
	 * 页面字段
	 */
	private String textByteStr;
	/***
	 * 积分
	 */
	private Integer integral;
	/***
	 * 积分
	 */
	public Integer getIntegral() {
		return integral;
	}
	/***
	 * 积分
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/***
	 * 页面字段
	 */
	public String getTextByteStr() {
		if (textByte!=null){
			try {
				return new String(textByte,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return textByteStr;
	}
	/***
	 * 页面字段
	 */
	public void setTextByteStr(String textByteStr) {
		this.textByteStr = textByteStr;
	}
	/**
	 * 设置：商品id，同时也是商品编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：商品id，同时也是商品编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：商品标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：商品标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	/**
	 * 获取：供应商ID
	 */
	public Integer getSupId() {
		return supId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupName() {
		return supName;
	}
	/**
	 * 设置：商品卖点
	 */
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	/**
	 * 获取：商品卖点
	 */
	public String getSellPoint() {
		return sellPoint;
	}
	/**
	 * 设置：商品价格，单位为：分
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：商品价格，单位为：分
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：库存数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：库存数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：售卖数量限制
	 */
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	/**
	 * 获取：售卖数量限制
	 */
	public Integer getLimitNum() {
		return limitNum;
	}
	/**
	 * 设置：商品图片主图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：商品图片主图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：所属类目，叶子类目
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	/**
	 * 获取：所属类目，叶子类目
	 */
	public Long getCid() {
		return cid;
	}
	/**
	 * 设置：版本号
	 */
	public void setVerId(Integer verId) {
		this.verId = verId;
	}
	/**
	 * 获取：版本号
	 */
	public Integer getVerId() {
		return verId;
	}
	/**
	 * 设置：商品状态，1-正常，2-下架，3-删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：商品状态，1-正常，2-下架，3-删除
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改人ID
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * 获取：修改人ID
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * 设置：修改人
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * 设置：更新时间
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：更新时间
	 */
	public String getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置：已字节的形式保存富文本内容
	 */
	public void setTextByte(byte[] textByte) {
		this.textByte = textByte;
	}
	/**
	 * 获取：已字节的形式保存富文本内容
	 */
	public byte[] getTextByte() {
		if(textByteStr!=null){
			try {
				return textByteStr.getBytes("UTF-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return textByte;
	}
}
