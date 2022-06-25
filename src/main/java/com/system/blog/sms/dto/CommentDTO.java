package com.system.blog.sms.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDTO {

	private long id;
	
	@NotEmpty(message = "The name don't must be empty or null")
	private String name;
	
	@NotEmpty(message = "The email don't must be empty or null")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 10, message = "The body of commentary must have at least ten characters")
	private String body;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public CommentDTO() {
		super();
	}

}
