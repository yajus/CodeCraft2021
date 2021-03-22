package com.huawei.java.main;

import java.util.ArrayList;

//输入数据
//每天的请求 增加和删除各维护一个链表
public class VMPerDay {
	ArrayList<VMRequest> adddatas;
	ArrayList<VMRequest> deletedatas;
	public VMPerDay() 
	{
		adddatas = new ArrayList<VMRequest>();
		deletedatas = new ArrayList<VMRequest>();
	}
}
