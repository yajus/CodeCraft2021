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
		this.stype = service.stype;//不变
		this.Running = true;
		this.cpu = service.cpu;//不变
		this.usedcpuA = 0;
		this.usedcpuB = 0;
		this.memory = service.memory;//不变
		this.usedmemoryA =0;
		this.usedmemoryB =0;
		this.hardcost = service.hardcost;//不变
		this.runningday = 0;
		this.datecost = service.datecost;//不变
		this.cost = 0;
		this.ID = ID;//不变
	}
	
	
	
	}

public class ServicesState {
	HashMap<Integer,OwnServices> runningServices = new HashMap<Integer,OwnServices>();//当前正在运行的服务器
	HashMap<Integer,OwnServices> stopServices = new HashMap<Integer,OwnServices>();//当前没在运行的服务器
	int NumOfService=-1;
	//删除虚拟机
	void removevm(OwnVM vmdeleted)
	{
		OwnServices hereservice = this.runningServices.get(vmdeleted.ServiceID);//获取当前虚拟机所在服务器
		int oneortwo = vmdeleted.oneortwo;//判断虚拟机节点数
		int Servicepoint = vmdeleted.Servicepoint;//判断虚拟机节点数
		if(oneortwo ==1)//双节点情况
		{
			hereservice.AVMs.remove(hereservice.AVMs.indexOf(vmdeleted.ID));
			hereservice.BVMs.remove(hereservice.BVMs.indexOf(vmdeleted.ID));
			hereservice.usedcpuA -= vmdeleted.cpu/2;
			hereservice.usedcpuB -= vmdeleted.cpu/2;
			hereservice.usedmemoryA -= vmdeleted.memory/2;
			hereservice.usedmemoryB -= vmdeleted.memory/2;
			
			
		}
		//单节点情况
		else if(Servicepoint==1)
		{
			hereservice.AVMs.remove(hereservice.AVMs.indexOf(vmdeleted.ID));
			hereservice.usedcpuA -= vmdeleted.cpu;
			hereservice.usedmemoryA -= vmdeleted.memory;
		}
		else if(Servicepoint==2)
		{
			hereservice.BVMs.remove(hereservice.BVMs.indexOf(vmdeleted.ID));
			hereservice.usedcpuB -= vmdeleted.cpu;
			hereservice.usedmemoryB -= vmdeleted.memory;
		}
		else
		{
			System.out.println("代码有误，删除服务器中的虚拟机部分");
		}
		if(hereservice.AVMs.size()==0&&hereservice.BVMs.size()==0)
		{
			StopService(hereservice.ID);
		}
		
	}
	
