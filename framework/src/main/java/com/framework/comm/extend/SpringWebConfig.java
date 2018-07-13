package com.framework.comm.extend; 
/**
 * 
 * 接口或类的说明: URL扩展，扩展url链接不区分大小写与接收点参数值后面的数据<br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2018年3月8日 下午2:24:36 <br>
 * ==========================
 *
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
@EnableWebMvc  
@ComponentScan//组件扫
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);// 设置URL不区分大小写
		configurer.setUseSuffixPatternMatch(false);// 接收参数值后.后面的数据，例：a=xx.yy
		configurer.setPathMatcher(matcher);
	}
}