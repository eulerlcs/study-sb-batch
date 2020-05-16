package com.github.eulerlcs.study.sb.batch.sample.s11ItemReader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

@Configuration
@Slf4j
public class AppConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job s11FirstJob() {
        return this.jobBuilderFactory.get("s11FirstJob")
                .start(s11FirstStep())
                .build();
    }


    @Bean
    public Step s11FirstStep() {
        final Iterator<String> params = Arrays.asList("param_01", "param_02", "param_03").iterator();

        return this.stepBuilderFactory.get("s11FirstStep")
                .<String, String>chunk(1)
                .reader(() -> params.hasNext() ? params.next() : null)
                .processor((Function<? super String, ? extends String>) (item) -> item + "_processed")
                .writer(items -> items.forEach(item ->
                        log.warn("★★★ eulercls [{}] @ [{}] is wrote.",
                                item,
                                Thread.currentThread().getName())))
                .build();
    }
}
