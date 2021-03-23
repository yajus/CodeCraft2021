package com.huawei.java.main;

import java.util.ArrayList;

class arrangebase
{
	int ServiceID;
	int point;
	public arrangebase(int ServiceID,int point)
	{
		this.ServiceID = ServiceID;
		this.point = point;
	}
}

class arrangelist
{
	ArrayList<arrangebase> alist;
	public arrangelist()
	{
		 alist = new ArrayList<arrangebase>();
	}
	void add(int id,int point)
	{
		this.alist.add(new arrangebase(id,point));
	}
}
public class arrangevm {
	ArrayList<arrangelist> alllist;
	public arrangevm()
	{
		 alllist = new ArrayList<arrangelist>();
	}
	void add(arrangelist l)
	{
		this.alllist.add(l);
	}
}
