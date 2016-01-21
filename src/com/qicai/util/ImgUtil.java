package com.qicai.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*******************************************************************************
 * 缩略图类（�?用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换�?具体使用方法
 * compressPic(大图片路�?生成小图片路�?大图片文件名,生成小图片文�?生成小图片宽�?生成小图片高�?是否等比缩放(默认为true))
 */
@SuppressWarnings("restriction")
public class ImgUtil {
	// 图片处理
	public static void compressPic(Image img,String path) {
		try {
			// 获得源文�?
			if(img==null){
				return;
			}
			// 判断图片格式是否正确
				int newWidth=img.getWidth(null);
				int newHeight=img.getHeight(null);
				// 判断是否是等比缩�?
					// 为等比缩放计算输出的图片宽度及高�?
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				/*
				 * Image.SCALE_SMOOTH 的缩略算�?生成缩略图片的平滑度�?优先级比速度�?生成的图片质量比较好 但�?度慢
				 */
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(path);
				// JPEGImageEncoder可�?用于其他图片类型的转�?
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
