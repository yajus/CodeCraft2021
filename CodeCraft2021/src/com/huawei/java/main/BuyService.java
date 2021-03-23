package com.huawei.java.main;

import java.util.ArrayList;
import java.util.HashMap;

class ServiceTypeAndNum
{
	HashMap<String,Integer> mapTypeNum; 
	public ServiceTypeAndNum()
	{
		mapTypeNum = new HashMap<String,Integer> ();
	}
	void buy(String type)
	{
		if(this.mapTypeNum.containsKey(type))
		{
			this.mapTypeNum.put(type,this.mapTypeNum.get(type)+1);
		}
		else
		{
			this.mapTypeNum.put(type,1);
		}
	}
}

public class BuyService {
	
	ArrayList<ServiceTypeAndNum> s;
	public  BuyService()
	{
		 s = new ArrayList<ServiceTypeAndNum>();
	}
	void add(ServiceTypeAndNum sta)
	{
		s.add(sta);
	}
	
}
