package com.huawei.java.main;

//服务器
public class Service {
	String stype;
	int cpu;
	int memory;
	int hardcost;
	int datecost;
	Service(String stype,int cpu,int memory,int hardcost,int datecost)
	{
		this.stype = stype;
		this.cpu = cpu;
		this.memory = memory;
		this.hardcost = hardcost;
		this.datecost = datecost;
	}
}
