package com.example.exceptions;

import java.util.Date;

import lombok.Data;

public class ApiError {
	
	private Integer errorcode;
	private String errorDesc;
	private Date date;
	
	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public ApiError(Integer errorcode, String errorDesc, Date date) {
		super();
		this.errorcode = errorcode;
		this.errorDesc = errorDesc;
		this.date = date;
	}

}
