package org.example.test.batch.batch.jpaPage;

import org.example.test.domain.Dept;
import org.example.test.domain.Dept2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemProcessorConfig {
    @Bean
    ItemProcessor<Dept, Dept2> jpaPageProcessor() {
        return dept -> new Dept2(dept.getDeptNo(), "NEW3_"+dept.getDName(), dept.getLoc());
    }
}
