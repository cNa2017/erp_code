package com.itheima.erp.action;

import com.itheima.erp.biz.IDepBiz;
import com.itheima.erp.entity.Dep;

public class DepAction extends BaseAction<Dep>{
	private IDepBiz depBiz;
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		//父类调用方法应该调用dep的biz
		super.setBaseBiz(depBiz);
	}
}

