package com.sy.entity;

import lombok.Data;

@Data
public class RestResponse<T> {

	/**
	 * 状态码
	 */
	private String status = "-2000";
	
	/**
	 * 消息
	 */
	private String msg = "";
	
	/**
	 * 返回数据
	 */
	private T data;
	
	/**
	 * 实例
	 * @param <T>
	 * @return
	 */
	public static <T> RestResponse<T> newInstance() {
		return new RestResponse<T>();
	}
	
}
