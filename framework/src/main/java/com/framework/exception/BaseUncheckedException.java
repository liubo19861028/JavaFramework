package com.framework.exception;

public class BaseUncheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270618013238197154L;

	private String msgKey;

	public BaseUncheckedException(String msgKey, String message, Throwable throwable) {
		super(message, throwable);
		this.msgKey = msgKey;
	}

	public BaseUncheckedException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BaseUncheckedException(String msgKey, String message) {
		super(message);
		this.msgKey = msgKey;
	}

	public BaseUncheckedException(String message) {
		super(message);
	}

	public BaseUncheckedException(Throwable throwable) {
		super(throwable);
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

}
