package com.test.batchtest;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class Exercice2JobConfig {

    @Bean
    public Job exercise2Job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        Step step = new StepBuilder("step1", jobRepository)
            .<String, String>chunk(1, transactionManager)
            .reader(new ListItemReader<>(List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo")))
            .processor((ItemProcessor<String, String>) item -> {
                System.out.println("🔍 Processing: " + item);
                Thread.sleep(200); // simulate delay
                return "Processed-" + item;
            })
            .writer(items -> items.forEach(item -> System.out.println("📝 Writing: " + item)))
            .build();

        return new JobBuilder("exercise2Job", jobRepository)
            .start(step)
            .build();
    }
}