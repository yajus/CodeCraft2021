package com.huawei.java.main;

//输入数据
//请求添加或删除
public class VMRequest {
	String vtype;
	int ID;
	public VMRequest(String vtype,int ID)
	{
		this.vtype = vtype;
		this.ID = ID;
	}
}
