package com.sy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sy.entity.RestResponse;
import com.sy.entity.pojo.CamaraInfo;
import com.sy.core.excpetion.ParamException;
import com.sy.service.IStreamTaskService;

@RestController
@RequestMapping("/ffmpeg")
public class StreamTaskController extends BaseRestController {

	@Autowired
	private IStreamTaskService StreamTaskService;
	
	/**
	 * 开启ffmpeg对摄像机的推拉流服
	 * @param camaraInfo
	 * @return
	 * @throws ParamException 
	 */
	@PostMapping("/startTaskByCamaraInfo")
	public RestResponse<String> startTaskByCamaraInfo(@Valid @RequestBody CamaraInfo camaraInfo) throws Exception {
		
		String data = StreamTaskService.startTaskByCamaraInfo(camaraInfo);
		
		return success(data);
	}
	
	@PostMapping("/stopTaskByTaskID")
	public RestResponse<String> stopTaskByTaskID(String varTaskID) throws ParamException {
		
		if(StringUtils.isEmpty(varTaskID))throw new ParamException("varTaskID不能为空");
		
		String data = StreamTaskService.stopTaskByTaskID(varTaskID) ? "success" : "fail";
		
		return success(data);
	}
	
	
}
