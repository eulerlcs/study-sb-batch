package com.github.eulerlcs.study.sb.batch.sample.s05nested;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
//@Import(CommonConfiguration.class)
public class AppConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job s05EodJob() {
        return this.jobBuilderFactory.get("s05EodJob")
                .start(s05FirstEodStep())
                .next(s05SecondEodStep())
                .next(s05FourthStep())
                .build();
    }


    //    @Bean
    public Step s05FirstEodStep() {
        return new JobStepBuilder(new StepBuilder("s05FirstEodStep"))
                .job(s05FirstJob())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }

    //    @Bean
    public Step s05SecondEodStep() {
        return new JobStepBuilder(new StepBuilder("s05SecondEodStep"))
                .job(s05SecondJob())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job s05FirstJob() {
        return this.jobBuilderFactory.get("s05FirstJob")
                .start(s05FirstFlow())
                .end()
                .build();
    }

    @Bean
    public Job s05SecondJob() {
        return this.jobBuilderFactory.get("s05SecondJob")
                .start(s05SecondFlow())
                .end()
                .build();
    }


    @Bean
    public Flow s05FirstFlow() {
        return new FlowBuilder<Flow>("s05FirstFlow")
                .start(s05FirstStep())
                .next(s05SecondStep())
                .build();
    }

    @Bean
    public Flow s05SecondFlow() {
        return new FlowBuilder<Flow>("s05SecondFlow")
                .start(s05ThirdStep())
                .build();
    }

    @Bean
    public Step s05FirstStep() {
        return this.stepBuilderFactory.get("s05FirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s05SecondStep() {
        return this.stepBuilderFactory.get("s05SecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s05ThirdStep() {
        return this.stepBuilderFactory.get("s05ThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    //@Bean
    public Step s05FourthStep() {
        return this.stepBuilderFactory.get("s05FourthStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }
}
