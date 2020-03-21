package com.sy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sy.core.excpetion.BusException;
import com.sy.entity.RestResponse;
import com.sy.entity.pojo.CamaraInfo;
import com.sy.service.IHKWDSDKService;

/**
 * 海康威视SDK操作
 * @author god
 *
 */
@RestController
@RequestMapping("/hkwsSDK")
public class HKWDSDKController extends BaseRestController{

	@Autowired
	private IHKWDSDKService HKWDSDKServiceImpl;
	
	/**
	 * 抓图，返回图片路径
	 * @return
	 * @throws BusException 
	 */
	@PostMapping("/getPicturePath")
	public RestResponse<String> captureJPEGPicture(@Valid @RequestBody CamaraInfo camaraInfo) throws BusException {
		
		String pathString = HKWDSDKServiceImpl.captureJPEGPicture(camaraInfo);
		
		return success(pathString);
	}
	
	@PostMapping("/getPictureIO")
	public RestResponse<String> capturePictureIO(@Valid @RequestBody CamaraInfo camaraInfo, HttpServletResponse response) throws BusException, IOException {
		
		String pathString = HKWDSDKServiceImpl.capturePictureIO(camaraInfo, "PNG", response);
		
		return success(pathString);
	}
	
	
}
