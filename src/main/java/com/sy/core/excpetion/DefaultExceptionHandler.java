package com.sy.core.excpetion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.entity.RestResponse;
import com.sy.util.ExceptionUtil;
import com.sy.util.LoggerUtil;




@ControllerAdvice
public class DefaultExceptionHandler{
	
	  /**
	     * 全局异常捕捉处理
	     * @param ex
	     * @return
	     */
	    @ResponseBody
	    @ExceptionHandler(value = Exception.class)
	    public RestResponse<String> errorHandler(Exception ex) {
	    	RestResponse<String> result = RestResponse.newInstance();
	        try {
	        	result = ExceptionUtil.handleException(String.class, ex);	
			} catch (Exception e) {
				LoggerUtil.error("系统错误日志捕捉异常",e);
				result.setStatus("500");
                result.setMsg("系统异常，请联系管理员或供应商！");
			}
	        
	        LoggerUtil.error(result.getMsg(), ex);
	        
	        return result;
	    }
	 
	 
}
