package com.example.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users",schema="public")

public class Users {
	
	@Id
	private String username;
	private String password;
	private Integer enabled;
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	public Users(Integer idusers, String username, String password, Integer enabled) {
		this.username = username;
		this.password = password;
		this.enabled= enabled;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
}
