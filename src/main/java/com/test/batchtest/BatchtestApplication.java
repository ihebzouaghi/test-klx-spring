package com.test.batchtest;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchtestApplication implements CommandLineRunner {

    @Autowired
    Job exercise2Job;

    @Autowired
    JobLauncher jobLauncher;

    public static void main(String[] args) {
        SpringApplication.run(BatchtestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
        jobLauncher.run(exercise2Job, params);
    }
}