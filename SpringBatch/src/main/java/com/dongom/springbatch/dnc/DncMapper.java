package com.dongom.springbatch.dnc;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class DncMapper implements FieldSetMapper<Dnc>{
	private String cusNo;

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	@Override
	public Dnc mapFieldSet(FieldSet fs) throws BindException {
		if(fs == null) {
			return null;
		}
		Dnc dnc = new Dnc();
		dnc.setCusNo(fs.readString("cusNo"));
		return dnc;
	}
}
