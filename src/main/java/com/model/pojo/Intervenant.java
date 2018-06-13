package com.example.model.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intervenant",schema="public")
public class Intervenant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idintervenant;
	private String nom;
	private String prenom;
	private String email;
	private String users_idusers;
	
	public Intervenant() {
		// TODO Auto-generated constructor stub
	}
	
	public Intervenant(Integer idintervenant, String nom, String prenom, String email, String users_idusers) {
		this.idintervenant=idintervenant;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.users_idusers=users_idusers;
	}

	
	public Integer getIdintervenant() {
		return idintervenant;
	}
	public void setIdintervenant(Integer idintervenant) {
		this.idintervenant = idintervenant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsers_idusers() {
		return users_idusers;
	}
	public void setUsers_idusers(String users_idusers) {
		this.users_idusers = users_idusers;
	}
}
