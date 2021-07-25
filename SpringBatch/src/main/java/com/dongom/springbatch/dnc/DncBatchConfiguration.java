package com.dongom.springbatch.dnc;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.dongom.springbatch.person.Person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DncBatchConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job dncJob() {
		return jobBuilderFactory.get("dncJob")
				.start(dncStep1(null))
				.build();
	}

	@Bean
	@JobScope
	public Step dncStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
		log.info(">>>>> This is Step1");
		log.info(">>>>> requestDate = {}", requestDate);
		StepBuilder stepBuilder = stepBuilderFactory.get("dncStep");
		SimpleStepBuilder simpleStepBuilder = stepBuilder.<String, String>chunk(10);
		simpleStepBuilder.reader(dncReader(null));
		simpleStepBuilder.writer(dncWriter());
		return simpleStepBuilder.build();
	}

	@Bean
	@StepScope
	public FlatFileItemReader dncReader(@Value("#{jobParameters[requestDate]}") String requestDate) {
		String fileNm = "DNC_"+requestDate+".dat";
		FlatFileItemReaderBuilder reader = new FlatFileItemReaderBuilder();
		Resource resource = new ClassPathResource(fileNm);
		reader.name("dncItemReader");
		reader.resource(resource);
		reader.encoding("UTF-8");

		DefaultLineMapper<Dnc> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new DncFieldSetMapper());
		
		reader.lineMapper(lineMapper);

		return reader.build();
		
	}
	
	@Bean
	public DncPrintItemWriter dncWriter() {
		return new DncPrintItemWriter();
	}
}