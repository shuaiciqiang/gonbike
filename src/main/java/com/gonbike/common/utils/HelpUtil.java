package com.gonbike.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * 基础工具类
 * @author shuaige
 * @date 2018-12-13
 */
public class HelpUtil {

	/**
	 * 本机IP地址
	 */
	public static String serverIP="";
	/**
	 * 操作系统类型，默认是0=windows,1=linux
	 */
	private static int osType=-1;
	
	public static String getGUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}


	/**
	 * 简单方法：获取系统类型，0代表是windows，1是linux系统
	 * @return
	 */
	public static int getOsType(){
		if(osType==-1){
			String vTemp=System.getProperty("os.name").toLowerCase();
			if (vTemp.indexOf("window")>-1){
				osType=0;
			}else{
				osType=1;
			}
			return osType;
		}else {
			return osType;
		}
	}
	/**
	 * 获取系统当前的小时数
	 * @return
	 */
	public static Integer getHour() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("HH");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}
	
	/**
	 * 获取系统当前的小时数
	 * @return
	 */
	public static int getSysHour() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("HH");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}
	/**
	 * 返回2018-12-12 12:15:18格式
	 * @return
	 */
	public static String getDateTime() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return vDateTime;
	}
	/**
	 * 获取系统时间yyyyMMddHHmmss
	 * @return
	 */
	public static String getDateTime2() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyyMMddHHmmss");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return vDateTime;
	}
	/**
	 * 获取系统时间yyyyMMdd
	 * @return
	 */
	public static String getShortDate() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyyMMdd");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return vDateTime;
	}
	/**
	 * 返回2018-12-12格式
	 * @return
	 */
	public static String getDate() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyy-MM-dd");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return vDateTime;
	}
	/**
	 * 返回年份的最后2位
	 * @return
	 */
	public static Integer getYearShort(){
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yy");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}
	/**
	 * 返回年份
	 * @return
	 */
	public static Integer getYear() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyy");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}

	/**
	 * 返回月份
	 * @return
	 */
	public static Integer getMonth() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("MM");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}

	/**
	 * 返回几号
	 * @return
	 */
	public static Integer getDay() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("dd");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}
	/**
	 * 返回201812
	 * @return
	 */
	public static Integer getYearAndMonth() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyyMM");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return Integer.valueOf(vDateTime);
	}
	/**
	 * 返回2018-12
	 * @return
	 */
	public static String getYearMonth() {
		String vDateTime = "";
		SimpleDateFormat vTime = new SimpleDateFormat("yyyy-MM");
		Date vOrderDateTemp = new Date();
		vDateTime = vTime.format(vOrderDateTemp);
		return vDateTime;
	}
	/**
	 * 返回今天是星期几，1代表星期一，2代表星期二（要防止系统时钟跳跃或时区错误）
	 * @return
	 */
	public static Integer getSysWeek(){
		Date date=new Date(); 
	    SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
	    String vTemp=dateFm.format(date);
	    if (vTemp.equals("星期一")){
	    	return 1;
	    }else if (vTemp.equals("星期二")){
	    	return 2;
	    }else if (vTemp.equals("星期三")){
	    	return 3;
	    }else if (vTemp.equals("星期四")){
	    	return 4;
	    }else if (vTemp.equals("星期五")){
	    	return 5;
	    }else if (vTemp.equals("星期六")){
	    	return 6;
	    }else{
	    	return 7;
	    }    	    
	}
	
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 是否整型 数字
	 * @param s
	 * @return
	 */
    public static boolean isInteger(String s){    
        if((s != null)&&(s!=""))    
         return s.matches("^[0-9]*$");    
        else    
         return false;    
    }    
    /**  
    * 判断字符串是否是浮点数  
    */    
    public static boolean isDouble(String value) {    
        try {    
           Double.parseDouble(value);    
           if (value.contains("."))    
               return true;    
           return false;    
        } catch (NumberFormatException e) {    
           return false;    
        }    
    }  
    
	/**
	 * 是否是数字，可能是整型，可能带小数的数字
	 * @param s
	 * @return
	 */
    public static boolean isNumber(String s){    
    	if (s==null||s.trim().equals("")){
    		return false;
    	}
        try{
        	Float.valueOf(s);
        }catch(Exception e){
        	return false;
        }
        return true;
    }   
    
    
    
    /**
     * 字符串型浮点数金额转换为分，主要用于保存到数据库
     * @param num1  12.56
     * @return 1256
     */
    public static Integer mulForMoney(String num1){ 
    	if(num1==null){
    		num1="0";
    	}
    	 
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal("100");
    	return a.multiply(b).intValue();    	
    } 
    /**
     * 数字型相乘的方法
     * @param num1
     * @param num2
     * @return
     */
    public static Integer mul(Integer num1,Integer num2){    
    	if(num1==null){
    		num1=0;
    	}
    	if (num2==null){
    		num2=0;
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return a.multiply(b).intValue();  
    }
    /**
     * 字符串型的数字除法的方法
     * @param num1
     * @param num2
     * @return
     */
    public static String divide(String num1,String num2){    
    	if(num1==null){
    		num1="0";
    	}
    	if (num2==null){
    		num2="0";
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
   
    	BigDecimal c=a.divide(b, 2, RoundingMode.HALF_UP);
    	return c.toString();  
    }
    
    /**
     * 字符串型的数字除法的方法
     * @param num1
     * @param num2
     * @return
     */
    public static Integer divide(Integer num1,Integer num2){    
    	if(num1==null){
    		num1=0;
    	}
    	if (num2==null){
    		num2=0;
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
   
    	BigDecimal c=a.divide(b, 2, RoundingMode.HALF_UP);
    	return c.intValue();  
    }
    
    /**
     * 字符串型的数字相乘的方法
     * @param num1
     * @param num2
     * @return
     */
    public static Integer mulByStr(String num1,String num2){    
    	if(num1==null){
    		num1="0";
    	}
    	if (num2==null){
    		num2="0";
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return a.multiply(b).intValue();  
    }
    /**
     * 字符串类型的数字整型相加的方法
     * @param num1
     * @param num2
     * @return
     */
    public static String add(String num1,String num2){    	
    	if(num1==null){
    		num1="0";
    	}
    	if (num2==null){
    		num2="0";
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return String.valueOf(a.add(b).intValue());  
    }
    /**
     * 字符串类型的数字整型相减的方法
     * @param num1
     * @param num2
     * @return
     */
    public static String subtract(String num1,String num2){    	
    	if(num1==null){
    		num1="0";
    	}
    	if (num2==null){
    		num2="0";
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return String.valueOf(a.subtract(b).intValue());  
    }
    /**
     * 数字型相加的方法
     * @param num1
     * @param num2
     * @return
     */
    public static Integer add(Integer num1,Integer num2){    	
    	if(num1==null){
    		num1=0;
    	}
    	if (num2==null){
    		num2=0;
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return a.add(b).intValue();  
    }
    /**
     * 数字型相减的方法
     * @param num1
     * @param num2
     * @return
     */
    public static Integer subtract(Integer num1,Integer num2){    	
    	if(num1==null){
    		num1=0;
    	}
    	if (num2==null){
    		num2=0;
    	}
    	BigDecimal a = new BigDecimal(num1);
    	BigDecimal b = new BigDecimal(num2);
    	return a.subtract(b).intValue();  
    }
	public static void main(String[] args){
		System.out.println(divide(5,2));
	}
}
