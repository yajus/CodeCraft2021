package com.huawei.java.main;

import java.util.HashMap;

//正在维护的虚拟机对象
class OwnVM
{
	int ServiceID;
	int Servicepoint; //0为双节点 1为A节点 2为B节点
	String vtype;
	int cpu;
	int memory;
	int oneortwo;
	int ID;
	public OwnVM(VM vm, int ID)
	{
		this.vtype = vm.vtype;
		this.cpu = vm.cpu;
		this.memory = vm.memory;
		this.oneortwo = vm.oneortwo;
		this.ID = ID;
	}
	}
public class VMState {
	HashMap<Integer,OwnVM> allVM = new HashMap<Integer,OwnVM>();
	OwnVM removevm(int ID)
	{
		OwnVM tmp;
		tmp = this.allVM.get(ID);
		this.allVM.remove(ID);
		return tmp;
	}
	
	OwnVM addvm(VM vm,int VID,int SID)
	{
		OwnVM tmp = new OwnVM(vm,VID);
		
		return tmp;
		
	}
	
}
