package com.huawei.java.main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//请求删除
//class DeleteVMRequest
//{
//	int ID;
//	public DeleteVMRequest(int ID)
//	{
//		this.ID = ID;
//	}
//}
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