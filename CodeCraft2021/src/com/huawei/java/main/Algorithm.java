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
	void ProcessEveryday(FileInOut datas,int n)//n表示第几天
	{
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
			int VID = adddata.ID;//虚拟机ID
			int ID = allservice.ChoiceService();
			OwnVM  vm= allvm.addvm(datas.VMTypes.get(vtype),VID,ServicesState allservice);//被移除的虚拟机
//			allservice.removevm(vm);
			
		}
		
	}
	
	void deleteVM(int day)
	{
		
	}
//	void 
	
}
