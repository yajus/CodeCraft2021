package com.huawei.java.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

//正在维护的服务器对象
class OwnServices
{
	// running cost,服务器ID
	//开机了多少天
	String stype;//服务器类型
	ArrayList<Integer> AVMs = new ArrayList<Integer>();//正在A运行的虚拟机
	ArrayList<Integer> BVMs = new ArrayList<Integer>();//正在B运行的虚拟机
	boolean Running;//是否开机
	int cpu;//cpu上限
	int usedcpuA;//Acpu使用量
	int usedcpuB;//Bcpu使用量
	int memory;//内存上限
	int usedmemoryA;//A内存使用量
	int usedmemoryB;//B内存使用量
	int runningday;//开机了多少天
	int hardcost;//硬件成本
	int datecost;//开机成本/天
	long cost;//当前运行了的成本
	int ID;//服务器ID
	
	public OwnServices(Service service,int ID)
	{
		this.stype = service.stype;
		this.Running = true;
		this.cpu = service.cpu;
		this.usedcpuA = 0;
		this.usedcpuB = 0;
		this.memory = service.memory;
		this.usedmemoryA =0;
		this.usedmemoryB =0;
		this.hardcost = service.hardcost;
		this.runningday = 0;
		this.datecost = service.datecost;
		this.cost = 0;
		this.ID = ID;
	}
	
	
	
	}

public class ServicesState {
	long count=1;//当前总共有多少服务器 用于ID计算
	HashMap<Integer,OwnServices> runningServices = new HashMap<Integer,OwnServices>();//当前正在运行的服务器
	HashMap<Integer,OwnServices> stopServices = new HashMap<Integer,OwnServices>();//当前没在运行的服务器
	
	//删除虚拟机
	void removevm(OwnVM vmdeleted)
	{
		OwnServices hereservice = this.runningServices.get(vmdeleted.ServiceID);//获取当前虚拟机所在服务器
		int oneortwo = vmdeleted.oneortwo;//判断虚拟机节点数
		int Servicepoint = vmdeleted.Servicepoint;//判断虚拟机节点数
		if(oneortwo ==2)//双节点情况
		{
			hereservice.AVMs.remove(vmdeleted.ID);
			hereservice.BVMs.remove(vmdeleted.ID);
			hereservice.usedcpuA -= vmdeleted.cpu/2;
			hereservice.usedcpuB -= vmdeleted.cpu/2;
			hereservice.usedmemoryA -= vmdeleted.memory/2;
			hereservice.usedmemoryB -= vmdeleted.memory/2;
			
			
		}
		//单节点情况
		else if(Servicepoint==1)
		{
			hereservice.AVMs.remove(vmdeleted.ID);
			hereservice.usedcpuA -= vmdeleted.cpu;
			hereservice.usedmemoryA -= vmdeleted.memory;
		}
		else if(Servicepoint==2)
		{
			hereservice.BVMs.remove(vmdeleted.ID);
			hereservice.usedcpuB -= vmdeleted.cpu;
			hereservice.usedmemoryB -= vmdeleted.memory;
		}
		else
		{
			System.out.println("代码有误，删除服务器中的虚拟机部分");
		}
		if(hereservice.AVMs.size()==0&&hereservice.BVMs.size()==0)
		{
			this.stopServices.put(hereservice.ID, hereservice);
			this.runningServices.remove(hereservice.ID);
		}
		
	}
	int ChoiceService(FileInOut datas,String vtype)
	{
		VM vmdata = datas.VMTypes.get(vtype);
		int cpu = vmdata.cpu;
		int memory = vmdata.memory;
		int oneortwo = vmdata.oneortwo;
		
		//单节点
		if(oneortwo == 1)
		{
			 for(Entry<Integer,OwnServices> i:this.runningServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>=cpu&&serviceinf.memory/2-serviceinf.usedmemoryA>=memory)
				 {
					 return serviceinf.ID;
				 }
				 
				 else if(serviceinf.cpu/2-serviceinf.usedcpuB>=cpu&&serviceinf.memory/2-serviceinf.usedmemoryB>=memory)
				 {
					 return serviceinf.ID;
				 }
			 }
		}
		//双节点
		if(oneortwo == 2)
		{
			 for(Entry<Integer,OwnServices> i:this.runningServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>=cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryA>=memory/2&&serviceinf.cpu/2-serviceinf.usedcpuB>=cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryB>=memory/2)
				 {
					 return serviceinf.ID;
				 }
			 }
		}
		return -1;
		
	}
	
	
}
