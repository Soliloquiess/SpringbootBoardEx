package com.wisenut.study.model;

import lombok.Data;

@Data
public class InData {
	
	Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
