package com.picserver.bean;

public class SpaceBean {
	//空间名称，也就是hbase的rowkey
	String name = null;
	//空间描述
	String desc = null;
	//空间封面
	String cover = null;
	
	//已用容量
	String storage  = null;
	//图片数量
	String number = null;
	//访问流量
	String flow = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
}