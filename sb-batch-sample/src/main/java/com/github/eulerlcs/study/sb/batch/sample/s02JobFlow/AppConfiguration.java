package com.github.eulerlcs.study.sb.batch.sample.s02JobFlow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job s02FirstJob() {
        return this.jobBuilderFactory.get("s02FirstJob")
                .start(s02FirstFlow())
                .next(s02ThirdStep())
                .end()
                .build();
    }

    @Bean
    public Flow s02FirstFlow() {
        return new FlowBuilder<Flow>("s02FirstFlow")
                .start(s02FirstStep())
                .next(s02SecondStep())
                .build();
    }

    @Bean
    public Step s02FirstStep() {
        return this.stepBuilderFactory.get("s02FirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s02SecondStep() {
        return this.stepBuilderFactory.get("s02SecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s02ThirdStep() {
        return this.stepBuilderFactory.get("s02ThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }
}
