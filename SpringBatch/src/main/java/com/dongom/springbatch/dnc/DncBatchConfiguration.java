package com.dongom.springbatch.dnc;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DncBatchConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job dncJob() {
		return jobBuilderFactory.get("dncJob")
				.start(dncStep1(null, null))
				.build();
	}

	@Bean
	@JobScope
	public Step dncStep1(@Value("#{jobParameters[requestDate]}") String requestDate, 
			@Value("#{jobParameters[brnCd]}") String brnCd) {
		log.info(">>>>> This is Step1");
		log.info(">>>>> requestDate = {}", requestDate);
		StepBuilder stepBuilder = stepBuilderFactory.get("dncStep");
		SimpleStepBuilder simpleStepBuilder = stepBuilder.<String, String>chunk(10);

		simpleStepBuilder.reader(dncReader(null, null));
		simpleStepBuilder.processor(dncProcessor());
		simpleStepBuilder.writer(dncwriter());
		return simpleStepBuilder.build();
	}

	@Bean
	@StepScope
	public FlatFileItemReader dncReader(@Value("#{jobParameters[requestDate]}") String requestDate, 
			@Value("#{jobParameters[brnCd]}") String brnCd) {
		FlatFileItemReaderBuilder reader = new FlatFileItemReaderBuilder();
		System.out.println(requestDate);
		System.out.println(brnCd);
		// DNC_YYYYYMMDD.dat
		String fileName = "DNC_" + requestDate + ".dat";
		reader.name("dncItemReader");
		reader.resource(new ClassPathResource(fileName));
		reader.lineTokenizer(new DelimitedLineTokenizer());
		reader.fieldSetMapper(new DncFieldSetMapper());
		return reader.build();
	}
	
	@Bean
	public ItemProcessor<String, String> dncProcessor() {
		return (ItemProcessor<String, String>) new DncMapper();
	}
	
	@Bean
	public ItemWriter dncwriter() {
		return new DncPrintItemWriter();
	}
}