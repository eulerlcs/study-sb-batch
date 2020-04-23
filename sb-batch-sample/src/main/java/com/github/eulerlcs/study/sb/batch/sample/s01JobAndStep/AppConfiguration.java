package com.github.eulerlcs.study.sb.batch.sample.s01JobAndStep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
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

    @Bean
    public Job s01FirstJob() {
        return this.jobBuilderFactory.get("s01FirstJob")
                .start(s01FirstStep())
                .next(s01SecondStep())
                .build();
    }

    //@Bean
    public Job s01SecondJob() {
        return this.jobBuilderFactory.get("s01SecondJob")
                .start(s01FirstStep()).on("COMPLETED").to(s01ThirdStep())
                .from(s01ThirdStep()).on(ExitStatus.COMPLETED.getExitCode()).to(s01SecondStep())
                .from(s01SecondStep()).end()
                .build();
    }


    @Bean
    public Step s01FirstStep() {
        return this.stepBuilderFactory.get("s01FirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s01SecondStep() {
        return this.stepBuilderFactory.get("s01SecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s01ThirdStep() {
        return this.stepBuilderFactory.get("s01ThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s01FourthStep() {
        return this.stepBuilderFactory.get("s01FourthStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

}
