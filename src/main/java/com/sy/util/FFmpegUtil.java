package com.sy.util;

import java.util.Map;

import cc.eguid.commandManager.CommandManager;
import cc.eguid.commandManager.CommandManagerImpl;

public class FFmpegUtil {

	private static CommandManager manager = new CommandManagerImpl();
	
	/**
	 * 开始ffmpeg任务
	 * @param map
	 * @return taskID
	 */
	public static String start(Map<String, String> map) {
		
		return manager.start(map);
		
	}
	
	/**
	 * 停止任务
	 * @param taskID
	 * @return
	 */
	public static boolean stop(String taskID) {
		
		return manager.stop(taskID);
		
	}
	
}
