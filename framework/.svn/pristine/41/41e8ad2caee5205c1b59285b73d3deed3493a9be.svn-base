package com.framework.comm.util;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.framework.beans.T;
import com.framework.exception.BaseCheckedException;
 
/**
 * 
 * 接口或类的说明: HTTP请求工具
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class NetUtil {
	private static Logger logger = LoggerFactory.getLogger(NetUtil.class);
	private static final String EXCEPTION_BASE_KEY = NetUtil.class.getName();
	public static final String EXCEPTION_KEY_SERVER_ERROR = EXCEPTION_BASE_KEY.concat(".ServerError");

	/**
	 * 异常码：签名不正确
	 */
	public static final String ERROR_INVALID_SIGN = "90";
	/**
	 * 异常码：服务不可用
	 */
	public static final String ERROR_SERVICE_UNAVAILABLE = "91";
	/**
	 * 异常码：无效请求
	 */
	public static final String ERROR_INVALID_REQUEST = "92";
	/**
	 * 异常码：服务器内部错误
	 */
	public static final String ERROR_INTERNAL_SERVER_ERROR = "93";
	/**
	 * 异常码：应用不存在
	 */
	public static final String ERROR_APPLICATION_IS_NOT_EXISTED = "94";
	/**
	 * 异常码：服务不存在
	 */
	public static final String ERROR_SERVICE_IS_NOT_FOUND = "95";
	/**
	 * 异常码：非法请求参数
	 */
	public static final String ERROR_ILLEGAL_REQUEST_PARAMETER = "96";
	/**
	 * 异常码：受限的请求者（未经允许访问，如受限IP）
	 */
	public static final String ERROR_ILLEGAL_REQUEST = "97";

	/**
	 * 异常信息容器
	 */
	private static Map<String, String> errMsgContainer = new HashMap<String, String>();

	/**
	 * 连接超时时间
	 */
	private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 5 * 1000;

	/**
	 * 读超时时间
	 */
	private static final int DEFAULT_READ_TIMEOUT_MS = 10 * 1000;

	/**
	 * 默认编码
	 */
	protected static final String DEFAULT_CHARSET = "UTF-8";

	private static final HttpClient HTTP_CLIENT;
	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		PoolingClientConnectionManager pcm = new PoolingClientConnectionManager(schemeRegistry);
		pcm.setMaxTotal(200);
		pcm.setDefaultMaxPerRoute(20);
		HTTP_CLIENT = new DefaultHttpClient(pcm);
		HTTP_CLIENT.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT_MS);
		HTTP_CLIENT.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_READ_TIMEOUT_MS);
		HTTP_CLIENT.getParams().setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 16 * 1024);
	}

	/**
	 * 根据具体的的KEY来获取U点续费错误信息
	 *
	 * @param key
	 * @return
	 */
	public static String getErrorMessageInfo(String key) {
		if (StringUtil.isEmpty(key))
			return "";
		if (errMsgContainer.isEmpty()) {
			errMsgContainer.put(ERROR_INVALID_SIGN, "签名不正确");
			errMsgContainer.put(ERROR_SERVICE_UNAVAILABLE, "服务不可用");
			errMsgContainer.put(ERROR_INVALID_REQUEST, "无效请求");
			errMsgContainer.put(ERROR_INTERNAL_SERVER_ERROR, "服务器内部错误");
			errMsgContainer.put(ERROR_APPLICATION_IS_NOT_EXISTED, "应用不存在");
			errMsgContainer.put(ERROR_SERVICE_IS_NOT_FOUND, "服务不存在");
			errMsgContainer.put(ERROR_ILLEGAL_REQUEST_PARAMETER, "非法请求参数");
			errMsgContainer.put(ERROR_ILLEGAL_REQUEST, "受限的请求者");
			errMsgContainer = Collections.unmodifiableMap(errMsgContainer);
		}
		return errMsgContainer.get(key);
	}

	/**
	 * 是否包含指定的异常KEY
	 *
	 * @param errMsgKey
	 * @return
	 */
	public static boolean isContainsTheErrMsgKey(String errMsgKey) {
		if (errMsgContainer.isEmpty())
			getErrorMessageInfo("");
		return errMsgContainer.keySet().contains(errMsgKey);
	}
	/**
	 * 发送POST请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param postUrl
	 *            POST请求的URL
	 * @return 
	 * @throws BaseCheckedException
	 */
	public static <T> T postHttpRequest(String paramStr, String postUri ) throws BaseCheckedException {
		 
	   return (T) postHttpRequest(paramStr, postUri, DEFAULT_READ_TIMEOUT_MS, DEFAULT_CHARSET);
	}


	/**
	 * 发送POST请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param postUrl
	 *            POST请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @throws BaseCheckedException
	 */
	public static String postHttpRequest(String paramStr, String postUri, int expireSeconds) throws BaseCheckedException {
		return postHttpRequest(paramStr, postUri, expireSeconds, DEFAULT_CHARSET);
	}

	/**
	 * 发送GET请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param getUrl
	 *            GET请求的URL
	
	 * @throws BaseCheckedException
	 */
	public static String getHttpRequest(String paramStr, String getUri) throws BaseCheckedException {
		return getHttpRequest(paramStr, getUri, DEFAULT_READ_TIMEOUT_MS, DEFAULT_CHARSET);
	}
	
	/**
	 * 发送GET请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param getUrl
	 *            GET请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @throws BaseCheckedException
	 */
	public static String getHttpRequest(String paramStr, String getUri, int expireSeconds) throws BaseCheckedException {
		return getHttpRequest(paramStr, getUri, expireSeconds, DEFAULT_CHARSET);
	}

	
	/**
	 * 发送POST请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param postUrl
	 *            POST请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @param charSet
	 *            字符集
	 * @throws BaseCheckedException
	 */
	public static String postHttpRequest(String paramStr, String postUrl, int expireSeconds, String charSet) throws BaseCheckedException {
		if (paramStr == null)
			paramStr = "";
		return postHttpRequest(UrlUtil.convertUrlParameterStringToMap(paramStr), postUrl, expireSeconds, charSet);
	}

	/**
	 * 发送GET请求
	 *
	 * @param paramStr
	 *            字符串形式的参数
	 * @param getUrl
	 *            GET请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒]
	 * @param charSet
	 *            字符集
	 * @throws BaseCheckedException
	 */
	public static String getHttpRequest(String paramStr, String getUrl, int expireSeconds, String charSet) throws BaseCheckedException {
		if (paramStr == null)
			paramStr = "";
		return getHttpRequest(UrlUtil.convertUrlParameterStringToMap(paramStr), getUrl, expireSeconds, charSet);
	}

	/**
	 * 发送POST请求
	 *
	 * @param uParamsMap
	 *            Map形式的�数
	 * @param postUrl
	 *            POST请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @throws BaseCheckedException
	 */
	public static String postHttpRequest(Map<String, String> uParamsMap, String postUri, int expireSeconds) throws BaseCheckedException {
		return postHttpRequest(uParamsMap, postUri, expireSeconds, DEFAULT_CHARSET);
	}

	/**
	 * 发送POST请求
	 *
	 * @param uParamsMap
	 *            Map形式的参数
	 * @param getUrl
	 *            GET请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @throws BaseCheckedException
	 */
	public static String getHttpRequest(Map<String, String> uParamsMap, String getUri, int expireSeconds) throws BaseCheckedException {
		return getHttpRequest(uParamsMap, getUri, expireSeconds, DEFAULT_CHARSET);
	}

	/**
	 * 发送POST请求
	 *
	 * @param uParamsMap
	 *            Map形式的�数
	 * @param postUrl
	 *            POST请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @param charSet
	 *            字符集
	 * @throws BaseCheckedException
	 */
	public static String postHttpRequest(Map<String, String> uParamsMap, String postUrl, int expireSeconds, String charSet) throws BaseCheckedException {
		logger.debug("以HTTP Client的方式，发送POST请求.");
		NameValuePair[] nameValuPairArray = convertParamsMap2NameValuePair(uParamsMap);
		HttpPost postMethod = new HttpPost(postUrl);
		StringEntity postEntity = null;
		List<NameValuePair> parameters = Arrays.asList(nameValuPairArray);
		try {
			postEntity = new UrlEncodedFormEntity(parameters, charSet);
		} catch (UnsupportedEncodingException e) {
			try {
				postEntity = new UrlEncodedFormEntity(parameters);
			} catch (UnsupportedEncodingException e1) {
				// should not happend, default charset is ISO_8859_1
				return null;
			}
		}
		postMethod.setEntity(postEntity);
		String result = httpRequest(postMethod, expireSeconds, postUrl, charSet);
		logger.debug("POST请求发送完毕. ");
		return result;
	}

	/**
	 * 发送POST请求
	 *
	 * @param uParamsMap
	 *            Map形式的参数
	 * @param getUrl
	 *            GET请求的URL
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @param charSet
	 *            字符集
	 * @throws BaseCheckedException
	 */
	public static String getHttpRequest(Map<String, String> uParamsMap, String getUrl, int expireSeconds, String charSet) throws BaseCheckedException {
		logger.debug("以HTTP Client的方式，发送GET请求.");
		String queryString = formUrlEncode(convertParamsMap2NameValuePair(uParamsMap), charSet);
		HttpGet getMethod = null;
		if (StringUtil.isEmpty(queryString)) {
			getMethod = new HttpGet(getUrl);
		} else {
			getMethod = new HttpGet(getUrl + "?" + queryString);
		}
		String result = httpRequest(getMethod, expireSeconds, getUrl, charSet);
		logger.debug("GET请求发送完毕. ");
		return result;
	}

	protected static String formUrlEncode(NameValuePair[] pairs, String charset) {
		try {
			return doFormUrlEncode(pairs, charset);
		} catch (UnsupportedEncodingException e) {
			try {
				return doFormUrlEncode(pairs, DEFAULT_CHARSET);
			} catch (UnsupportedEncodingException fatal) {
				// Should never happen. ISO-8859-1 must be supported on all JVMs
				return null;
			}
		}
	}

	protected static String doFormUrlEncode(NameValuePair[] pairs, String charset) throws UnsupportedEncodingException {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < pairs.length; i++) {
			URLCodec codec = new URLCodec();
			NameValuePair pair = pairs[i];
			if (pair.getName() != null) {
				if (i > 0) {
					buf.append("&");
				}

				buf.append(codec.encode(pair.getName(), charset));
				buf.append("=");
				if (pair.getValue() != null) {
					if (pair.getValue().startsWith("http%3A")) {
						buf.append(pair.getValue());
					} else {
						buf.append(codec.encode(pair.getValue(), charset));
					}
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 转换Map的参数成NameValuePair
	 *
	 * @param uParamsMap
	 * @return
	 */
	public static NameValuePair[] convertParamsMap2NameValuePair(Map<String, String> uParamsMap) {
		if (uParamsMap == null)
			uParamsMap = new HashMap<String, String>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : uParamsMap.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		NameValuePair[] nameValuPairArray = nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
		return nameValuPairArray;
	}

	/**
	 * 发送POST请求
	 *
	 * @param postMethods
	 *            HttpMethodBase对象
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @throws BaseCheckedException
	 */
	public static String httpRequest(HttpEntityEnclosingRequestBase postMethod, int expireSeconds, String requestUri) throws BaseCheckedException {
		return httpRequest(postMethod, expireSeconds, requestUri, null);
	}

	/**
	 * 发送POST请求
	 *
	 * @param postMethods
	 *            HttpMethodBase对象
	 * @param expireSeconds
	 *            请求超时时间，单位为秒
	 * @param charSet
	 *            字符集
	 * @throws BaseCheckedException
	 */
	public static String httpRequest(HttpRequestBase httpMethod, int expireSeconds, String requestUri, String charSet) throws BaseCheckedException {
		return httpRequest(HTTP_CLIENT, httpMethod, expireSeconds, requestUri, charSet);
	}

	/**
	 * 对比 httpRequest ，增多HttpClient参数，方便子类扩展
	 *
	 * @param httpClient
	 * @param httpMethod
	 * @param expireSeconds
	 * @param requestUri
	 * @param charSet
	 * @return
	 * @throws BaseCheckedException
	 */
	protected static String httpRequest(HttpClient httpClient, HttpRequestBase httpMethod, int expireSeconds, String requestUri, String charSet) throws BaseCheckedException {
		if (expireSeconds > 0) {
			httpMethod.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, expireSeconds * 1000);
		}
		if (logger.isDebugEnabled())
			logger.debug("http Request QueryString: {}", httpMethod.getRequestLine().getUri());

		httpMethod.getURI();
		try {
			HttpResponse response = httpClient.execute(httpMethod);
			int status = response.getStatusLine().getStatusCode();
			String respBody = EntityUtils.toString(response.getEntity(), charSet);
			if (HttpServletResponse.SC_OK == status) {
				logger.debug("HTTP请求发送完毕. 响应结果为：{}", respBody);
				return respBody != null ? respBody.trim() : respBody;
			} else if (HttpServletResponse.SC_INTERNAL_SERVER_ERROR == status) {
				String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时出现内部服务错误. " + ("".equals(respBody) ? "" : (". Response body: " + respBody));
				logger.warn(errMsg);
				throw new BaseCheckedException(ERROR_INTERNAL_SERVER_ERROR, "远程服务时出现500内部服务错误");
			} else if (HttpServletResponse.SC_NOT_FOUND == status) {
				String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时出现找不到对应的服务. " + ("".equals(respBody) ? "" : (". Response body: " + respBody));
				logger.warn(errMsg);
				throw new BaseCheckedException(ERROR_SERVICE_IS_NOT_FOUND, "远程服务时出现找不到对应的服务");
			} else if (HttpServletResponse.SC_FORBIDDEN == status) {
				String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时出现请求受限（未经允许访问，如受限IP）. " + ("".equals(respBody) ? "" : (". Response body: " + respBody));
				logger.warn(errMsg);
				throw new BaseCheckedException(ERROR_ILLEGAL_REQUEST, "远程服务时出现请求受限");
			} else {
				String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时出现未处理的响应状态. Exception code: " + status + ("".equals(respBody) ? "" : (". Response body: " + respBody));
				logger.warn(errMsg);
				throw new BaseCheckedException(ERROR_SERVICE_UNAVAILABLE, "远程服务时出现未处理的响应状态" + status);
			}
		} catch (ClientProtocolException e) {
			String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时发生ClientProtocolException，异常信息: " + e.getMessage();
			logger.warn(errMsg);
			throw new BaseCheckedException(ERROR_SERVICE_UNAVAILABLE, e.getMessage());
		} catch (IOException e) {
			String errMsg = "调用【" + httpMethod.getURI() + "】远程服务时发生IOException，异常信息: " + e.getMessage();
			logger.warn(errMsg);
			throw new BaseCheckedException(ERROR_SERVICE_UNAVAILABLE, e.getMessage());
		}
	}

	/**
	 * 从HttpServletRequest中获取远程IP
	 *
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		final String UNKNOWN = "unknown";
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtil.isEmpty(ip) || ip.startsWith(UNKNOWN)) {
			ip = request.getHeader("Proxy-Client-IP");
		} else {
			ip = ip.split(",")[0];
		}
		if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	  
	/**
	 * 从HttpServletRequest中获取请求Root
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestRoot(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		String requestUrl = request.getContextPath();
		if (requestUrl != null && !"".equals(requestUrl)) {
			sb.append(requestUrl);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("请求的URL是:" + sb.toString());
		}
		return sb.toString();
	}

	/**
	 * 获取服务器的主机名称
	 *
	 * @return
	 */
	public static String getHostName() {
		String hostName = System.getenv("HOSTNAME");
		if (StringUtil.isEmpty(hostName)) {
			try {
				hostName = InetAddress.getLocalHost().getHostName();
			} catch (Exception e) {
				logger.warn("获取服务器主机名称时出现异常", e);
			}
		}
		return hostName;
	}
}
