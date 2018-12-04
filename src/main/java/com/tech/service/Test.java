package com.tech.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		try{
			String sDate1="12/4/1998";  
		    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		    System.out.println(sDate1+"\t"+date1);
		}catch (Exception e){
			e.printStackTrace();
		}
		  
	}
}	
