package com.tech.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "part",uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Part {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "genref_id", nullable = false)
	private GenRef genref;
	
	public String pono = null;
	public String partno = null;
	public String serialno = null;
	public String heatno = null;
	public String heatlotno = null;
	public String partname = null;
	public String qnt = null;
	public String jobwo = null;
	public String tcno  = null;
	public String revNo= null;
	public String inspector  = null;
	
	public Part(){
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GenRef getGenref() {
		return genref;
	}
	public void setGenref(GenRef genref) {
		this.genref = genref;
	}
	public String getPono() {
		return pono;
	}
	public void setPono(String pono) {
		this.pono = pono;
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
	public String getPartname() {
		return partname;
	}
	public void setPartname(String partname) {
		this.partname = partname;
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
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	
	
	
}
