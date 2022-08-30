package com.wisenut.study.model;

import lombok.Data;

@Data
public class AjaxResponse {

	boolean success;
	String errorMsg;
	String redirectUrl;
	InData indata;
	String resMsg;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public InData getIndata() {
		return indata;
	}
	public void setIndata(InData indata) {
		this.indata = indata;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	
	
	
}
