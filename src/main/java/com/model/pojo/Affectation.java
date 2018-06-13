package com.example.model.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="affectation",schema="public")

public class Affectation {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idaffectation;
	private String description;
	private Integer intervention_idintervention;
	private Integer intervenant_idintervenant;
	
	public Affectation() {}
	
	public Affectation(Integer idaffectation, String description, Integer intervention_idintervention,
			Integer intervenant_idintervenant) {
		this.idaffectation = idaffectation;
		this.description = description;
		this.intervention_idintervention = intervention_idintervention;
		this.intervenant_idintervenant = intervenant_idintervenant;
	}
	public Integer getIdaffectation() {
		return idaffectation;
	}
	public void setIdaffectation(Integer idaffectation) {
		this.idaffectation = idaffectation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIntervention_idintervention() {
		return intervention_idintervention;
	}
	public void setIntervention_idintervention(Integer intervention_idintervention) {
		this.intervention_idintervention = intervention_idintervention;
	}
	public Integer getIntervenant_idintervenant() {
		return intervenant_idintervenant;
	}
	public void setIntervenant_idintervenant(Integer intervenant_idintervenant) {
		this.intervenant_idintervenant = intervenant_idintervenant;
	}
	
}
