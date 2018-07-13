package com.framework.comm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * 
 * 接口或类的说明: url工具类
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class UrlUtil {
	public static final String ENCODING_UTF8 = "UTF-8";
	private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class);

	/**
	 * 处理URL参数串，把参数名和参数值转化成键值对的形式
	 */
	public static Map<String, String> convertParamsString2Map(String paramsString) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		String[] tempParams = paramsString.split("&");
		String name = null;
		String value = null;
		if (tempParams != null && tempParams.length > 0) {
			for (int i = 0; i < tempParams.length; i++) {
				String[] tempArray = tempParams[i].split("=");
				if (tempArray.length == 2) {
					name = tempArray[0];
					value = tempArray[1];
				} else {
					if (tempArray.length != 1)
						continue;
					else {
						name = tempArray[0];
						value = "";
					}
				}
				if (!StringUtil.isEmpty(name)) {
					paramsMap.put(name, value);
				}
			}
		}
		return paramsMap;
	}

	/**
	 * 将字符串的URL参数转换成Map形式的键值对
	 * 
	 * @param urlParameterString
	 *            可以是带参数的URL，也可以是纯参数字符串
	 * @return 参数Map对象
	 */
	public static Map<String, String> convertUrlParameterStringToMap(String urlParameterString) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (urlParameterString == null)
			return paramMap;
		if (urlParameterString.indexOf("?") != -1)
			urlParameterString = urlParameterString.substring(urlParameterString.indexOf("?") + 1);
		String[] userInfoParam = urlParameterString.split("&");
		String[] kv = null;
		for (String _str : userInfoParam) {
			if (StringUtil.isEmpty(_str))
				continue;
			kv = _str.split("=");
			if (kv.length > 2)
				paramMap.put(kv[0], _str.substring(_str.indexOf("=") + 1));
			else if (kv.length == 2)
				paramMap.put(kv[0], kv[1]);
			else if (kv.length == 1 && kv[0] != null)
				paramMap.put(kv[0], "");
			else
				logger.warn(_str);
		}
		return paramMap;
	}

	/**
	 * 把Map转成URL的参数串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String convertParamsMap2String(Map<String, String> paramMap) {
		return convertParamsMap2String(paramMap, true);
	}

	public static String convertParamsMap2String(Map<String, String> paramMap, boolean isEncode) {
		if (paramMap == null)
			return "";

		StringBuilder sb = new StringBuilder("");
		String value = null;
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			if (!StringUtil.isEmpty(sb.toString()))
				sb.append("&");
			value = entry.getValue() == null ? "" : entry.getValue();
			if (isEncode) {
				try {
					sb.append(entry.getKey()).append("=").append(URLEncoder.encode(value, ENCODING_UTF8));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				sb.append(entry.getKey()).append("=").append(value);

		}
		return sb.toString();
	}

	/**
	 * 获取URL参数串中指定参数的值
	 * 
	 * @param paramsString
	 * @param paramName
	 * @return
	 */
	public static String getParamValue(String paramsString, String paramName) {
		return convertParamsString2Map(paramsString).get(paramName);
	}

	/**
	 * 从参数串中移除某个参数(不会自动编码解码)
	 * 
	 * @param paramsString
	 * @param paramName
	 * @return
	 */
	public static String removeParam(String paramsString, String paramName) {
		Map<String, String> map = convertParamsString2Map(paramsString);
		if (!StringUtil.isEmpty(map.get(paramName))) {
			map.remove(paramName);
		}
		return convertParamsMap2String(map, false);
	}

	/**
	 * 从参数串中移除某些参数(不会自动编码解码)
	 * 
	 * @param paramsString
	 * @param paramName
	 * @return
	 */
	public static String removeParams(String paramsString, String[] paramNames) {
		if (paramNames == null || paramNames.length == 0)
			return paramsString;
		Map<String, String> map = convertUrlParameterStringToMap(paramsString);
		for (String paraName : paramNames) {
			if (map.containsKey(paraName))
				map.remove(paraName);
		}
		return convertParamsMap2String(map, false);
	}

	/**
	 * 从URL中移除某个参数
	 * 
	 * @param paramsString
	 * @param paramName
	 * @return
	 */
	public static String removeParamFromUrl(String url, String paramName) {
		return removeParamsFromUrl(url, new String[] { paramName });
	}

	/**
	 * 从URL中移除某个参数
	 * 
	 * @param paramsString
	 * @param paramName
	 * @return
	 */
	public static String removeParamsFromUrl(String url, String[] paramNames) {
		if (StringUtil.isEmpty(url) || paramNames == null || paramNames.length == 0)
			return url;
		String[] data = url.split("\\?");
		if (data.length < 2)
			return url;
		String paramsString = removeParams(data[1], paramNames);
		if (StringUtil.isEmpty(paramsString)) {
			return data[0];
		}
		return data[0] + "?" + paramsString;
	}

	/**
	 * 返回已经排好序（按照参数名的英文字母升序）的参数名字的列表
	 * 
	 * @param paramsString
	 * @return
	 */
	public static List<String> getSortParamsName(String paramsString) {
		List<String> paramsNameList = new ArrayList<String>();
		String[] tempParams = paramsString.split("&");
		if (tempParams != null && tempParams.length > 0) {
			for (int i = 0; i < tempParams.length; i++) {
				if (!StringUtil.isEmpty(tempParams[i].split("=")[0])) {
					paramsNameList.add(tempParams[i].split("=")[0]);
				}
			}
			Collections.sort(paramsNameList);// key排序
		}
		return paramsNameList;
	}

	/**
	 * 处理URL参数串，对参数的顺序进行重新排序，按照参数名的英文字母升序
	 * 
	 * @param paramsString
	 *            格式："b=XX & d=XX & c=XX & a=XX" (乱序)
	 * @return sortParamsString 格式："a=XX & b=XX & c=XX & d=XX" (升序)
	 */
	public static String getSortParamsString(String paramsString) {
		return getSortParamsString(paramsString, true);
	}

	public static String getSortParamsString(String paramsString, boolean isDecode) {
		StringBuilder sortParamsString = new StringBuilder("");
		List<String> sortParamsNameList = getSortParamsName(paramsString);
		Map<String, String> paramsMap = convertParamsString2Map(paramsString);
		for (String paramName : sortParamsNameList) {
			if (!StringUtil.isEmpty(paramName)) {
				if (!StringUtil.isEmpty(sortParamsString.toString()))
					sortParamsString.append("&");
				sortParamsString.append(paramName + "=" + paramsMap.get(paramName));
			}
		}

		if (isDecode == false)
			return sortParamsString.toString();
		return decode(sortParamsString.toString());
	}

	/**
	 * 在URL后面添加参数
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String appendParams(String url, String params) {
		if (StringUtil.isEmpty(url)) {
			logger.warn("url is empty!");
			return null;
		}
		if (StringUtil.isEmpty(params))
			return url;
		if (url.indexOf("?") > -1) {// URL中含有?，即已经有其他的参数了
			return url + "&" + params;
		} else {
			return url + "?" + params;
		}
	}

	/**
	 * 采用UTF-8字符集的URLEncoder方法
	 * 
	 * @param arg
	 * @return
	 */
	public static String encode(String arg) {
		return encode(arg, ENCODING_UTF8);
	}

	/**
	 * 采用UTF-8字符集的URLEncoder方法
	 * 
	 * @param arg
	 * @param enc
	 * @return
	 */
	public static String encode(String arg, String enc) {
		if (arg == null || "".equals(arg))
			return arg;
		try {
			return URLEncoder.encode(arg, enc);
		} catch (UnsupportedEncodingException e) {
			logger.error("对[" + arg + "]进行URLEncoder.encode操作时出现异常", e);
			return arg;
		}
	}

	/**
	 * 采用UTF-8字符集的URLDecoder方法
	 * 
	 * @param arg
	 * @return
	 */
	public static String decode(String arg) {
		return decode(arg, ENCODING_UTF8);
	}

	/**
	 * 采用UTF-8字符集的URLDecoder方法
	 * 
	 * @param arg
	 * @param enc
	 * @return
	 */
	public static String decode(String arg, String enc) {
		if (arg == null || "".equals(arg))
			return arg;
		try {
			return URLDecoder.decode(arg, enc);
		} catch (UnsupportedEncodingException e) {
			logger.error("对[" + arg + "]进行URLEncoder.decode操作时出现异常", e);
			return arg;
		}
	}

	/**
	 * 获取排序后的参数字符串
	 * 
	 * @param paramsMap
	 *            参数Map对象
	 * @return
	 */
	public static String getSortParamsString(Map<String, String> paramsMap) {
		return getSortParamsString(paramsMap, false);
	}

	/**
	 * 获取排序后的参数字符串
	 * 
	 * @param paramsMap
	 *            参数Map对象
	 * @param isEncode
	 *            是否进行URLEncoder编码
	 * @return
	 */
	public static String getSortParamsString(Map<String, String> paramsMap, boolean isEncode) {
		if (paramsMap == null || paramsMap.isEmpty())
			return null;

		List<String> sortParamsNameList = new ArrayList<String>(paramsMap.keySet());
		Collections.sort(sortParamsNameList);

		StringBuilder sortParamsString = new StringBuilder();
		String tmpValue = null;
		for (String paramName : sortParamsNameList) {
			if (StringUtil.isEmpty(paramName))
				continue;
			tmpValue = paramsMap.get(paramName) == null ? "" : paramsMap.get(paramName);
			sortParamsString.append(paramName).append("=").append(isEncode ? encode(tmpValue) : tmpValue).append("&");
		}

		if (sortParamsString.length() > 1) {
			int length = sortParamsString.length();
			sortParamsString.delete(length - 1, length);
		}

		return sortParamsString.toString();
	}

	/**
	 * 
	 * 描述：获取远程请求者的信息
	 * 
	 * @param req
	 * @return
	 */
	public static String getRemoteRequesterMessage(HttpServletRequest req) {
		String pathInfo = req.getPathInfo();
		pathInfo = StringUtil.isEmpty(pathInfo) ? "" : pathInfo.substring(1);
		StringBuilder invokerMsg = new StringBuilder();
		invokerMsg.append("【");
		invokerMsg.append("remoteAddr=").append(NetUtil.getRemoteIP(req));
		invokerMsg.append(",remoteUser=").append(req.getRemoteUser());
		invokerMsg.append(",remoteHost=").append(req.getRemoteHost());
		invokerMsg.append(",remotePort=").append(req.getRemotePort());
		invokerMsg.append(",pathInfo=").append(pathInfo);
		invokerMsg.append(",queryString[").append(req.getQueryString()).append("]");
		invokerMsg.append("】");
		return invokerMsg.toString();
	}

}
