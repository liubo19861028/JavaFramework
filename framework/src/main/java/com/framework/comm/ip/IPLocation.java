package com.framework.comm.ip;
/**
 * 
 * 接口或类的说明: 用来封装ip相关信息，目前只有两个字段，ip所在的国家和地区
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class IPLocation {
	public String country;
	public String area;

	public IPLocation() {
		country = area = "";
	}

	public IPLocation getCopy() {
		IPLocation ret = new IPLocation();
		ret.country = country;
		ret.area = area;
		return ret;
	}
}
