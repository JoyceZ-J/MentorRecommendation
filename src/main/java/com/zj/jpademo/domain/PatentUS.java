package com.zj.jpademo.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  
 * @Author  dragonzl
 * @Date 2021-05-19 10:28:13 
 */

@Entity( name ="USApatent")
@Table
public class PatentUS implements Serializable {

	private static final long serialVersionUID =  4028831045285941707L;

	@Id
	@GeneratedValue
	private Long id;

   	@Column(name = "patentType" )
	private String patentType;

   	@Column(name = "patentName" )
	private String patentName;

   	@Column(name = "PatentCode" )
	private String patentCode;

   	@Column(name = "PublicationNo" )
	private String publicationNo;

   	@Column(name = "inventor" )
	private String inventor;

   	@Column(name = "Applicant" )
	private String applicant;

   	@Column(name = "MainClassCode" )
	private String mainClassCode;

   	@Column(name = "ApplicationDate" )
	private String applicationDate;

   	@Column(name = "PublicationDate" )
	private String publicationDate;

   	@Column(name = "CountryOrganization" )
	private String countryOrganization;

   	@Column(name = "Agency" )
	private String agency;

   	@Column(name = "agent" )
	private String agent;

   	@Column(name = "url" )
	private String url;

   	@Column(name = "patent_abstract" )
	private String patentAbstract;

	public String getPatentType() {
		return this.patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

	public String getPatentName() {
		return this.patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getPatentCode() {
		return this.patentCode;
	}

	public void setPatentCode(String patentCode) {
		this.patentCode = patentCode;
	}

	public String getPublicationNo() {
		return this.publicationNo;
	}

	public void setPublicationNo(String publicationNo) {
		this.publicationNo = publicationNo;
	}

	public String getInventor() {
		return this.inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getMainClassCode() {
		return this.mainClassCode;
	}

	public void setMainClassCode(String mainClassCode) {
		this.mainClassCode = mainClassCode;
	}

	public String getApplicationDate() {
		return this.applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getCountryOrganization() {
		return this.countryOrganization;
	}

	public void setCountryOrganization(String countryOrganization) {
		this.countryOrganization = countryOrganization;
	}

	public String getAgency() {
		return this.agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPatentAbstract() {
		return this.patentAbstract;
	}

	public void setPatentAbstract(String patentAbstract) {
		this.patentAbstract = patentAbstract;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
