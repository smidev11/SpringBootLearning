package com.tech.entity;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class Service {
	
	private SessionFactory hibernateFactory;
	
	public Service(EntityManagerFactory factory){
		 if(factory.unwrap(SessionFactory.class) == null){
		      throw new NullPointerException("factory is not a hibernate factory");
		    }
		    this.hibernateFactory = factory.unwrap(SessionFactory.class);
	}
	
	public void test(){
		 Session sessionOne = hibernateFactory.openSession();
	      sessionOne.beginTransaction();
	      
	      Job j = sessionOne.load(Job.class, new Long(1));
	      System.out.println("");
	      String c = j.getClientName();
	      GenRef g = j.getGenref();
	      System.out.println("");
	      
		
	}
}
