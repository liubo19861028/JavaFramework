package com.framework.comm.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* 
* 接口或类的说明: http cookie工具类
*
* <br>==========================
* <br> 公司：南京壹号家信息科技有限公司
* <br> 开发：yisheng.lu@yihaojiaju.com
* <br> 版本：1.0
* <br> 创建时间：2017-11-15 上午9:25:35
* <br>==========================
*
*/
public class CookieUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * 更新Cookie中指定项
	 * 
	 * @param request
	 * @param response
	 * @param expiry
	 */
	public static void updateCookieMaxAge(HttpServletRequest request,
			HttpServletResponse response, String uri, String key, Integer expiry) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || StringUtil.isEmpty(key))
			return;
		for (Cookie cookie : cookies) {
			if (key.equals(cookie.getName())) {
				if (expiry != null)
					cookie.setMaxAge(expiry);
				if (StringUtil.isEmpty(uri))
					cookie.setPath("/");
				else
					cookie.setPath(uri);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * 更新Cookie中指定项
	 * 
	 * @param request
	 * @param response
	 * @param expiry
	 */
	public static void updateCookieMaxAge(HttpServletRequest request,
			HttpServletResponse response, String key, Integer expiry) {
		updateCookieMaxAge(request, response, null, key, expiry);
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    if(maxAge>0)  cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static String getCookieValueByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie.getValue();
	    }else{
	        return null;
	    }   
	}
	
	/**
	 * 获取客户端cookies中，某个cookie的值
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String key) {
		if (cookies == null || key == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (key.equals(cookies[i].getName())) {
				if (logger.isDebugEnabled()) {
					logger.debug("Cookies[{}]:[{}={}]", new Object[] { i, key,	cookies[i].getValue() });
				}
				return cookies[i].getValue();
			}
		}
		return null;
	}
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
