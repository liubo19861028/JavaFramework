package com.framework.exception;

import java.util.Arrays;

/**
 * 异常父类
 * @author
 *
 */
public class BaseCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7434573530407105096L;
	private final String msgKey;

    public String getMsgKey() {
        return msgKey;
    }

    private Object[] args;

    public Object[] getArgs() {
        return args;
    }

    public BaseCheckedException(String msgKey) {
        super();
        this.msgKey = msgKey;
    }

    public BaseCheckedException(String msgKey, String message) {
        super(message);
        this.msgKey = msgKey;
    }

    public BaseCheckedException(String msgKey, String message, Object[] args) {
        super(message);
        this.msgKey = msgKey;
        this.args = args;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "[msgKey=" + msgKey + ", args=" + Arrays.toString(args) + "]";
    }

}
