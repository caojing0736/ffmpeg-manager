package com.sy.controller;

import com.sy.entity.RestResponse;

public class BaseRestController {

	/**
	 *    	  模板方法，成功统一返回
	 * @param <T>
	 * @param data
	 * @return
	 */
	protected <T> RestResponse<T> success(T data) {
		
		RestResponse<T> restResponse = RestResponse.newInstance();		
		restResponse.setData(data);
		return restResponse;
			
	}
}
