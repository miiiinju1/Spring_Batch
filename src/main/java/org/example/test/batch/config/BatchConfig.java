package org.example.test.batch.config;


import lombok.RequiredArgsConstructor;
import org.example.test.batch.batch.CustomProcessor;
import org.example.test.batch.batch.CustomReader;
import org.example.test.batch.batch.CustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    @Bean
    Job createJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("job", jobRepository)
                .flow(createStep(jobRepository, transactionManager))
                .end()
                .build();

    }
    @Bean
    Step createStep (JobRepository jobRepository, PlatformTransactionManager transactionManger) {
        return new StepBuilder("step", jobRepository)
                .<String, String>chunk(5, transactionManger)
                .allowStartIfComplete(true)
                .reader(new CustomReader())
                .processor(new CustomProcessor())
                .writer(new CustomWriter())
                .build();



    }
    public JobParameters jobParameters() {
        return new JobParametersBuilder()
                .addLocalDateTime("localDateTime", LocalDateTime.now())
                .toJobParameters();
    }

}
