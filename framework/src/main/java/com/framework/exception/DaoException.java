package com.framework.exception;

 

/**
 * 
 * 接口或类的说明: 领域业务异常
 *
 *
 */
public class DaoException extends BaseCheckedException {
    private static final long serialVersionUID = -3593861868151634148L;

    public DaoException(String msgKey, String message) {
        super(msgKey, message);
    }

    public DaoException(String msgKey, String message, Object[] args) {
        super(msgKey, message, args);
    }

}