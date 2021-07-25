package com.dongom.springbatch.dnc;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class DncFieldSetMapper implements FieldSetMapper<Dnc> {
	

	public Dnc mapFieldSet(FieldSet fs) {
		
		Dnc dnc = new Dnc();
		dnc.setCusNo(fs.readString(0));
		return dnc;
	}

}
