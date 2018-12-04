package com.tech.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Job implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "JOB_ID")
	private Long id;
	
	
	private String clientName = null;
	private Date date = null;
	private String tcno;
	
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
	private GenRef genref;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
	//@JoinTable(name = "JOB_DEGREASING", joinColumns = { @JoinColumn(name = "JOB_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID") })
	private List<Degreasing> degreasing = new ArrayList<>();
	
	public Job() {
			super();
		}

	
	public GenRef getGenref() {
		return genref;
	}
	
	public void setGenref(GenRef genref) {
		this.genref = genref;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	public String getTcno() {
		return tcno;
	}


	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	
	


	public List<Degreasing> getDegreasing() {
		return degreasing;
	}


	public void setDegreasing(List<Degreasing> degreasing) {
		this.degreasing = degreasing;
	}


	@Override
	public String toString() {
		return "Job [id=" + id + ", clientName=" + clientName + ", date=" + date + ", tcno=" + tcno + ", genref="
				+ genref + "]";
	}
	
	
	
	
}
