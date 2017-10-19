package com.inveitix.timeoffsystem.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterForm
{
	@NotBlank
    @Size(min=2, max=127)
	private String name;

	@Email
	@NotBlank
	private String email;

	@NotBlank
    @Size(min=10, max=10)
	private String egn;

	@NotBlank
    @Size(min=8, max=255)
	private String password;

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

	public String getEgn() {
		return egn;
	}

	public void setEgn(String egn) {
		this.egn = egn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
