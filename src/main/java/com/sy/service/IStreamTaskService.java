package com.sy.service;

import com.sy.entity.pojo.CamaraInfo;
import com.sy.core.excpetion.ParamException;

public interface IStreamTaskService {

	/**
	 * 开启ffmpeg对摄像机的推拉流进程
	 * @param camaraInfo
	 * @return
	 * @throws ParamException 
	 * @throws Exception 
	 */
	String startTaskByCamaraInfo(CamaraInfo camaraInfo) throws Exception;

	/**
	 * 关闭ffmpeg对摄像机的推拉流进程
	 * @param taskID
	 * @return
	 */
	boolean stopTaskByTaskID(String taskID);
}
