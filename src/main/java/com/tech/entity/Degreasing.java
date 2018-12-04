package com.tech.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "degreasing",
uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class Degreasing implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id", nullable = false)
	
	private Job job;
	
	private String stime ;
	private String endtime;
	private String product;
	private String methoddegreasing;
	private String qnt;
	private String operator;
	private String operationtime;
	
	public Degreasing() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMethoddegreasing() {
		return methoddegreasing;
	}
	public void setMethoddegreasing(String methoddegreasing) {
		this.methoddegreasing = methoddegreasing;
	}
	public String getQnt() {
		return qnt;
	}
	public void setQnt(String qnt) {
		this.qnt = qnt;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationtime() {
		return operationtime;
	}
	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}
	
	
}
