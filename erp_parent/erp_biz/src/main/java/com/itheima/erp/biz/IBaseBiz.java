package com.itheima.erp.biz;

import java.util.List;



public interface IBaseBiz<T> {
	List<T> getList(T t1,T t2,Object object);
	
	List<T> getListByPage(T t1,T t2,Object object,int firstResult,int maxResult);
	
	Long getCount(T t1,T t2,Object object);
	void add(T t);
	void delete(Long uuid);
	void update(T t);
	T get(Long uuid);
}
