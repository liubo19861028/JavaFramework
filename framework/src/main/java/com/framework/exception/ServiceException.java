package com.framework.exception;

 

public class ServiceException extends BaseCheckedException {

	private static final long serialVersionUID = -8470156770391713503L;

	public ServiceException(String msgKey, String message) {
        super(msgKey, message);
    }

    public ServiceException(String msgKey, String message, Object[] args) {
        super(msgKey, message, args);
    }

}
