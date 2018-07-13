package com.framework.comm.ip;
/**
 * 
 * 接口或类的说明: 一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class IPEntry {

	public String beginIp;
	public String endIp;
	public String country;
	public String area;

	/**
	 * 构造函数
	 */

	public IPEntry() {
		beginIp = endIp = country = area = "";
	}

	public String toString() {
		return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
	}
}
