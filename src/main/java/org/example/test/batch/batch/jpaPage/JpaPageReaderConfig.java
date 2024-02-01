package org.example.test.batch.batch.jpaPage;

import jakarta.persistence.*;

import lombok.RequiredArgsConstructor;
import org.example.test.domain.Dept;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaPageReaderConfig {

    private final EntityManagerFactory entityManagerFactory;
    private final int chunkSize = 10;
    @Bean
    public JpaPagingItemReader<Dept> jpaPageReader() {
        return new JpaPagingItemReaderBuilder<Dept>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("select d from Dept d order by deptNo asc")
                .build();
    }

}
