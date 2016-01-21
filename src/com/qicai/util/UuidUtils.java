package com.qicai.util;

import java.util.Date;
import java.util.Random;

public class UuidUtils {
	public static final Random random=new Random();
	/**
	 * 主键生成策略,生成�?��15位不重复的主�?
	 * @return
	 */
	public static String getUUID(){
		return new Date().getTime()+""+(random.nextInt(10))+(random.nextInt(10));
	}
	public static String getImgUUID(){
		return "img"+new Date().getTime()+""+(random.nextInt(10))+(random.nextInt(10));
	}
	public static String getObjectUUID(String code){
		if(code==null){
			code="";
		}
		return code+new Date().getTime()+""+(random.nextInt(10))+(random.nextInt(10));
	}
	//生成11位不重复的商户requiestId
	public static String getRequirstId(){
		String str=System.currentTimeMillis()+"";
		str=str.substring(str.length()-8)+random.nextInt(10)+random.nextInt(10);
		return str;
	}
}
