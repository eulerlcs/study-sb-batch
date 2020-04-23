package com.github.eulerlcs.study.sb.batch.sample.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CommonConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step commonFirstStep() {
        return this.stepBuilderFactory.get("commonFirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step commonSecondStep() {
        return this.stepBuilderFactory.get("commonSecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step commonThirdStep() {
        return this.stepBuilderFactory.get("commonThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step commonFourthStep() {
        return this.stepBuilderFactory.get("commonFourthStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Flow commonFirstFlow(Step commonFirstStep, Step commonSecondStep) {
        return new FlowBuilder<Flow>("commonFirstFlow")
                .start(commonFirstStep)
                .next(commonSecondStep)
                .build();
    }
}
