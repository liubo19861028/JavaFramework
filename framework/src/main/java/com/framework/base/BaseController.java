package com.framework.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.comm.extend.SpringWebConfig;

public class BaseController extends SpringWebConfig {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;
	
	// 通用返回对象
	protected BaseResult result;
	protected ResultSearch resultSearch;

	public BaseController() {
	}

}
