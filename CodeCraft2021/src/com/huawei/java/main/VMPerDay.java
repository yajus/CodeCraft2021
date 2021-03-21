package com.huawei.java.main;

import java.util.ArrayList;


//每天的请求
public class VMPerDay {
	ArrayList<VMRequest> adddatas;
	ArrayList<VMRequest> deletedatas;
	public VMPerDay() 
	{
		adddatas = new ArrayList<VMRequest>();
		deletedatas = new ArrayList<VMRequest>();
	}
}
