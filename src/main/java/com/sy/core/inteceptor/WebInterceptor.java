package com.sy.core.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sy.core.cache.RequestCacheData;

@Component
public class WebInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		RequestCacheData.getInstance().setCache();

		validateAppKey(request);
		
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//方法完毕以后清除线程变量,必须要保证清除线程变量
    	RequestCacheData.getInstance().removeCache();
	}

	/**
	 * 验证身份令牌
	 * @param request
	 * @return
	 */
	private boolean validateAppKey(HttpServletRequest request){
		
		String appKey = request.getHeader("APPKEY");
		String appToken = request.getHeader("APPTOKEN");
		
		if(appKey == null || appToken == null) return false;
		
		return false;
	}
	
	
}
