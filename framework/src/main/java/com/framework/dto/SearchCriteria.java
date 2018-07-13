package com.framework.dto; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

/**
 * 
 * 接口或类的说明: 查询条件数据对象标识接口
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class SearchCriteria implements DataTransferObject {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -59811428910124014L;


	private static Logger logger = LoggerFactory.getLogger(SearchCriteria.class);
	 

	/**
	 * 排序字段
	 */
	private String sortField="fcreatetime";

	/**
	 * 排序类型,默认是升序
	 */
	private String orderType="desc";

	 

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	 
	
	
	
}
