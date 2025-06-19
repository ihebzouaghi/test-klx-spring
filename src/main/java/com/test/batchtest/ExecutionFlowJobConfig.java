package com.test.batchtest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

@Configuration
@EnableBatchProcessing
public class ExecutionFlowJobConfig {

    @Bean
    public Job executionFlowJob(JobRepository jobRepository,
                                Step loadStep,
                                Step validateStep,
                                JobExecutionDecider decider) {

        return new JobBuilder("executionFlowJob", jobRepository)
                .start(decider).on("MORNING").to(loadStep)
                .from(decider).on("EVENING").to(validateStep)
                .next(loadStep)
                .next(validateStep)
                .end()
                .build();
    }

    @Bean
    public Step loadStep(JobRepository jobRepository,
                         PlatformTransactionManager txManager) {

        return new StepBuilder("loadStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Executing Load Step");
                    return RepeatStatus.FINISHED;
                }, txManager)
                .build();
    }

    @Bean
    public Step validateStep(JobRepository jobRepository,
                             PlatformTransactionManager txManager) {

        return new StepBuilder("validateStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Executing Validate Step");
                    return RepeatStatus.FINISHED;
                }, txManager)
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new MorningEveningDecider();
    }
}
