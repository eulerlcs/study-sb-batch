package com.github.eulerlcs.study.sb.batch.hello.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@Slf4j
public class SbBatchHelloConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return this.jobBuilderFactory.get("helloJob").start(helloStep()).build();
    }

    @Bean
    public Step helloStep() {
        return this.stepBuilderFactory.get("helloStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("hello, eulerlcs. welcome the world of the spring boot batch!");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
