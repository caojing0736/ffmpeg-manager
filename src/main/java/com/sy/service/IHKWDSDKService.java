package com.sy.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.sy.core.excpetion.BusException;
import com.sy.entity.pojo.CamaraInfo;

public interface IHKWDSDKService {

	/**
	 * 设备抓图
	 * @param camaraInfo
	 * @return
	 * @throws BusException
	 */
	String captureJPEGPicture(CamaraInfo camaraInfo) throws BusException;

	/**
	 * 设备抓图，回写IO流
	 * @param camaraInfo
	 * @param response
	 * @return
	 * @throws BusException
	 * @throws IOException
	 */
	String capturePictureIO(CamaraInfo camaraInfo, String formatName, HttpServletResponse response)
			throws BusException, IOException;

}
