package com.tech.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class GenRef {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;
	
	public String clientName = null;
	public String address = null;
	public String coatingSpec= null;
	public String coatingSys = null;
	public String tcno  = null;
	public String date = null;
	public String maskingReq = null;
	public String nacelevelcert  = null;
	public String inspector  = null;
	
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genref", cascade = CascadeType.ALL)
	private List<Part> partList = new ArrayList<>();
	
	public GenRef() {
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoatingSpec() {
		return coatingSpec;
	}

	public void setCoatingSpec(String coatingSpec) {
		this.coatingSpec = coatingSpec;
	}

	public String getCoatingSys() {
		return coatingSys;
	}

	public void setCoatingSys(String coatingSys) {
		this.coatingSys = coatingSys;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMaskingReq() {
		return maskingReq;
	}

	public void setMaskingReq(String maskingReq) {
		this.maskingReq = maskingReq;
	}

	public String getNacelevelcert() {
		return nacelevelcert;
	}

	public void setNacelevelcert(String nacelevelcert) {
		this.nacelevelcert = nacelevelcert;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public List<Part> getPartList() {
		return partList;
	}

	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}

	
	
	
	
}
