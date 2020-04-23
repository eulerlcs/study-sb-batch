package com.github.eulerlcs.study.sb.batch.sample.s03Split;

import com.github.eulerlcs.study.sb.batch.sample.common.CommonConfiguration;
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
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@Slf4j
@Import(CommonConfiguration.class)
public class AppConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job s03FirstJob(Flow commonFirstFlow) {
        return this.jobBuilderFactory.get("s03FirstJob")
                .start(s03FirstFlow())
                .split(new SimpleAsyncTaskExecutor()).add(
                        s03SecondFlow(),
                        commonFirstFlow
                )
                .next(s03FourthStep())
                .end()
                .build();
    }


    @Bean
    public Flow s03FirstFlow() {
        return new FlowBuilder<Flow>("s03FirstFlow")
                .start(s03FirstStep())
                .next(s03SecondStep())
                .build();
    }

    @Bean
    public Flow s03SecondFlow() {
        return new FlowBuilder<Flow>("s03SecondFlow")
                .start(s03ThirdStep())
                .build();
    }

    @Bean
    public Step s03FirstStep() {
        return this.stepBuilderFactory.get("s03FirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s03SecondStep() {
        return this.stepBuilderFactory.get("s03SecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s03ThirdStep() {
        return this.stepBuilderFactory.get("s03ThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s03FourthStep() {
        return this.stepBuilderFactory.get("s03FourthStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }
}
