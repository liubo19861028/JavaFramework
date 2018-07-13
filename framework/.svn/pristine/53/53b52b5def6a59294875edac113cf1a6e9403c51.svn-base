package com.framework.base;

import java.io.Serializable;

public class BaseResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private Boolean success;
	private Object data;
	
	public BaseResult(BaseResultCode code, Boolean  status, Object data,String message) {
		super();
		this.code=code.toString();
		this.success=status;
		this. data= data;
		this.message=message;
	}
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(BaseResultCode code) {
		this.code = code.toString();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 描述：产生一个成功不带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genSuccessResultInfo() {
		return new BaseResult(BaseResultCode.SUCCESS,true, null,null);
	}

	/**
	 *
	 * 描述：产生一个成功带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genSuccessResultInfo(Object data,String message) {
		return new BaseResult(BaseResultCode.SUCCESS,true,data,message);
	}
	
	
	/**
	 *
	 * 描述：产生一个成功带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genSuccessResultInfo(String message) {
		return new BaseResult(BaseResultCode.SUCCESS,true, null,message);
	}
	
	/**
	 *
	 * 描述：产生一个成功带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genSuccessResultInfo(Object data) {
		return new BaseResult(BaseResultCode.SUCCESS,true, data,null);
	}
	
	
	/**
	 *
	 * 描述：产生一个失败不带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genErrorResultInfo() {
		return new BaseResult(BaseResultCode.ERROR,false, null,null);
	}
	/**
	 *
	 * 描述：产生一个失败带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genErrorResultInfo(Object data,String message) {
		return new BaseResult(BaseResultCode.ERROR,false, data,message);
	}
	
	/**
	 *
	 * 描述：产生一个失败带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genErrorResultInfo(String message) {
		return new BaseResult(BaseResultCode.ERROR,false, null,message);
	}
	/**
	 *
	 * 描述：产生一个失败带参数的结果
	 * @author yisheng.lu@yihaojiaju.com
	 * @return
	 */
	public static BaseResult genErrorResultInfo(Object data) {
		return new BaseResult(BaseResultCode.ERROR,false, data,null);
	}
}
