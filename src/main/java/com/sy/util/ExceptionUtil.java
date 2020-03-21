package com.sy.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


import org.springframework.web.bind.MethodArgumentNotValidException;

import com.sy.core.excpetion.BaseException;
import com.sy.entity.RestResponse;

public class ExceptionUtil {

	/**
	 * 处理异常
	 * @param c
	 * @param e
	 * @return
	 * @author 
	 */
	@SuppressWarnings("unchecked")
	public static <T> RestResponse<T> handleException(T c, Exception e)  {
		//套一层调用，其他地方调用该方法时就不会再有warning
		Class<? extends Object> clazz = c.getClass();
		return (RestResponse<T>) handleException(clazz, e);
	}
	
	/**
	 * 处理异常
	 * @param c
	 * @param e
	 * @return
	 * @author 
	 */
	public static <T> RestResponse<T> handleException(Class<T> c, Exception e)  {		
		Throwable ex = null;
		if(e instanceof BaseException){
			ex = e;
		}else{
			ex = e.getCause();
			if(ex == null) {
				ex = e;
			}
		}
		
		RestResponse<T> restResponse = new RestResponse<T>();
		T t = null;
		try {
			t = c.newInstance();
		} catch (InstantiationException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		}		
		restResponse.setData(t);		
		
		if(ex instanceof BaseException){
			BaseException baseException = (BaseException)ex;
			restResponse.setMsg(baseException.getMessage());
			restResponse.setStatus("-200");
		}else if(ex instanceof MethodArgumentNotValidException) {
			//@NotNull 参数为空的验证异常
			String nullArgMsg = ((((MethodArgumentNotValidException) ex)
					.getBindingResult()).getAllErrors()).get(0).getDefaultMessage();
			restResponse.setMsg(nullArgMsg);
			restResponse.setStatus("-1"); 
		}else{
			restResponse.setMsg("系统异常，请联系管理员或供应商！");
			restResponse.setStatus("-1"); 			   
		}	
		return restResponse;
	}

	

	public static String getExceptionStackTrace(Throwable anexcepObj)
		{
			StringWriter sw = null;
			PrintWriter printWriter = null;
			try{
				if(anexcepObj != null){
					sw = new StringWriter();
					printWriter = new PrintWriter(sw);
					anexcepObj.printStackTrace(printWriter);
					printWriter.flush();
					sw.flush();
					return sw.toString();
				}else
					return null;
			}finally{		
				try{
					if(sw != null)
						sw.close();
					if(printWriter != null)
						printWriter.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
			
		}
	
}
