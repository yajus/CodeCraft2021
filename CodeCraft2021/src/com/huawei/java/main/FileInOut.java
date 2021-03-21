package com.huawei.java.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//文件输入输出
public class FileInOut {
	int N; //服务器类型数量
	int M; //虚拟机类型数量
	int T; //T天请求
	int[] AllT; //每天请求数目
	ArrayList<Service> ServiceTypes  = new ArrayList<Service>(); //服务器类型
	ArrayList<VM> VMTypes = new ArrayList<VM>();//虚拟机类型
	ArrayList<VMPerDay> AllRequests= new ArrayList<VMPerDay>();
    
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
		System.out.println(T);
		VMPerDay day;
		for(int i =1;i<=T;i++)
		{
			day = new VMPerDay();
			line = br.readLine();
			int numofrequest = Integer.parseInt(line);
			for(int j =1;j<=numofrequest;j++)
			{
//			System.out.println(j);
			line = br.readLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//VMRequest(String vtype,int ID)
			if(data[0].equals("add"))
			{
				day.adddatas.add(new VMRequest(data[1],Integer.parseInt(data[2])));
			}
			if(data[0].equals("del"))
			{
				day.deletedatas.add(new VMRequest("",Integer.parseInt(data[1])));
			}
//			System.out.println(VMTypes.get(0).vtype);
			}
			AllRequests.add(day);
//			System.out.println(AllRequests.size());
//			System.out.println(AllRequests.get(AllRequests.size()-1).adddatas.get(0).ID);
		}
		br.close();
	}
	
	public void FileOut()
	{
		String pathname = "./result.txt";
	}
}
