package com.framework.sql;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import com.framework.annotation.IsEntity;
import com.framework.base.BaseEntity;
import com.framework.base.BaseSearch;
import com.framework.exception.DaoException;

public class GetSelectSql<T extends BaseEntity,PK extends Serializable> {

	/**
	 * 获取所有数据
	 * @param search 实体
	 * @return
	 */
	public String getQuerySql(BaseSearch search){
		StringBuffer sql=new StringBuffer();
		sql.append("select * from ");
		sql.append(search.getTable());
		sql.append(" where 1=1 ");
		
		if (search.getWhere()!=null && search.getWhere().trim().length()>0) {
            sql.append(" and "+search.getWhere());
        }
		
		return sql.toString();
	}
	
	/**
	 * 分页
	 * @param search
	 * @return
	 */
	public String getQueryPageSql(BaseSearch search) {
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from (");
			sql.append("select *,ROWNUM RN from (");
				sql.append("select "+search.getField()+" from "+search.getTable());
				sql.append(" where 1=1 ");
				if (search.getWhere()!=null && search.getWhere().trim().length()>0) {
					sql.append(" and "+search.getWhere());
				}
        
				sql.append(" order by "+search.getOrderField());
				sql.append(" "+search.getOrderType().toString());
			sql.append(") T1");
        sql.append(" where ROWNUM <= "+(search.getPageIndex() * search.getPageSize()));
        
        sql.append(") where RN >"+((search.getPageIndex()-1) * search.getPageSize()));
        
        return sql.toString();
		
	}
	
	/**
	 * 获取分页总数
	 * @return
	 */
	public String getQueryPageRowCountSql(BaseSearch search) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(1) from ");
		sql.append(search.getTable());
		sql.append(" where 1=1 ");
		
		if (search.getWhere()!=null && search.getWhere().trim().length()>0) {
            sql.append(" and "+search.getWhere());
        }
		
		return sql.toString();
	}
	
	/**
	 * 新增语法
	 * @param clazz 实体类
	 * @param pk  主键值
	 * @return
	 * @throws DaoException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getSql(Class clazz, PK pk) throws DaoException {
		 StringBuffer sql=new StringBuffer();
		 Annotation annotation =clazz.getAnnotation(IsEntity.class);
		 if (annotation == null || ((IsEntity) annotation).table() == null || ((IsEntity) annotation).pk() == null) {
				throw new DaoException("Dao错误", "实体没有注解:"+clazz.getName());
			}
			
			sql.append("select * from " + ((IsEntity) annotation).table() + " ");

			sql.append(" where ");
			if(pk instanceof Integer) {
				//如果是数值型
				sql.append(((IsEntity) annotation).pk() + " = "+pk.toString());
			}
			else {

				sql.append(((IsEntity) annotation).pk() + " = '"+pk.toString()+"'");
			}
		 return sql.toString();
	}
	
	/**
	 * 获取下一个值
	 * @param clazz
	 * @return
	 * @throws DaoException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String nextIdSql(Class clazz) throws DaoException {
		StringBuffer sql=new StringBuffer();
		
		Annotation annotation =clazz.getAnnotation(IsEntity.class);
		 if (annotation == null || ((IsEntity) annotation).table() == null || ((IsEntity) annotation).pk() == null) {
			 throw new DaoException("Dao错误", "实体没有注解:"+clazz.getName());
			}
			
			sql.append("SELECT "+((IsEntity) annotation).sequence()+".NEXTVAL FROM DUAL");

		return sql.toString();
	}
	
	/***
	 * 是否存在
	 * @param search
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String isExistsSql(Class clazz,BaseSearch search) {
		StringBuffer sql=new StringBuffer();
			sql.append("SELECT Count(1) FROM "+search.getTable());
			sql.append(" where 1=1 ");
			if(search.getWhere() != null && search.getWhere().length()>0) {
				sql.append(" and "+search.getWhere());
			}
			
		return sql.toString();
	}
	
	/***
	 * 获取第一行第一列值
	 * @param search
	 * @return
	 */
	public String getStringSql(BaseSearch search) {
		StringBuffer sql=new StringBuffer();
			
			sql.append("SELECT "+search.getField()+" FROM "+search.getTable());
			sql.append(" where 1=1 ");
			if(search.getWhere() != null && search.getWhere().length()>0) {
				sql.append(" and "+search.getWhere());
			}
		return sql.toString();
	}
	
}
