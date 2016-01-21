package com.qicai.jspTag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qicai.bean.Advertise;
import com.qicai.dto.AdvertiseDTO;
import com.qicai.service.AdvertiseService;

/**
 * 广告标签
 */
public class AdTag extends SimpleTagSupport {
	protected AdvertiseService advertiseService;
	private String pageUrl;//页面的url  自动生成
	private String imgPath;//图片路径   自动生成
	private String href;
	private String code;//广告在页面的标志 唯一，unique  50
	private Integer width;//宽度   5
	private Integer height;//高度 5
	private String descript;//广告描述  100
	
	@Override
	public void doTag() throws JspException, IOException {
		setNewsService();//初始化
		//第一次去查询该code对应的广告，
		AdvertiseDTO advertiseDTO=advertiseService.findByCode(code);
		if(advertiseDTO==null){//保存
			Advertise saveParam=new Advertise();
			saveParam.setPageUrl(pageUrl);
			saveParam.setHref(href);
			saveParam.setCode(code);
			saveParam.setCreateDate(new Date());
			saveParam.setDescript(descript);
			saveParam.setHeight(height);
			saveParam.setWidth(width);
			saveParam.setImgPath("/qicai/images/advertise.png");//默认占位
			advertiseService.save(saveParam);
			advertiseDTO=advertiseService.findByCode(code);
		}
		getJspContext().getOut().print(
		"<a href='"+advertiseDTO.getHref()+"' target='_blank'><img id='"+code+"' " +
				" src='"+advertiseDTO.getImgPath()+"'" +
				" style='width:"+advertiseDTO.getWidth()+"px;height:"+
				advertiseDTO.getHeight()+"px';/></a>");
		
		super.doTag();
	}

	public void setNewsService() {
		PageContext pageContext = (PageContext) this.getJspContext();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		pageUrl=(String) request.getAttribute("pageUri");
		String param=request.getQueryString();
		if(param!=null){
			pageUrl+="?"+param;
		}
		pageUrl+="#"+code;
        ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.advertiseService = (AdvertiseService) wac.getBean("advertiseService");
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	
}
