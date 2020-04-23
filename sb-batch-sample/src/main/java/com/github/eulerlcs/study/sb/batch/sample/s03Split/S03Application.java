package com.github.eulerlcs.study.sb.batch.sample.s03Split;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class S03Application {
    public static void main(String[] args) {
        SpringApplication.run(S03Application.class, args);
    }
}
