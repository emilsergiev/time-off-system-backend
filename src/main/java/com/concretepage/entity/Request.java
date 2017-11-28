package com.concretepage.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="requests")
public class Request implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int id;  
	@Column(name="dates")
    private String dates;
	@Column(name="days")	
	private String days;
	@Column(name="note")	
	private String note;
	@Column(name="reason")	
	private String reason;
	@Column(name="status")	
	private String status;
	@Column(name="submit_time")	
	private String submit_time;
	@Column(name="type")	
	private String type;
	@Column(name="user_id")	
	private int user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
} 