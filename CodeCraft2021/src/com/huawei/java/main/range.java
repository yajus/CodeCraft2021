package com.huawei.java.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

//排序函数 给算法中的新增服务器类型选择提供参考意见
public class range {
	//List<>单位cpu 硬件成本
	//List<>单位memory 硬件和维护成本
//	List<>单位cpu 硬件和维护成本
	//List<>单位memory 硬件成本
	ArrayList<Service> ServicesRangeByCpuAndMemory;
	range(FileInOut datas)
	{
		ServicesRangeByCpuAndMemory = new ArrayList<Service>();
		for(Map.Entry<String, Service> i : datas.ServiceTypes.entrySet())
		{
			if(i.getValue()==null)
				System.out.println("isnull");
			ServicesRangeByCpuAndMemory.add(i.getValue());
		}
		
	}
	void RangeByCpuAndGpu()
	{
		ServicesRangeByCpuAndMemory.sort(new Comparator<Service>(){
			public int compare(Service a, Service b) {
				int c1,c2;
				double l1,l2,l3,l4;
				l1 =0.25;
				l2 =0.25;
				l3 =0.25;
				l4 =0.25;
				c1 = (int)(l1*a.hardcost/a.cpu+l2*a.hardcost/a.memory+l3*a.datecost/a.cpu+l4*a.datecost/a.memory);
				c2 = (int)(l1*b.hardcost/b.cpu+l2*b.hardcost/b.memory+l3*b.datecost/b.cpu+l4*b.datecost/b.memory);
				return c1-c2;
			}
		});
	}
	
}
