package com.sy.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sy.core.excpetion.BusException;
import com.sy.entity.pojo.CamaraInfo;
import com.sy.service.IHKWDSDKService;
import com.sy.util.HKSDKUtil;
import com.sy.util.ImageIOUtil;
import com.sy.util.LoggerUtil;

@Service
public class HKWDSDKServiceImpl implements IHKWDSDKService {

	@Value("${properties.imageWidth}")
	private int imageWidth;

	@Value("${properties.imageHeight}")
	private int imageHeight;

	@Override
	public String captureJPEGPicture(CamaraInfo camaraInfo) throws BusException {

		return HKSDKUtil.captureJPEGPicture(camaraInfo);

	}

	@Override
	public String capturePictureIO(CamaraInfo camaraInfo, String formatName, HttpServletResponse response)
			throws BusException, IOException {

		long t1 = System.currentTimeMillis();

		String picturePath = captureJPEGPicture(camaraInfo);

		long t2 = System.currentTimeMillis();
		LoggerUtil.info("抓取图片耗时：" + (t2 - t1));

		return capturePictureStream(picturePath, formatName, response);
	}

	/**
	 * 输出图片流
	 * 
	 * @param path
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private String capturePictureStream(String path, String formatName, HttpServletResponse response)
			throws IOException {

		BufferedImage bufferedImage = ImageIO.read(new File(path));
		
		long t1 = System.currentTimeMillis();
		
		BufferedImage newbufferedImage = ImageIOUtil.editImage(bufferedImage, imageWidth, imageHeight);
		
		long t2 = System.currentTimeMillis();
		LoggerUtil.info("修改图片耗时：" + (t2 - t1));
		
		ImageIO.write(newbufferedImage, formatName, response.getOutputStream());
		
		long t3 = System.currentTimeMillis();
		LoggerUtil.info("输出图片耗时：" + (t3 - t2));
		
		return "success";
	}

}
