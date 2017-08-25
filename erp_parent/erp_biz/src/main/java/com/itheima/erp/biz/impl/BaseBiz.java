package com.itheima.erp.biz.impl;

import java.util.List;

import com.itheima.erp.biz.IBaseBiz;
import com.itheima.erp.dao.IBaseDao;


public class BaseBiz<T> implements IBaseBiz<T>{

	private IBaseDao<T> baseDao;
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<T> getList(T t1,T t2,Object object) {
		
		return baseDao.getList(t1,t2,object);
	}

	@Override
	public List<T> getListByPage(T t1, T t2, Object object, int firstResult, int maxResult) {
		return baseDao.getListByPage(t1, t2, object, firstResult, maxResult);
	}

	@Override
	public Long getCount(T t1, T t2, Object object) {
		return baseDao.getCount(t1, t2, object);
	}

	@Override
	public void add(T t) {
		baseDao.add(t);
		
	}

	@Override
	public void delete(Long uuid) {
		baseDao.delete(uuid);
		
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
		
	}

	@Override
	public T get(Long uuid) {
		return baseDao.get(uuid);
	}

}