	//选择服务器
	String ChoiceService(VM vmdata)
	{
		int cpu = vmdata.cpu;
		int memory = vmdata.memory;
		int oneortwo = vmdata.oneortwo;
		
		//单节点
		if(oneortwo == 0)
		{
			//从正在运行的服务器里找
			 for(Entry<Integer,OwnServices> i:this.runningServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>cpu&&serviceinf.memory/2-serviceinf.usedmemoryA>memory)
				 {
					 return Integer.toString(serviceinf.ID)+".A";
				 }
				 
				 else if(serviceinf.cpu/2-serviceinf.usedcpuB>cpu&&serviceinf.memory/2-serviceinf.usedmemoryB>memory)
				 {
					 return Integer.toString(serviceinf.ID)+".B";
				 }
			 }
			 //从停止的服务器里找
			 for(Entry<Integer,OwnServices> i:this.stopServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>cpu&&serviceinf.memory/2-serviceinf.usedmemoryA>memory)
				 {
					 StartService(serviceinf.ID);
					 return Integer.toString(serviceinf.ID)+".A";
				 }
				 
				 else if(serviceinf.cpu/2-serviceinf.usedcpuB>cpu&&serviceinf.memory/2-serviceinf.usedmemoryB>memory)
				 {
					 StartService(serviceinf.ID);
					 return Integer.toString(serviceinf.ID)+".B";
				 }
			 }
		}
		//双节点
		if(oneortwo == 1)
		{
			//从正在运行的服务器里找
			 for(Entry<Integer,OwnServices> i:this.runningServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryA>memory/2&&serviceinf.cpu/2-serviceinf.usedcpuB>cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryB>memory/2)
				 {
					 return Integer.toString(serviceinf.ID)+".AB";
				 }
			 }
			//从停止的服务器里找
			 for(Entry<Integer,OwnServices> i:this.stopServices.entrySet())
			 {
				 //有待优化
				 OwnServices serviceinf = i.getValue();
				 if(serviceinf.cpu/2-serviceinf.usedcpuA>cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryA>memory/2&&serviceinf.cpu/2-serviceinf.usedcpuB>cpu/2&&serviceinf.memory/2-serviceinf.usedmemoryB>memory/2)
				 {
					 StartService(serviceinf.ID);
					 return Integer.toString(serviceinf.ID)+".AB";
				 }
			 }
		}
		return null;
	}
	
	//停止服务器
	void StopService(int ServiceID)
	{
		OwnServices hereservice = this.runningServices.get(ServiceID);
		hereservice.Running = false;
		this.stopServices.put(hereservice.ID, hereservice);
		this.runningServices.remove(hereservice.ID);
	}
	
	//启动服务器
	void StartService(int ServiceID)
	{
		OwnServices hereservice = this.stopServices.get(ServiceID);
		hereservice.Running = true;
		this.runningServices.put(hereservice.ID, hereservice);
		this.stopServices.remove(hereservice.ID);
	}
	
	//添加虚拟机
	void addvm(VM vmdata,int VID,int SID,int Servicepoint)
	{
		OwnServices hereservice = this.runningServices.get(SID);//获取当前虚拟机所在服务器
		int hereServicepoint = Servicepoint;//判断虚拟机节点数
		if(hereServicepoint ==0)//双节点情况
		{
			hereservice.AVMs.add(VID);
			hereservice.BVMs.add(VID);
			hereservice.usedcpuA += vmdata.cpu/2;
			hereservice.usedcpuB += vmdata.cpu/2;
			hereservice.usedmemoryA += vmdata.memory/2;
			hereservice.usedmemoryB += vmdata.memory/2;
			
			
		}
		//单节点情况
		else if(hereServicepoint==1)
		{
			hereservice.AVMs.add(VID);
			hereservice.usedcpuA += vmdata.cpu;
			hereservice.usedmemoryA += vmdata.memory;
		}
		else if(hereServicepoint==2)
		{
			hereservice.BVMs.add(VID);
			hereservice.usedcpuB += vmdata.cpu;
			hereservice.usedmemoryB += vmdata.memory;
		}
		else
		{
			System.out.println("代码有误，添加服务器中的虚拟机部分");
		}
	}
	
	//添加服务器
	OwnServices  AddService(Service service)
	{
		this.NumOfService+=1;
		OwnServices hereservice = new OwnServices(service,this.NumOfService);
		runningServices.put(this.NumOfService, hereservice);
		return hereservice;
	}
	
	//拓展服务器
	OwnServices update(ArrayList<Service> ServiceArray,VM vmdata,int VID)
	{
		OwnServices hereservice = null;
		//双节点
		if(vmdata.oneortwo==1)
		{
			for(Service i:ServiceArray)
			{
				if(i.cpu>vmdata.cpu&&i.memory>vmdata.memory)
				{

					hereservice =AddService(i);
					break;
				}
			}
		}
		//单节点
		if(vmdata.oneortwo==0)
		{
			for(Service i:ServiceArray)
			{
				if(i.cpu/2>vmdata.cpu&&i.memory/2>vmdata.memory)
				{

					hereservice =AddService(i);
					break;
				}
			}
		}
		return hereservice;
	}
	
}
