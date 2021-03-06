package com.framework.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.framework.annotation.IsEntity;
import com.framework.annotation.NotColumn;
import com.framework.base.BaseEntity;
import com.framework.comm.ClassGenericsUtils;
import com.framework.exception.DaoException;

public class GetUpdateSql<T extends BaseEntity> {

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getUpdateSql(T t) throws DaoException {
		StringBuilder sql = new StringBuilder();
		StringBuilder valuessql = new StringBuilder();
		String pk="";//保存主键名
		Class clazz = t.getClass();

		Field[] files = clazz.getDeclaredFields();
		
		
		Annotation annotation = clazz.getAnnotation(IsEntity.class);// 获得注解
		pk=((IsEntity) annotation).pk();
		Object oT = (Object) t;
		for (Field field : files) {

			field.setAccessible(true);
			Object oValue = ClassGenericsUtils.getFieldValue(field, oT);

			// 如果这个字段是表字段,如果没有注解NotColumn 就表示是字段
			if (field.getAnnotation(NotColumn.class) == null) {

				if(((IsEntity) annotation).pk().toLowerCase().equals(field.getName().toLowerCase()))
				{
					//此举是避免注解跟实体字段大小写对不上，导致参数无法赋值
					pk=field.getName();
				}
				// 不更新null值
				if (oValue != null) {
					valuessql.append(field.getName()+" = #{" + field.getName() + "},");
				} 
			}
		}

		if (valuessql.length() > 0) {
			
			valuessql.deleteCharAt(valuessql.length() - 1);

			if (annotation == null || ((IsEntity) annotation).table() == null
					|| ((IsEntity) annotation).pk() == null) {
				throw new DaoException("Dao错误", "实体没有注解:"+clazz.getName());
			}
			sql.append("update " + ((IsEntity) annotation).table() + " ");

			sql.append(" set ");
			sql.append(valuessql);

			sql.append(" where ");
			sql.append(pk + " = #{"+pk+"}");
			
			//System.out.println("最终返回sql:" + sql.toString());
			return sql.toString();
		}
		throw new DaoException("Dao错误", "没有取到列:" + clazz.getName());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getUpdateAllSql(T t) throws DaoException {
		StringBuilder sql = new StringBuilder();
		StringBuilder valuessql = new StringBuilder();
		String pk="";//保存主键名
		Class clazz = t.getClass();

		Field[] files = clazz.getDeclaredFields();
		
		Annotation annotation = clazz.getAnnotation(IsEntity.class);// 获得注解
		pk=((IsEntity) annotation).pk();
		Object oT = (Object) t;
		for (Field field : files) {

			field.setAccessible(true);
			Object oValue = ClassGenericsUtils.getFieldValue(field, oT);

			// 如果这个字段是表字段,如果没有注解NotColumn 就表示是字段
			if (field.getAnnotation(NotColumn.class) == null) {

				if(((IsEntity) annotation).pk().toLowerCase().equals(field.getName().toLowerCase()))
				{
					//此举是避免注解跟实体字段大小写对不上，导致参数无法赋值
					pk=field.getName();
				}
				// 不更新null值
				if (oValue != null) {
					valuessql.append(field.getName()+" = #{" + field.getName() + "},");
				}else {
					valuessql.append(field.getName()+" = null,");
				} 
			}
		}

		if (valuessql.length() > 0) {
			
			valuessql.deleteCharAt(valuessql.length() - 1);

			if (annotation == null || ((IsEntity) annotation).table() == null
					|| ((IsEntity) annotation).pk() == null) {
				throw new DaoException("Dao错误", "实体没有注解:"+clazz.getName());
			}
			sql.append("update " + ((IsEntity) annotation).table() + " ");

			sql.append(" set ");
			sql.append(valuessql);

			sql.append(" where ");
			sql.append(pk + " = #{"+pk+"}");
			
			return sql.toString();
		}
		throw new DaoException("Dao错误","没有取到列:" + clazz.getName());
	}
	
}
