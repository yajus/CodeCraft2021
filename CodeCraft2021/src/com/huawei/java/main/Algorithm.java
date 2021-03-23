package com.huawei.java.main;

import java.util.ArrayList;

//进行服务器的调度
public class Algorithm {
	//VMState 
	//ServiceState 
	//FileInOut datas
	//服务器状态 AB节点各用了多少 服务器开机无否 开机了多少天
	//
	VMState allvm = new VMState(); //所有的vm
	ServicesState allservice = new ServicesState(); //所有的vm
	range allrange;
	BuyService resultbuy = new BuyService();
	void ProcessEveryday(FileInOut datas,int n)//n表示第几天
	{
		allrange = new range(datas);
		ArrayList<VMRequest> DeleteTodayDatas = datas.AllRequests.get(n-1).deletedatas;
		ArrayList<VMRequest> AddTodayDatas = datas.AllRequests.get(n-1).adddatas;
		VMRequest deldata;
		VMRequest adddata;
		for(int i = 0;i<DeleteTodayDatas.size();i++)
		{
			deldata = DeleteTodayDatas.get(i);
			int VID = deldata.ID;//虚拟机ID
			OwnVM  vm= allvm.removevm(VID);//被移除的虚拟机
			allservice.removevm(vm);
			
		}
		for(int i = 0;i<AddTodayDatas.size();i++)
		{
			adddata = AddTodayDatas.get(i);
			String vtype = adddata.vtype;//虚拟机类型
			VM vmdata = datas.VMTypes.get(vtype);//要添加的虚拟机数据
			int VID = adddata.ID;//虚拟机ID
			String IDandPoint = allservice.ChoiceService(vmdata);//选择服务器
			int Servicepoint=0;
			int SID=0;
			if(IDandPoint==null)
			{
				//扩容操作
				allrange.RangeByCpuAndGpu();
				SID = allservice.update(allrange.ServicesRangeByCpuAndMemory,vmdata,adddata.ID);
				
				if(vmdata.oneortwo==1)
				Servicepoint = 1;
				if(vmdata.oneortwo==2)
					Servicepoint = 0; 
			}
			else
			{
			String[] IDPoint=IDandPoint.split(".");
			SID = Integer.parseInt(IDPoint[0]);//虚拟机号码
			
			if((IDPoint[1].equals("A")))
				Servicepoint= 1;
			if((IDPoint[1].equals("B")))
				Servicepoint= 2;
			if((IDPoint[1].equals("AB")))
				Servicepoint= 0;
			
			}
			allvm.addvm(vmdata, VID, SID, Servicepoint);;
			allservice.addvm(vmdata,VID, SID, Servicepoint);
		}
		
	}
	
}
