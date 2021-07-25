package com.dongom.springbatch.dnc;

import java.io.Serializable;

public class Dnc implements Serializable {
	
	String cusNo;

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	@Override
	public String toString() {
		return "Dnc [cusNo=" + cusNo + "]";
	}
	
}
