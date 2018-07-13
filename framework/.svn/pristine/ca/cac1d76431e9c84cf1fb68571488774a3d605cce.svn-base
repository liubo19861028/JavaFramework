package com.framework.base;

import java.io.Serializable;

public class BaseSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * 当前页
	 */
	private Integer pageIndex=1;
	
	/*
	 * 每页显示多少条
	 */
	private Integer pageSize=10;
	
	/*
	 * 总共有多少页
	 */
	private Integer pageCount=0;
	
	/*
	 * 排序字段
	 */
	private String orderField;
	
	/**
	 * 表名
	 */
	private String table;
	
	/**
	 * 字段名
	 */
	private String field;
	
	/**
	 * 条件
	 */
	private String where;
	
	/**
	 * 总行数
	 */
	private Integer rowCount;
	

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public SearchOrder getOrderType() {
		return orderType;
	}

	public void setOrderType(SearchOrder orderType) {
		this.orderType = orderType;
	}


	/**
	 * 排序方式，默认是升序
	 */
	private SearchOrder orderType = SearchOrder.DESC;

	/**
	 * 多重排序条件
	 */
	//private LinkedHashMap<String, SearchOrder> orderFieldsAndOrder;


	public enum SearchOrder {
		/** 升序 */
		ASC,
		/** 降序 */
		DESC
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		if(rowCount%pageSize==0) {
			return rowCount/pageSize;
		}else {
			return ((Integer)(rowCount/pageSize))+1;
		}
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
}
