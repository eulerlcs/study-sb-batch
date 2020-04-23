package com.github.eulerlcs.study.sb.batch.sample.s00;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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

    // @Bean
    public Job s00FirstJob() {
        return this.jobBuilderFactory.get("s00FirstJob")
                .start(s00FirstStep())
                .next(s00SecondStep())
                .build();
    }

    @Bean
    public Job s00SecondJob() {
        return this.jobBuilderFactory.get("s00SecondJob")
                .start(s00FirstStep()).on("COMPLETED").to(s00ThirdStep())
                .from(s00ThirdStep()).on("COMPLETED").to(s00SecondStep())
                .from(s00SecondStep()).end()
                .build();
    }


    @Bean
    public Step s00FirstStep() {
        return this.stepBuilderFactory.get("s00FirstStep").tasklet(
                (contribution, chunkContext) -> {
                    log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                            chunkContext.getStepContext().getJobName(),
                            chunkContext.getStepContext().getStepName(),
                            Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }
        ).build();
    }

    @Bean
    public Step s00SecondStep() {
        return this.stepBuilderFactory.get("s00SecondStep").tasklet(
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
    public Step s00ThirdStep() {
        return this.stepBuilderFactory.get("s00ThirdStep").tasklet(
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
    public Step s00FourthStep() {
        return this.stepBuilderFactory.get("s00FourthStep").tasklet(
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
