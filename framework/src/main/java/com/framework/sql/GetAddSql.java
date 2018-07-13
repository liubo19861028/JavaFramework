package com.framework.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.framework.annotation.IsEntity;
import com.framework.annotation.NotColumn;
import com.framework.base.BaseEntity;
import com.framework.comm.ClassGenericsUtils;
import com.framework.comm.util.StringUtil;
import com.framework.exception.DaoException;

public class GetAddSql<T extends BaseEntity> {

	/*
	 * 根据实体新增sql,null不新增
	 */
	@SuppressWarnings("rawtypes")
	public String getAddSql(T t) throws IllegalArgumentException, IllegalAccessException, DaoException {
		StringBuilder sql = new StringBuilder();
		StringBuilder intosql = new StringBuilder();
		StringBuilder valuessql = new StringBuilder();

		Class clazz = t.getClass();
		@SuppressWarnings("unchecked")
		Annotation annotation = clazz.getAnnotation(IsEntity.class);// 获得注解
		if (annotation == null || ((IsEntity) annotation).table() == null
				|| ((IsEntity) annotation).pk() == null) {
				throw new DaoException("Dao错误", "实体缺少注解"+ clazz.getName());
		}
		Field[] files = clazz.getDeclaredFields();
		Object oT = (Object) t;
		for (Field field : files) {

			field.setAccessible(true);
			Object oValue = ClassGenericsUtils.getFieldValue(field, oT);
			// System.out.println(field.getName()+":--:"+(oValue==null?"空":oValue.toString()));

			// 如果这个字段是表字段,如果没有注解NotColumn 就表示是字段
			if (field.getAnnotation(NotColumn.class) == null) {

				// 不新增null值
				if (oValue != null) {
					intosql.append(field.getName() + ",");
					valuessql.append("#{" + field.getName() + "},");
				} 
				else if(((IsEntity) annotation).pk().toLowerCase().equals(field.getName().toLowerCase())&&!StringUtil.isEmpty(((IsEntity) annotation).sequence()))
				{
					intosql.append(field.getName() + ",");
					valuessql.append("" + ((IsEntity) annotation).sequence() + ".NEXTVAL,");
				}
			}
		}


		if (intosql.length() > 0) {
			intosql.deleteCharAt(intosql.length() - 1);
			valuessql.deleteCharAt(valuessql.length() - 1);

			sql.append("insert into " + ((IsEntity) annotation).table() + " ");

			sql.append(" ( ");
			sql.append(intosql.toString());
			sql.append(" ) ");

			sql.append(" values ");

			sql.append(" ( ");
			sql.append(valuessql.toString());
			sql.append(" ) ");
			//System.out.println("最终返回sql:" + sql.toString());
			return sql.toString();
		}
		//System.err.println("没有取到列:" + clazz.getName());
		
		throw new DaoException("Dao错误", "没有取到列:" + clazz.getName());
	}

	/*
	 * 根据实体值新增sql,null默认新增null
	 */
	public String getAddAllSql(T t) throws IllegalArgumentException, IllegalAccessException, DaoException {
		StringBuilder sql = new StringBuilder();
		StringBuilder intosql = new StringBuilder();
		StringBuilder valuessql = new StringBuilder();

		Class clazz = t.getClass();
		@SuppressWarnings("unchecked")
		Annotation annotation = clazz.getAnnotation(IsEntity.class);// 获得注解
		if (annotation == null || ((IsEntity) annotation).table() == null
				|| ((IsEntity) annotation).pk() == null) {
				throw new DaoException("Dao错误", "实体缺少注解"+ clazz.getName());
		}
		Field[] files = clazz.getDeclaredFields();
		Object oT = (Object) t;
		for (Field field : files) {

			field.setAccessible(true);
			Object oValue = ClassGenericsUtils.getFieldValue(field, oT);
			// System.out.println(field.getName()+":--:"+(oValue==null?"空":oValue.toString()));

			// 如果这个字段是表字段,如果没有注解NotColumn 就表示是字段
			if (field.getAnnotation(NotColumn.class) == null) {

				// 值不等于null的时候添加值
				if (oValue != null) {
					intosql.append(field.getName() + ",");
					valuessql.append("#{" + field.getName() + "},");
				}  else {
					if(((IsEntity) annotation).pk().toLowerCase().equals(field.getName().toLowerCase())&&!StringUtil.isEmpty(((IsEntity) annotation).sequence()))
					{
						intosql.append(field.getName() + ",");
						valuessql.append("" + ((IsEntity) annotation).sequence() + ".NEXTVAL,");
					}
					else {
						intosql.append(field.getName() + ",");
						//如果是integer类型，默认为0，integer不能是null
						if (oValue instanceof Integer) {
							valuessql.append("0,");
						} else {
							valuessql.append("null,");
						}
					}
					
				}
			}
		}


		if (intosql.length() > 0) {
			intosql.deleteCharAt(intosql.length() - 1);
			valuessql.deleteCharAt(valuessql.length() - 1);

			sql.append("insert into " + ((IsEntity) annotation).table() + " ");

			sql.append(" ( ");
			sql.append(intosql.toString());
			sql.append(" ) ");

			sql.append(" values ");

			sql.append(" ( ");
			sql.append(valuessql.toString());
			sql.append(" ) ");
			//System.out.println("最终返回sql:" + sql.toString());
			return sql.toString();
		}
		throw new DaoException("Dao错误", "没有取到列:" + clazz.getName());
	}

}
