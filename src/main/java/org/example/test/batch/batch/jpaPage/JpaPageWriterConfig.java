package org.example.test.batch.batch.jpaPage;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.example.test.domain.Dept2;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaPageWriterConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    JpaItemWriter<Dept2> jpaPageWriter() {
        JpaItemWriter<Dept2> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }


}
