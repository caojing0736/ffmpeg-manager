package com.sy.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageIOUtil {

	/**
	 * 修改图片大小
	 * 
	 * @param sourceImage
	 * @param FORMAT_NAME
	 * @param width
	 * @param height
	 */
	public static BufferedImage editImage(BufferedImage sourceImage, int width, int height) {

		int sourceWidth = sourceImage.getWidth(null);
		int sourceHeight = sourceImage.getHeight(null);
		// 压缩LOGO
		if (sourceWidth > width) {
			sourceWidth = width;
		}
		if (sourceHeight > height) {
			sourceHeight = height;
		}
		Image image = sourceImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();

		return toBufferedImage(image);

	}

	/**
	 * image转换为BufferedImage
	 * 
	 * @param img
	 * @return
	 */
	private static BufferedImage toBufferedImage(Image img) {

		if (img instanceof BufferedImage)
			return (BufferedImage) img;

		BufferedImage bufImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		Graphics2D bGr = bufImage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bufImage;

	}
}
