package com.github.eulerlcs.study.sb.batch.sample.s06Listener;

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

    @Autowired
    private EulerlcsJobListener jobListener;

    @Autowired
    private EulerlcsChunkListener chunkListener;

    @Bean
    public Job s06FirstJob() {
        return this.jobBuilderFactory.get("s06FirstJob")
                .listener(jobListener)
                .start(s06FirstFlow())
                .next(s06ThirdStep())
                .end()
                .build();
    }

    @Bean
    public Flow s06FirstFlow() {
        return new FlowBuilder<Flow>("s06FirstFlow")
                .start(s06FirstStep())
                .next(s06SecondStep())
                .build();
    }

    @Bean
    public Step s06FirstStep() {
        return this.stepBuilderFactory.get("s06FirstStep").tasklet(
                (contribution, chunkContext) -> {
                    log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                            chunkContext.getStepContext().getJobName(),
                            chunkContext.getStepContext().getStepName(),
                            Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }
        ).listener(chunkListener).build();
    }

    @Bean
    public Step s06SecondStep() {
        return this.stepBuilderFactory.get("s06SecondStep").tasklet(
                (stepContribution, chunkContext) -> {
                    log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                            chunkContext.getStepContext().getJobName(),
                            chunkContext.getStepContext().getStepName(),
                            Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }
        ).build();
    }

    @Bean
    public Step s06ThirdStep() {
        return this.stepBuilderFactory.get("s06ThirdStep").tasklet(
                (stepContribution, chunkContext) -> {
                    log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                            chunkContext.getStepContext().getJobName(),
                            chunkContext.getStepContext().getStepName(),
                            Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }
        ).build();
    }
}
