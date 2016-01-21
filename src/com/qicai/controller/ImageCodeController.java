package com.qicai.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * ��֤��
 * @author Administrator
 *
 */
@Controller
public class ImageCodeController {
	private int width = 120;
	// ��֤��ͼƬ�ĸ߶ȡ�
	private int height = 30;
	// ��֤���ַ�����
	private int codeCount = 6;
	private int x = 15;
	// ����߶�
	private int fontHeight=25;
	private int codeY=25;
	
	private Random random = new Random();
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };


	@RequestMapping(value = "/getCode")
	public void getCode(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String code=request.getParameter("code");
		if(code==null){return;}
		// ����ͼ��buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// ����һ���������������
		
		// ��ͼ�����Ϊ��ɫ
		g.setColor(new Color(230, 230, 230));
		g.fillRect(0, 0, width, height);
		// �������壬����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// �������塣
		g.setFont(font);
		// ���߿�
//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, width - 1, height - 1);
		// �������160�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽��
		g.setColor(Color.BLACK);
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// randomCode���ڱ��������������֤�룬�Ա��û���¼�������֤��
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// �������codeCount���ֵ���֤�롣
		for (int i = 0; i < codeCount; i++) {
			// �õ������������֤�����֡�
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			// �����������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ��
			red = random.nextInt(200);
			green = random.nextInt(200);
			blue = random.nextInt(200);
			// �������������ɫ����֤����Ƶ�ͼ���С�
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
			// ���������ĸ�����������һ��
			randomCode.append(strRand);
		}
		// ����λ���ֵ���֤�뱣�浽Session�С�
		HttpSession session = request.getSession();
		session.setAttribute(code, randomCode.toString());
		// ��ֹͼ�񻺴档
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// ��ͼ�������Servlet������С�
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//��֤��֤��
	@RequestMapping(value = "/checkCode")
	public void checkCode(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String code=request.getParameter("code");
		String value=request.getParameter("value");
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			if(code!=null&&value!=null&&value.equalsIgnoreCase(
					(String)request.getSession().getAttribute(code))
					){
					pw.write("true");
			}else{
				pw.write("false");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(pw!=null)pw.close();
		}
	}

}
