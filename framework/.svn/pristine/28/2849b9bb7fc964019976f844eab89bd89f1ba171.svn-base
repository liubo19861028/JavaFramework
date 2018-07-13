package com.framework.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface BaseService <T extends BaseEntity,PK extends Serializable> {
	
	/**
	 * 根据实体新增，null值不新增
	 * @param t
	 * @return
	 */
	public Integer add(T t);
	
	/**
	 * 使用事物add实体数据
	 * @param list
	 * @return
	 */
	@Transactional
	public Integer addTran(List<T> list);
	
	/**
	 * 根据实体新增，null值也默认新增null
	 * @param t
	 * @return
	 */
	public Integer addAll(T t);
	
	/**
	 * 使用事物add实体数据，并新增null值数据
	 * @param list
	 * @return
	 */
	@Transactional
	public Integer addAllTran(List<T> list);
	
	/**
	 * 根据主键删除
	 * @param t
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer delete(Class clazz,PK pk);
	
	/**
	 * 根据实体更新
	 * @param t
	 * @return
	 */
	public Integer update(T t);
	

	/**
	 * 使用事物更新实体数据
	 * @param list
	 * @return
	 */
	@Transactional
	public Integer updateTran(List<T> list);
	
	/**
	 * 根据实体更新
	 * @param t
	 * @return
	 */
	public Integer updateAll(T t);
	
	/**
	 * 使用事物更新实体数据，并更新null值数据
	 * @param list
	 * @return
	 */
	@Transactional
	public Integer updateAllTran(List<T> list);
	
	/**
	 * 获取所有数据
	 * @param search 实体
	 * @return
	 */
	public List<T> Query(BaseSearch search);
	
	/**
	 * 获取分页数据
	 * @param search 实体
	 * @return
	 */
	public List<T> QueryPage(BaseSearch search);
	
	/**
	 * 获取分页总条数
	 * @param search 
	 * @return
	 */
	public Integer QueryPageRowCount(BaseSearch search);
	
	/**
	 * 获取实体
	 * @param clazz
	 * @param pk
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public T get(Class clazz,PK pk);
	
	/**
	 * 获取下一个值
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer nextId(Class clazz);
	
	/***
	 * 是否存在
	 * @param clazz
	 * @param search
	 * @return
	 */
	public Boolean isExists(BaseSearch search);
	
	/***
	 * 获取第一行第一列值
	 * @param search
	 * @return
	 */
	public String getString(BaseSearch search);
	
}
