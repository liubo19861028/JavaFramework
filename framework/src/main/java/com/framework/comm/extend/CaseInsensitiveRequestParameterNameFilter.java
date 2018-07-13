package com.framework.comm.extend;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * 接口或类的说明: <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2018年3月12日 下午1:36:36 <br>
 * ==========================
 *
 */

public class CaseInsensitiveRequestParameterNameFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(new CaseInsensitiveParameterNameHttpServletRequest(request), response);
	}

	public static class CaseInsensitiveParameterNameHttpServletRequest extends HttpServletRequestWrapper {
		private final LinkedCaseInsensitiveMap<String[]> map = new LinkedCaseInsensitiveMap<>();

		@SuppressWarnings("unchecked")
		public CaseInsensitiveParameterNameHttpServletRequest(HttpServletRequest request) {
			super(request);
			map.putAll(request.getParameterMap());
		}

		@Override
		public String getParameter(String name) {

			String[] array = this.map.get(name);
			if (array != null && array.length > 0)
				return array[0];
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return Collections.unmodifiableMap(this.map);
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return Collections.enumeration(this.map.keySet());
		}

		@Override
		public String[] getParameterValues(String name) {
			return this.map.get(name);
		}

	}

}