package com.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author 
 *
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface IsEntity {
	
	/**
	 * 表名
	 * @return
	 */
	String table();
	
	/**
	 * 主键名,需与字段名对应
	 * @return
	 */
	String pk() default "fid";
	
	/**
	 * 
	 * @return
	 */
	String sequence() default "seq_sys";
	
}
