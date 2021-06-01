package com.dongom.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
		/*
		SpringApplication application = new SpringApplication(SpringBatchApplication.class);
		Properties properties = new Properties();
		properties.put("spring.batch.job.enabled", false);
		application.setDefaultProperties(properties);
		*/
	}

}
