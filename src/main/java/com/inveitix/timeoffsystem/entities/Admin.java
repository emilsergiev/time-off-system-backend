package com.inveitix.timeoffsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String password;
	private int egn;
	private int pto;
	private int upto;

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
	public int getEgn() {
		return egn;
	}
	public void setEgn(int egn) {
		this.egn = egn;
	}
	public int getPto() {
		return pto;
	}
	public void setPto(int pto) {
		this.pto = pto;
	}
	public int getUpto() {
		return upto;
	}
	public void setUpto(int upto) {
		this.upto = upto;
	}

}