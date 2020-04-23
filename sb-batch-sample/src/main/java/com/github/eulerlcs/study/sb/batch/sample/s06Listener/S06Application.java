package com.github.eulerlcs.study.sb.batch.sample.s06Listener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class S06Application {
    public static void main(String[] args) {
        SpringApplication.run(S06Application.class, args);
    }
}
