package com.huawei.java.main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
//程序入口函数



public class Main {
	
	public static void main(String[] args) {
		FileInOut file = new FileInOut();
		String path= "/Users/zhengxiaopeng/Desktop/华为软件精英挑战赛2021/training-2.txt";
		try{
			file.FileIn(path);//test
//			System.out.println("OK");
		}
		catch(Exception e)
		{
			
		}
		Algorithm method = new Algorithm();
		for(int i = 1;i<=file.T;i++)
		{
//			System.out.println(i);
			method.ProcessEveryday(file, i);
		}
		file.FileOut(method);
		
		
//排序类测试
//		range test =  new range(a);//
//		for(int i = 0;i<test.ServicesRangeByCpuAndGpu.size();i++)
//		System.out.println(test.ServicesRangeByCpuAndGpu.get(i).stype);
//		test.RangeByCpuAndGpu();
		
//		System.out.println("next");
//		for(int i = 0;i<test.ServicesRangeByCpuAndGpu.size();i++)
//		System.out.println(test.ServicesRangeByCpuAndGpu.get(i).stype);
    }
}