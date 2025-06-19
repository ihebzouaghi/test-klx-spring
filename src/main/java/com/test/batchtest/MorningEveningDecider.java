package com.test.batchtest;

import java.time.LocalTime;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class MorningEveningDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) {
            System.out.println("It's morning (" + now + "), proceeding with morning flow.");
            return new FlowExecutionStatus("MORNING");
        } else {
            System.out.println("It's evening (" + now + "), proceeding with evening flow.");
            return new FlowExecutionStatus("EVENING");
        }
    }
    
}
