package com.example.model.pojo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="license",schema="public")

public class License {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlicense;
	private String application;
	private Integer client_idclient;
	private Date date_expiration;
	
	
	public License() {
		// TODO Auto-generated constructor stub
	}
	
	public License(Integer idlicense, String application, Integer client_idclient, Date date_expiration) {
		this.idlicense = idlicense;
		this.application = application;
		this.client_idclient = client_idclient;
		this.date_expiration = date_expiration;
	}
	public Integer getIdlicense() {
		return idlicense;
	}
	public void setIdlicense(Integer idlicense) {
		this.idlicense = idlicense;
	}
	public Integer getClient_idclient() {
		return client_idclient;
	}
	public void setClient_idclient(Integer client_idclient) {
		this.client_idclient = client_idclient;
	}
	public Date getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
}
