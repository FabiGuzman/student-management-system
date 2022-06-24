package com.system.blog.sms.dto;

import java.util.Date;

public class ErrorDetails {

	private Date markOfTime;
	private String message;
	private String details;
	
	public ErrorDetails(Date markOfTime, String message, String details) {
		super();
		this.markOfTime = markOfTime;
		this.message = message;
		this.details = details;
	}

	public Date getMarkOfTime() {
		return markOfTime;
	}

	public void setMarkOfTime(Date markOfTime) {
		this.markOfTime = markOfTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
		
}
