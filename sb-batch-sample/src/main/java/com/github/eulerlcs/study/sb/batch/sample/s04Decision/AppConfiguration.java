package com.github.eulerlcs.study.sb.batch.sample.s04Decision;

import com.github.eulerlcs.study.sb.batch.sample.common.CommonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Slf4j
@Import(CommonConfiguration.class)
public class AppConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job s04FirstJob(OddEvenDecider oddEvenDecider) {
        return this.jobBuilderFactory.get("s04FirstJob")
                .start(s04FirstStep())
                .next(oddEvenDecider)
                .from(s04ThirdStep()).on("*").to(oddEvenDecider)
                .from(s04FourthStep()).on("*").to(oddEvenDecider)
                .from(oddEvenDecider).on("ODD").to(s04ThirdStep())
                .from(oddEvenDecider).on("EVEN").to(s04FourthStep())
                .from(oddEvenDecider).on(FlowExecutionStatus.STOPPED.getName()).to(s04FourthStep())
                .end()
                .build();
    }

    @Bean
    public Flow s04FirstFlow() {
        return new FlowBuilder<Flow>("s04FirstFlow")
                .start(s04FirstStep())
                .next(s04SecondStep())
                .build();
    }

    @Bean
    public Flow s04SecondFlow() {
        return new FlowBuilder<Flow>("s04SecondFlow")
                .start(s04ThirdStep())
                .build();
    }


    @Bean
    public Step s04FirstStep() {
        return this.stepBuilderFactory.get("s04FirstStep").tasklet((contribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s04SecondStep() {
        return this.stepBuilderFactory.get("s04SecondStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s04ThirdStep() {
        return this.stepBuilderFactory.get("s04ThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step s04FourthStep() {
        return this.stepBuilderFactory.get("s04FourthStep").tasklet((stepContribution, chunkContext) -> {
            log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                    chunkContext.getStepContext().getJobName(),
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        }).build();
    }
}
