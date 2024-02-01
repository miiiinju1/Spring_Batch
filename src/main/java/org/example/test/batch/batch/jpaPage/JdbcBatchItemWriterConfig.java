package org.example.test.batch.batch.jpaPage;

import lombok.RequiredArgsConstructor;
import org.example.test.domain.Dept2;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcBatchItemWriterConfig {

    private final DataSource dataSource;

    @Bean
    JdbcBatchItemWriter<Dept2> jdbcBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<Dept2>()
                .dataSource(dataSource)
                .sql("UPDATE dept2 SET d_name = :dName, loc = :loc WHERE dept_no = :deptNo")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();

    }
}
