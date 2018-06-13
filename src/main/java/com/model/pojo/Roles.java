package com.example.model.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles",schema="public")

public class Roles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer user_role_id;
	private String username;
	private String role;
	
	
	public Roles() {
		// TODO Auto-generated constructor stub
	} 
	
	
	
	public Roles(Integer user_role_id, String username, String role) {
		this.user_role_id = user_role_id;
		this.setUsername(username);
		this.role = role;
	}
	
	public Integer getUser_role_id()
	{
		return user_role_id;
	}
	
	public Integer setUser_role_id(Integer user_role_id )
	{
		return this.user_role_id=user_role_id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
