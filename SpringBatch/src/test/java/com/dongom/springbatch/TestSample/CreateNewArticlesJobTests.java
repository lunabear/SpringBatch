package com.dongom.springbatch.TestSample;

import java.util.Date;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateNewArticlesJobTests {
	
	@Autowired
	private Job createNewArticlesJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Test
	public void test() throws Exception {
		this.jobLauncher.run(this.createNewArticlesJob, new JobParametersBuilder()
				.addDate("key", new Date())
				.toJobParameters());
	}
}
