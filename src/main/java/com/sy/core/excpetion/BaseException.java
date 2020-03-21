package com.sy.core.excpetion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BaseException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常码
	 */
	private String errorCode;
	
	
	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
