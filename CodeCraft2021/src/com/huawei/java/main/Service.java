package com.huawei.java.main;
//输入数据
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
