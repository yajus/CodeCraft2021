package com.huawei.java.main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//服务器
class Service
{
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


//虚拟机
class VM
{
	String vtype;
	int cpu;
	int memory;
	int oneortwo;
	public VM(String vtype,int cpu,int memory,int oneortwo)
	{
		this.vtype = vtype;
		this.cpu = cpu;
		this.memory = memory;
		this.oneortwo = oneortwo;
	}
}

//请求添加或删除
class VMRequest
{
	String vtype;
	int ID;
	public VMRequest(String vtype,int ID)
	{
		this.vtype = vtype;
		this.ID = ID;
	}
}

//请求删除
//class DeleteVMRequest
//{
//	int ID;
//	public DeleteVMRequest(int ID)
//	{
//		this.ID = ID;
//	}
//}

//文件输入输出
class FileInOut
{
	int N; //服务器类型数量
	int M; //虚拟机类型数量
	int T; //T天请求
	int[] AllT; //每天请求数目
	ArrayList<Service> ServiceTypes  = new ArrayList<Service>(); //服务器类型
	ArrayList<VM> VMTypes = new ArrayList<VM>();//虚拟机类型
	ArrayList<HashMap<String,ArrayList<VMRequest>>> AllRequests= new ArrayList<HashMap<String,ArrayList<VMRequest>>>();
    
	public void FileIn(String path) throws IOException
	{
		String pathname = path;
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
		String line = "";
		
		//读取服务器类型
		line = br.readLine();
		N = Integer.parseInt(line);
		for(int i =1;i<=N;i++)
		{
			line = br.readLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//Service(String stype,int cpu,int memory,int hardcost,int datecost)
			ServiceTypes.add(new Service(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4])));
//			System.out.println(ServiceTypes.get(0).datecost);
		}
		
		//读取虚拟机类型
		line = br.readLine();
		M = Integer.parseInt(line);
		for(int i =1;i<=M;i++)
		{
			line = br.readLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//VM(String vtype,int cpu,int memory,int oneortwo)
			VMTypes.add(new VM(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3])));
//			System.out.println(VMTypes.get(0).vtype);
		}
		
		
		//读取T天的数据
		line = br.readLine();
		T = Integer.parseInt(line);
		for(int i =1;i<=T;i++)
		{
			ArrayList<VMRequest> adddatas= new ArrayList<VMRequest>();
			ArrayList<VMRequest> deletedatas= new ArrayList<VMRequest>();
			//ArrayList<HashMap<String,ArrayList<VMRequest>>> AllRequests
			line = br.readLine();
			int numofrequest = Integer.parseInt(line);
			for(int j =1;j<=numofrequest;j++)
			{
			line = br.readLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//VM(String vtype,int cpu,int memory,int oneortwo)
			VMTypes.add(new VM(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3])));
//			System.out.println(VMTypes.get(0).vtype);
			}
		}
	}
	public void FileOut()
	{
		String pathname = "./result.txt";
	}
}

public class Main {
	
	public static void main(String[] args) {
		FileInOut a = new FileInOut();
		String path= "/home/zhengxp/桌面/华为软件精英挑战赛2021/training-1.txt";
		try{
			a.FileIn(path);
		}
		catch(Exception e)
		{
			
		}
    }
}