package com.itheima.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.erp.dao.IBaseDao;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	private Class<T> entityClass;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao() {
		// 取出实体类的类型
		// 得当前调用者，即子类的类型,由于子类没有泛型,所以要获取父类
		Class<? extends BaseDao> childClass = this.getClass();
		// 获取父类,但父类class没法拿到泛型,所以创建父类超级接口
		Type parentType = childClass.getGenericSuperclass();
		// 获取参数化类型
		ParameterizedType pType = (ParameterizedType) parentType;
		// 类型实际类型参数的 Type 对象的数组, 指的是<>中里头可以有多个参数
		Type[] types = pType.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(T t1, T t2, Object object) {
		DetachedCriteria criteria = getDetachedCriteria(t1, t2, object);

		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	public DetachedCriteria getDetachedCriteria(T t1, T t2, Object object) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByPage(T t1, T t2, Object object, int firstResult, int maxResult) {
		DetachedCriteria criteria = getDetachedCriteria(t1, t2, object);
		criteria.addOrder(Order.asc("uuid"));
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		return list;
	}

	@Override
	public Long getCount(T t1, T t2, Object object) {
		DetachedCriteria criteria = getDetachedCriteria(t1, t2, object);
		// 聚合统计
		criteria.setProjection(Projections.rowCount());
		List<?> list = this.getHibernateTemplate().findByCriteria(criteria);
		return (Long) list.get(0);
	}

	@Override
	public void add(T t) {
		getHibernateTemplate().save(t);

	}

	@Override
	public void delete(Long uuid) {
		T t = getHibernateTemplate().get(entityClass, uuid);
		getHibernateTemplate().delete(t);

	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);

	}

	@Override
	public T get(Long uuid) {
		T t = getHibernateTemplate().get(entityClass, uuid);
		return t;

	}

}
