package com.omor.digital.base.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

	@JsonIgnore
	private Exception exception;

	private int code;
	private String type;
	private String message;

	public ErrorResponse() {

	}

	public ErrorResponse(int code, Exception e) {
		this.code = code;
		this.exception = e;
		this.message = e.getMessage();
		this.type = e.getClass().getSimpleName();
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}