package com.itheima.erp.biz.impl;

import com.itheima.erp.biz.IDepBiz;
import com.itheima.erp.dao.IDepDao;
import com.itheima.erp.entity.Dep;

public class DepBiz extends BaseBiz<Dep> implements IDepBiz{

	private IDepDao depDao;
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		//让父类的方法调用的时候,应该调用dep的dao
		super.setBaseDao(depDao);
	}
	


}
