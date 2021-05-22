package com.zj.jpademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description  
 * @Author  dragonzl
 * @Date 2021-05-21 09:42:38 
 */

@Entity( name ="patents")
public class PatentEN  implements Serializable {

	private static final long serialVersionUID =  8472326000618231496L;

	@Id
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "patent_type" )
	private String patentType;

   	@Column(name = "patent_name" )
	private String patentName;

   	@Column(name = "patent_code" )
	private String patentCode;

   	@Column(name = "publication_no" )
	private String publicationNo;

   	@Column(name = "inventor" )
	private String inventor;

   	@Column(name = "applicant" )
	private String applicant;

   	@Column(name = "mainclass_code" )
	private String mainclassCode;

   	@Column(name = "application_date" )
	private String applicationDate;

   	@Column(name = "publication_date" )
	private String publicationDate;

   	@Column(name = "country_organization" )
	private String countryOrganization;

   	@Column(name = "agency" )
	private String agency;

   	@Column(name = "agent" )
	private String agent;

   	@Column(name = "platform" )
	private String platform;

   	@Column(name = "url" )
	private String url;

   	@Column(name = "patent_abstract" )
	private String patentAbstract;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getMainclassCode() {
		return this.mainclassCode;
	}

	public void setMainclassCode(String mainclassCode) {
		this.mainclassCode = mainclassCode;
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

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
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
}
