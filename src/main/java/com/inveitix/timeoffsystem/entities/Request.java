package com.inveitix.timeoffsystem.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Request
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long userId;

	@NotBlank
	private String type;

	private int days;

	@NotBlank
	private String dates;

	@NotBlank
	@Column(columnDefinition="text")
	private String reason;

	@Column(columnDefinition="text")
	private String note;

	private Date submitTime;

	@Column(columnDefinition="varchar(255) default 'Not Reviewed'")
	private String status;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date today) {
		this.submitTime = today;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}