package com.itheima.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

import com.itheima.erp.dao.IBaseDao;
import com.itheima.erp.dao.IDepDao;
import com.itheima.erp.entity.Dep;

public class DepDao extends BaseDao<Dep> implements IDepDao{
	@Override
	public DetachedCriteria getDetachedCriteria(Dep dep1, Dep dep2, Object object) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dep.class);
		if (dep1 != null && !StringUtils.isEmpty(dep1.getName())) {
			criteria.add(Restrictions.ilike("name", dep1.getName(), MatchMode.ANYWHERE));
		}
		if (dep1 != null && !StringUtils.isEmpty(dep1.getTele())) {
			criteria.add(Restrictions.ilike("tele", dep1.getTele(), MatchMode.ANYWHERE));
		}
		return criteria;
	}
	

}
