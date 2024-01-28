package org.example.test.batch.batch;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String data) throws Exception {
        System.out.println("Processing data: " + data);
//        if(data.equals("2 SpringBoot")) {
//            throw new RuntimeException("Exception experiment");
//        }
        data = data.toUpperCase();
        return data;
    }
}
