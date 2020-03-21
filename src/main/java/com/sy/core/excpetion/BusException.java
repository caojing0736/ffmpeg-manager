package com.sy.core.excpetion;

public class BusException extends BaseException {

private static final long serialVersionUID = 1L;
	
	public BusException() {
		super();
	}

	public BusException(String message) {
		super(message);
	}

	public BusException(String message, String code) {
		super(message,code);

	}
	
}
