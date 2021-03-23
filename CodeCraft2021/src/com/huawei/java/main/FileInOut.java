package com.huawei.java.main;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//文件输入输出
public class FileInOut {
	int N; //服务器类型数量
	int M; //虚拟机类型数量
	int T; //T天请求
//	int[] AllT; //每天请求数目
	HashMap<String,Service> ServiceTypes  = new HashMap<String,Service>(); //服务器类型
	HashMap<String,VM> VMTypes = new HashMap<String,VM>();//虚拟机类型
	ArrayList<VMPerDay> AllRequests= new ArrayList<VMPerDay>();//T天的请求数据
    
	public void FileIn(String path) throws IOException
	{
		Scanner s = new Scanner(System.in);
//		String pathname = path;
//		File filename = new File(pathname);
//		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
//        BufferedReader br = new BufferedReader(reader);
		String line = "";
		
		//读取服务器类型
		line = s.nextLine();
		N = Integer.parseInt(line);
		for(int i =1;i<=N;i++)
		{
			line = s.nextLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//Service(String stype,int cpu,int memory,int hardcost,int datecost)
			ServiceTypes.put(data[0],new Service(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4])));
//			System.out.println(ServiceTypes.get(0).datecost);
		}
		
		//读取虚拟机类型
		line = s.nextLine();
		M = Integer.parseInt(line);
		for(int i =1;i<=M;i++)
		{
			line = s.nextLine();
			line = line.replace("(","");
			line = line.replace(")","");
			line = line.replace(" ","");
			String[] data = line.split(",");
			//VM(String vtype,int cpu,int memory,int oneortwo)
			VMTypes.put(data[0],new VM(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3])));
//			System.out.println(VMTypes.get(0).vtype);
		}
		
		
		//读取T天的数据
		line = s.nextLine();
		T = Integer.parseInt(line);
//		System.out.println(T);
		VMPerDay day;
		for(int i =1;i<=T;i++)
		{
			day = new VMPerDay();
			line = s.nextLine();
			int numofrequest = Integer.parseInt(line);
			for(int j =1;j<=numofrequest;j++)
			{
//			System.out.println(j);
			line = s.nextLine();
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
		s.close();
	}
	
//	public void FileOut(Algorithm method) throws IOException
//	{
//		String pathname = "./result.txt";
//		BufferedWriter  writer = new BufferedWriter(new FileWriter(pathname));
////		try() {
////	        writer.write(content);
////	        writer.flush();
////	    }
//		int day = method.resultarrange.alllist.size();
//		ArrayList<arrangelist> arrangeresult = method.resultarrange.alllist;
//		ArrayList<ServiceTypeAndNum> buyresult= method.resultbuy.s;
//		String out;
//		int size;
//		ArrayList<arrangebase> arrangelist;
//		for(int i =0;i<day;i++)
//		{
//			size = arrangeresult.get(i).alist.size();
//			out = "(purchase, "+String.valueOf(size)+")";
//			arrangelist = arrangeresult.get(i).alist;
//			writer.write(out);
//			for(int j = 0;j<size;j++)
//			{
//				if(arrangelist.get(j).point==)
//				out = "("+arrangelist.get(j).ServiceID;
//			}
//		}
//	}
	public void FileOut(Algorithm method)
	{
		String pathname = "./result.txt";
		ArrayList<ServiceTypeAndNum> serviceTypeAndNums = method.resultbuy.s;
		ArrayList<arrangelist> arrangelists = method.resultarrange.alllist;
		
		try {
//			FileWriter fw = new FileWriter(pathname, true);
//            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i=0; i<serviceTypeAndNums.size(); i++) { //0day-Tday
    			int service_num = serviceTypeAndNums.get(i).mapTypeNum.size(); //mapTypeNum key/value: service type/number 
    			int alist_size = arrangelists.get(i).alist.size(); //alist: arrangebase(ServiceID, point)
//    			bw.write("(purchase, " + service_num + ")\n");
    			System.out.print("(purchase, " + service_num + ")\n");
    			for(String str: serviceTypeAndNums.get(i).mapTypeNum.keySet()) {
    				int value = serviceTypeAndNums.get(i).mapTypeNum.get(str);
    				String out = "(" + str + ", " + value + ")\n";
//    				bw.write(out);
    				System.out.print(out);
    			}
//    			bw.write("(migration, 0)\n");
    			System.out.print("(migration, 0)\n");
    			for(arrangebase id_point: arrangelists.get(i).alist) {
    				int id = id_point.ServiceID, point = id_point.point;
    				String out = "";
    				if(point == 0) {//双节点
    					out = "(" + id + ")\n";
    				}else if(point == 1) {
    					out = "(" + id + ", A)\n";
    				}else {
    					out = "(" + id + ", B)\n";
    				}
//    				System.out.println(out);
//    				bw.write(out);
    				System.out.print(out);
//    				bw.flush();
    			}
    		}
//            bw.close();
//            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
}
