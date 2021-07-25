package com.dongom.springbatch.fasecampusSample.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class FastCampusSampleJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job fastCampusSampleJob() {
		return jobBuilderFactory.get("fastCampusSampleJob2")
				.start(fastCampusSampleStep())
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Step fastCampusSampleStep() {
		return stepBuilderFactory.get("fastCampusSampleStep")
				.tasklet((contribution, chunkContext) -> {
					log.info("step!");
					return RepeatStatus.FINISHED;
				})
				.build();
	}

}
