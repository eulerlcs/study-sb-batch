package com.github.eulerlcs.study.sb.batch.sample.s07Parameter;

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
    public Job s07FirstJob() {
        return this.jobBuilderFactory.get("s07FirstJob")
                .start(s07FirstStep())
                .next(s07SecondStep())
                .build();
    }

    @Bean
    public Job s07SecondJob() {
        return this.jobBuilderFactory.get("s07SecondJob")
                .start(s07FirstStep()).on("COMPLETED").to(s07ThirdStep())
                .from(s07ThirdStep()).on("COMPLETED").to(s07SecondStep())
                .from(s07SecondStep()).end()
                .build();
    }


    @Bean
    public Step s07FirstStep() {
        return this.stepBuilderFactory.get("s07FirstStep").tasklet(
                (contribution, chunkContext) -> {
                    String info = (String) chunkContext.getStepContext().getJobParameters().getOrDefault("info",
                            "not set");
                    log.warn("★★★ eulercls the command line parameter info=[{}] @ [{}.{}] is sent.",
                            info,
                            chunkContext.getStepContext().getJobName(),
                            chunkContext.getStepContext().getStepName()
                    );

                    return RepeatStatus.FINISHED;
                }
        ).build();
    }

    @Bean
    public Step s07SecondStep() {
        return this.stepBuilderFactory.get("s07SecondStep").tasklet(
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
    public Step s07ThirdStep() {
        return this.stepBuilderFactory.get("s07ThirdStep").tasklet(
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
