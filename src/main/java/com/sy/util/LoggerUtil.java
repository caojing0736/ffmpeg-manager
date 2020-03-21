package com.sy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.util.StringUtils;

public class LoggerUtil {

	private static final Logger errorLogger = LoggerFactory.getLogger("ffmpeg-manager-error");
	
	private static final Logger tracer = LoggerFactory.getLogger("ffmpeg-manager-tracer");
	
	private static final Logger debugger = LoggerFactory.getLogger("ffmpeg-manager-debugger");

	public static void info(String msg) {
		tracer.info(msg);
	}

	public static void error(String msg) {
		errorLogger.error(msg);
	}
	
	public static void error(String msg, Throwable ex) {
		String message ="";
		String throwablsMsg = ExceptionUtil.getExceptionStackTrace(ex);
		if(StringUtils.isEmpty(throwablsMsg)){
			message = msg+"  exception happened! ";
		}else{
			message = msg+"  exception happened! detail:"+throwablsMsg;	
		}	
		errorLogger.error(message);
	}
	
	public static void error(String msg, Throwable ex, Marker marker) {
		String message ="";
		String throwablsMsg = ExceptionUtil.getExceptionStackTrace(ex);
		if(StringUtils.isEmpty(throwablsMsg)){
			message = msg+"  exception happened! ";
		}else{
			message = msg+"  exception happened! detail:"+throwablsMsg;	
		}		
		errorLogger.error(marker, message);
	}
	
	public static void debug(String msg){
		debugger.debug(msg);
	}
	
}
