package com.qicai.util;

import java.util.Date;

/**
 * sqlÓï¾äĞÔÄÜ²âÊÔÀà
 * @author Administrator
 */
public class SqlTimeTest {
	private Date date;
	private long total=0;
	public long startTest(){
		if(date==null){
			date=new Date();
			return 0;
		}
		long second=System.currentTimeMillis()-date.getTime();
		date=new Date();
		total+=second;
		return second;
	}
	public long getTotal(){
		return total;
	}
}
