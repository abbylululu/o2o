package com.lululu.O2O.dto;

public class Result<T> {

	private boolean success;
	
	private T data;
	
	private String errorMsg;
	
	private int errorCod;

	public Result() {

	}
	
	// success constructor
	public Result(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	// error constructor
	public Result(boolean success, int errorCod, String errorMsg) {
		super();
		this.success = success;
		this.errorMsg = errorMsg;
		this.errorCod = errorCod;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCod() {
		return errorCod;
	}

	public void setErrorCod(int errorCod) {
		this.errorCod = errorCod;
	}
	
}
