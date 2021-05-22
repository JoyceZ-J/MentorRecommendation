package com.zj.jpademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  
 * @Author  dragonzl
 * @Date 2021-05-19 10:27:12 
 */

@Entity( name ="BrazilPatent")
@Table
public class PatentBrazil implements Serializable {

	private static final long serialVersionUID =  6726491100853718902L;

	@Id
   	@Column(name = "ID" )
	private Long id;

   	@Column(name = "title" )
	private String title;

   	@Column(name = "patent_abstract" )
	private String patentAbstract;

   	@Column(name = "country" )
	private String country;

   	@Column(name = "ApplicationNumber" )
	private String applicationNumber;

   	@Column(name = "ApplicationTime" )
	private String applicationTime;

   	@Column(name = "PublishNumber" )
	private String publishNumber;

   	@Column(name = "PublishTime" )
	private String publishTime;

   	@Column(name = "PublishType" )
	private String publishType;

   	@Column(name = "Applicants" )
	private String applicants;

   	@Column(name = "inventors" )
	private String inventors;

   	@Column(name = "organizations" )
	private String organizations;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPatentAbstract() {
		return this.patentAbstract;
	}

	public void setPatentAbstract(String patentAbstract) {
		this.patentAbstract = patentAbstract;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationTime() {
		return this.applicationTime;
	}

	public void setApplicationTime(String applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getPublishNumber() {
		return this.publishNumber;
	}

	public void setPublishNumber(String publishNumber) {
		this.publishNumber = publishNumber;
	}

	public String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishType() {
		return this.publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public String getApplicants() {
		return this.applicants;
	}

	public void setApplicants(String applicants) {
		this.applicants = applicants;
	}

	public String getInventors() {
		return this.inventors;
	}

	public void setInventors(String inventors) {
		this.inventors = inventors;
	}

	public String getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(String organizations) {
		this.organizations = organizations;
	}
}
