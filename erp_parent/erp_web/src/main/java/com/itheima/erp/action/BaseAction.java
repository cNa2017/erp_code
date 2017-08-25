package com.itheima.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.itheima.erp.biz.IBaseBiz;


public class BaseAction<T> {
	private IBaseBiz<T> baseBiz;
	private T t;//属性驱动,增加修改
	private T t1;//查询条件1
	private T t2;//查询条件2
	private Object param;//查询条件参数
	
	private int page;//页码
	private int rows;//每页显示条数
	private Long id;//编号
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public void setBaseBiz(IBaseBiz<T> baseBiz) {
		this.baseBiz = baseBiz;
	}

	public void list() {
		List<T> list = baseBiz.getList(t1,t2,param);
		String jsonString = JSON.toJSONString(list);
		HttpServletResponse res = ServletActionContext.getResponse();
	//	res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=UTF-8");
		try {
			res.getWriter().println(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listByPage(){
		int firstResult =(page-1)*rows;
		int maxResult=rows;
		List<T> list = baseBiz.getListByPage(t1, t2, param, firstResult, maxResult);
		Long total = baseBiz.getCount(t1, t2, param);
		Map<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("rows", list);
		write(JSON.toJSONString(map));
	}
	
	private void write(String jsonString){
		HttpServletResponse res = ServletActionContext.getResponse();
		//设置传输的编码
		res.setCharacterEncoding("utf-8");
		//设置浏览读取的编码
		res.setContentType("text/html;chartset=utf-8");
		
		try {
			//输出给前端
			res.getWriter().println(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(){
		try {
			baseBiz.add(t);
			ajaxReturn(true, "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "新增失败");
		}
		
	}
	public void delete(){
		try {
			baseBiz.delete(id);
			ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "删除失败");
		}
	}
	private void ajaxReturn(boolean success, String message){
		Map<String, Object> rtn = new HashMap<String,Object>();
		rtn.put("success", success);
		rtn.put("message", message);
		write(JSON.toJSONString(rtn));
	}
	public void get(){
		T t = baseBiz.get(id);
		//{"name":"管理员组","tele":"000000","uuid":1} 前端不能显示
		//{"t.name":"管理员组","t.tele":"000000","t.uuid":1} 这种数据，前端 就可以显示
		
		String jsonString = JSON.toJSONString(t);
		//把这个jsonString转成map
		Map<String, Object> jsonMap = JSON.parseObject(jsonString);
		
		//转换后的map
		Map<String,Object> newMap = new HashMap<String,Object>();
		
		//把jsonmap的key值拿出来，进行拼接t.
		for(String key : jsonMap.keySet()){
			String newKey = "t." + key;
			newMap.put(newKey, jsonMap.get(key));
		}
		write(JSON.toJSONString(newMap));
	}
	public void update(){
		try {
			baseBiz.update(t);
			ajaxReturn(true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "更新失败");
		}
	}
}
