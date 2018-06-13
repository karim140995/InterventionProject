package com.example.model.pojo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="intervention",schema="public")
public class Intervention {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idintervention;
	private String objet;
	private String description;
	private String remarque;
	private String payement;
	private	String priorité;
	private String etat;
	private Date date_cloture;
	private Date date_ajout;
	private String heuredebut_cloture;
	private String heurefin_cloture;
	private Integer client_idclient;
	
    public Intervention() {
		// TODO Auto-generated constructor stub
	}
	 
	public Intervention(Integer idintervention, String objet, String description, String remarque, String payement,
			String priorité, String etat, Date date_cloture, Date date_ajout, String heuredebut_cloture,
			String heurefin_cloture, Integer client_idclient) {
		this.idintervention = idintervention;
		this.objet = objet;
		this.description = description;
		this.remarque = remarque;
		this.payement = payement;
		this.priorité = priorité;
		this.etat = etat;
		this.date_cloture = date_cloture;
		this.date_ajout = date_ajout;
		this.heuredebut_cloture = heuredebut_cloture;
		this.heurefin_cloture = heurefin_cloture;
		this.client_idclient = client_idclient;
	}
	public Integer getIdintervention() {
		return idintervention;
	}
	public void setIdintervention(Integer idintervention) {
		this.idintervention = idintervention;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public String getPayement() {
		return payement;
	}
	public void setPayement(String payement) {
		this.payement = payement;
	}
	public String getPriorité() {
		return priorité;
	}
	public void setPriorité(String priorité) {
		this.priorité = priorité;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getDate_cloture() {
		return date_cloture;
	}
	public void setDate_cloture(Date date_cloture) {
		this.date_cloture = date_cloture;
	}
	public Date getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}
	public String getHeuredebut_cloture() {
		return heuredebut_cloture;
	}
	public void setHeuredebut_cloture(String heuredebut_cloture) {
		this.heuredebut_cloture = heuredebut_cloture;
	}
	public String getHeurefin_cloture() {
		return heurefin_cloture;
	}
	public void setHeurefin_cloture(String heurefin_cloture) {
		this.heurefin_cloture = heurefin_cloture;
	}
	public Integer getClient_idclient() {
		return client_idclient;
	}
	public void setClient_idclient(Integer client_idclient) {
		this.client_idclient = client_idclient;
	}
}
