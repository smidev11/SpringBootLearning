package com.tech.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	public String pono = null;
	public String coatingSpec= null;
	public String coatingSys = null;
	public String partno = null;
	public String serialno = null;
	public String heatno = null;
	public String heatlotno = null;
	public String desc = null;
	public String qnt = null;
	public String jobwo = null;
	public String tcno  = null;
	public String revNo= null;
	public String date = null;
	public String maskingReq = null;
	public String nacelevelcert  = null;
	public String inspector  = null;
	
	
	public GenRef() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPono() {
		return pono;
	}
	public void setPono(String pono) {
		this.pono = pono;
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
	public String getPartno() {
		return partno;
	}
	public void setPartno(String partno) {
		this.partno = partno;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getHeatno() {
		return heatno;
	}
	public void setHeatno(String heatno) {
		this.heatno = heatno;
	}
	public String getHeatlotno() {
		return heatlotno;
	}
	public void setHeatlotno(String heatlotno) {
		this.heatlotno = heatlotno;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getQnt() {
		return qnt;
	}
	public void setQnt(String qnt) {
		this.qnt = qnt;
	}
	public String getJobwo() {
		return jobwo;
	}
	public void setJobwo(String jobwo) {
		this.jobwo = jobwo;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = revNo;
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
	
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	
	
}
