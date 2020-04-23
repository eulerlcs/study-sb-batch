package com.github.eulerlcs.study.sb.batch.sample.s01JobAndStep;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class S01Application {
    public static void main(String[] args) {
        SpringApplication.run(S01Application.class, args);
    }
}
