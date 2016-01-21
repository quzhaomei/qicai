package com.qicai.controller;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
@Repository
public class SystemInit {
	private String imgPath=System.getProperty("catalina.base")+"/imgStore/";
	@PostConstruct
	public void init(){
		File imgFile=new File(imgPath);
		if(!imgFile.exists()){
			imgFile.mkdirs();
		}
	}
	public  String getImgPath() {
		return imgPath;
	}
	
}
