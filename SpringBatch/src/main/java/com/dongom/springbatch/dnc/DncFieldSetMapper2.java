package com.dongom.springbatch.dnc;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class DncFieldSetMapper2{
	@Bean
	public FieldSetMapper fieldSetMapper() {
		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();

		fieldSetMapper.setPrototypeBeanName("dnc");

		return fieldSetMapper;
	}

	@Bean
	@Scope("dnc")
	public Dnc dnc() {
		return new Dnc();
	}	

}
