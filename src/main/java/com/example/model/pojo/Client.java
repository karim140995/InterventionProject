package com.example.model.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@javax.persistence.Entity
@Table(name="client",schema="public")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idclient;
	private String nom_client;
	private String contact;
	private String adresse;
	private String tel;
	
	
	public Client(){}
	
	public Client(Integer idclient, String nom_client, String contact, String adresse, String tel) {
		this.idclient=idclient;
		this.nom_client=nom_client;
		this.contact=contact;
		this.adresse=adresse;
		this.tel=tel;
	}
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
