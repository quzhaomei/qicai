package com.qicai.jspTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import freemarker.template.utility.StringUtil;

public class StringTrimTag extends SimpleTagSupport {
	private String content;// ����
	private Integer length;// ���� ȫ��

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String result = switchStr(content,length);// ��ȡ����
		getJspContext().getOut().print(result);

		super.doTag();
	}

	public static String switchStr(String s,int trimLength) {
		String result=s;
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255){
				length++;
			}else{
				length += 2;
			}
			//�������ˡ�
			if(length>trimLength*2){
				result= s.substring(0,i)+"��";
				break;
			}

		}
		result=StringUtil.HTMLEnc(result);
		return result;
	}
}
