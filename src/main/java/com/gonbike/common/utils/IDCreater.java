package com.gonbike.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 
 * 简单的ID号生成器，19位长度的数字字符串，支持分布式生成，1毫秒最多生成999个ID
 * @author shuaige
 * @remark 系统时钟有回拨或跳跃的可能，此问题要注意服务器
 *
 */
public class IDCreater {
	private static IDCreater idWorker=new IDCreater();
	/**
	 * 计数器，一毫秒产生999个ID
	 */
	private static Integer number=0;
	/**
	 * 上一个计数器周期时间点
	 */
	private static long lastTime=0L;
	/**
	 * ID生成器识别代码，一般是IP,192.168.1.77的最后一段，，补全为077，
	 */
	private static String workerId="000";
	/**
	 * 开关。标识是否设置过了workerId
	 */
	private static boolean isCan=false;
	
	private String getId(){
		synchronized(IDCreater.class){
			if(isCan==false){
				InetAddress addr=null;
				try {
					addr = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}  
		        String ip=addr.getHostAddress().toString();
		        workerId=ip.substring(ip.lastIndexOf(".")+1);
		        if (workerId.length()==1){
		        	workerId="00"+workerId;
		        }else if (workerId.length()==2){
		        	workerId="0"+workerId;
		        }
			}
			long curTime=getSysTime();
			if (lastTime==0){
				lastTime=getSysTime();
			}
			if (curTime==lastTime){
				number++;
			}else{
				lastTime=getSysTime();
				number=0;
			}
			if (number==999){
				try{
					number=0;
					return getNextID();
				}catch(Exception e){
				
				}
			}
			String vStr=null;
			if (number.toString().length()==1){
				vStr=String.valueOf(curTime)+workerId+"00"+number;
			}else if (number.toString().length()==2){
				vStr=String.valueOf(curTime)+workerId+"0"+number;
			}else{
				vStr=String.valueOf(curTime)+workerId+number; 
			}
			return vStr;
		}
	}
	
	private long getSysTime(){
		return System.currentTimeMillis();
	}
	/**
	 * 返回一个19长度的数字字符串，13位时间+3位机器识别码+3位序列号
	 * @return  1536889126299077001
	 */
	public static String getNextID(){
		return idWorker.getId();
	}
	
	public static void main(String... args){		
		for(int i=0;i<10000;i++){
			System.out.println(idWorker.getNextID());
		}
	}
}
