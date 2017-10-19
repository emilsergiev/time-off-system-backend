package com.inveitix.timeoffsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean admin;

	private String name;

	private String email;

	private String password;

	private String egn;

	private int pto;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEgn() {
		return egn;
	}
	public void setEgn(String egn) {
		this.egn = egn;
	}
	public int getPto() {
		return pto;
	}
	public void setPto(int pto) {
		this.pto = pto;
	}
	public boolean getAdmin() {
		return admin;
	}

}