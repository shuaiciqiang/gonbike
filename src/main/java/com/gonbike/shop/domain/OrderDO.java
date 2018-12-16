package com.gonbike.shop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 09:59:45
 */
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单id
	private String orderId;
	//实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	private String payment;
	//支付类型，0、在线支付，1、货到付款
	private Integer paymentType;
	//邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	private Integer postFee;
	//状态：1、待发货 2、已发货  3、待收货 4、已收货
	private Integer status;
	//订单创建时间
	private String createTime;
	//订单更新时间
	private String updateTime;
	//付款时间
	private String paymentTime;
	//发货时间
	private String consignTime;
	//交易完成时间
	private String endTime;
	//交易关闭时间
	private String closeTime;
	//物流名称
	private String shippingName;
	//物流单号
	private String shippingCode;
	//用户id
	private Long userId;
	//买家留言
	private String buyerMessage;
	//买家昵称
	private String buyerNick;
	//0、待评价 1、已评价
	private Integer buyerRate;
	//0、待付款 1、已付款 
	private Integer ispay;
	//是否为有效订单（0、有效 1、无效--删除订单，--取消订单）
	private Integer isdelete;
	//是否存在退款（0、不存在，1、存在）
	private Integer isback;

	/**
	 * 设置：订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}
	/**
	 * 获取：实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	public String getPayment() {
		return payment;
	}
	/**
	 * 设置：支付类型，0、在线支付，1、货到付款
	 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * 获取：支付类型，0、在线支付，1、货到付款
	 */
	public Integer getPaymentType() {
		return paymentType;
	}
	/**
	 * 设置：邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	public void setPostFee(Integer postFee) {
		this.postFee = postFee;
	}
	/**
	 * 获取：邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	public Integer getPostFee() {
		return postFee;
	}
	/**
	 * 设置：状态：1、待发货 2、已发货  3、待收货 4、已收货
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1、待发货 2、已发货  3、待收货 4、已收货
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：订单创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：订单更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：订单更新时间
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：付款时间
	 */
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * 获取：付款时间
	 */
	public String getPaymentTime() {
		return paymentTime;
	}
	/**
	 * 设置：发货时间
	 */
	public void setConsignTime(String consignTime) {
		this.consignTime = consignTime;
	}
	/**
	 * 获取：发货时间
	 */
	public String getConsignTime() {
		return consignTime;
	}
	/**
	 * 设置：交易完成时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：交易完成时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：交易关闭时间
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	/**
	 * 获取：交易关闭时间
	 */
	public String getCloseTime() {
		return closeTime;
	}
	/**
	 * 设置：物流名称
	 */
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	/**
	 * 获取：物流名称
	 */
	public String getShippingName() {
		return shippingName;
	}
	/**
	 * 设置：物流单号
	 */
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	/**
	 * 获取：物流单号
	 */
	public String getShippingCode() {
		return shippingCode;
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
	 * 设置：买家留言
	 */
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	/**
	 * 获取：买家留言
	 */
	public String getBuyerMessage() {
		return buyerMessage;
	}
	/**
	 * 设置：买家昵称
	 */
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	/**
	 * 获取：买家昵称
	 */
	public String getBuyerNick() {
		return buyerNick;
	}
	/**
	 * 设置：0、待评价 1、已评价
	 */
	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}
	/**
	 * 获取：0、待评价 1、已评价
	 */
	public Integer getBuyerRate() {
		return buyerRate;
	}
	/**
	 * 设置：0、待付款 1、已付款 
	 */
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
	/**
	 * 获取：0、待付款 1、已付款 
	 */
	public Integer getIspay() {
		return ispay;
	}
	/**
	 * 设置：是否为有效订单（0、有效 1、无效--删除订单，--取消订单）
	 */
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	/**
	 * 获取：是否为有效订单（0、有效 1、无效--删除订单，--取消订单）
	 */
	public Integer getIsdelete() {
		return isdelete;
	}
	/**
	 * 设置：是否存在退款（0、不存在，1、存在）
	 */
	public void setIsback(Integer isback) {
		this.isback = isback;
	}
	/**
	 * 获取：是否存在退款（0、不存在，1、存在）
	 */
	public Integer getIsback() {
		return isback;
	}
}
