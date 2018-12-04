package com.tech.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.entity.GenRef;
import com.tech.entity.Part;

public class Test {
	public static void main(String[] args) {
		try{
			ObjectMapper om = new ObjectMapper();
			
			GenRef ref = new GenRef();
			ref.setClientName("TestC");
			ref.setAddress("PPPune");
			
			ref.setTcno("TC-999");
			
			Part p1 = new Part();
			p1.setInspector("INS1");
			p1.setPartname("Part1");
			p1.setGenref(ref);
			Part p2 = new Part();
			p2.setPartname("Part2");
			p2.setInspector("ins2");
			p2.setRevNo("3");
			p2.setGenref(ref);
			
			List<Part> plist = new ArrayList<>();
			plist.add(p2);
			
			plist.add(p1);
			ref.setPartList(plist);
			
			om.writeValue(new File("c.txt"), ref);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		  
	}
}	
