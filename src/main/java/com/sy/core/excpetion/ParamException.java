package com.sy.core.excpetion;

/**
 * 参数异常
 * @author god
 *
 */
public class ParamException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	public ParamException() {
		super();
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(String message, String code) {
		super(message,code);

	}


}
