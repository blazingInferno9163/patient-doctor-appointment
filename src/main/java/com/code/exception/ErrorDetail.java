package com.code.exception;

import java.util.Date;

//import java.sql.Date;

public class ErrorDetail {
	private Date timestamp;
	private String message;
	private String details;
	public ErrorDetail(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public ErrorDetail() {
		super();
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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
	@Override
	public String toString() {
		return "ErrorDetail [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
	
	

}
