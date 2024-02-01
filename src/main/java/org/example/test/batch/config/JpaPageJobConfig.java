package org.example.test.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.domain.Dept;
import org.example.test.domain.Dept2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaPageJobConfig {

    private final JpaItemWriter<Dept2> jdbcBatchItemWriter;
    private final JpaPagingItemReader<Dept> jpaPagingItemReader;
    private final ItemProcessor<Dept, Dept2> itemProcessor;
    private final int chunkSize = 10;

    @Bean
    Job createJpaPagingJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("jpaPagingJob", jobRepository)
                .flow(createJpaPagingStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    Step createJpaPagingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("jpaPagingStep", jobRepository)
                .<Dept, Dept2>chunk(chunkSize, transactionManager)
                .allowStartIfComplete(true)
                .reader(jpaPagingItemReader)
                .processor(itemProcessor)
                .writer(jdbcBatchItemWriter)
                .build();
    }
}
