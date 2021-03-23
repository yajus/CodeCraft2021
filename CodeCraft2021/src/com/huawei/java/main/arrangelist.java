package com.huawei.java.main;

import java.util.ArrayList;


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
