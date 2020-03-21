package com.sy.core;

import java.util.Map;

import com.google.common.collect.Maps;

public class Taskcontainer {

	private static Map<String, String> taskMap = Maps.newConcurrentMap();
	
	/**
	 * 任务是否存在
	 * @param taskID
	 * @return
	 */
	public static boolean contansTask(String taskID) {
		return taskMap.containsKey(taskID);
	}
	
	public static void addTaskID(String taskID) {
		
		taskMap.put(taskID, "1");
		
	}
	
	public static void removeTaskID(String taskID) {
		
		taskMap.remove(taskID);
	}
}
