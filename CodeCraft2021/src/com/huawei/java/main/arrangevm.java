package com.huawei.java.main;

import java.util.ArrayList;


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
