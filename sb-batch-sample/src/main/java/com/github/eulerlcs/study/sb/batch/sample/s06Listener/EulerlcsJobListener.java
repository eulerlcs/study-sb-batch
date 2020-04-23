package com.github.eulerlcs.study.sb.batch.sample.s06Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EulerlcsJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                jobExecution.getJobInstance().getJobName(),
                "EulerlcsJobListener.beforeJob",
                Thread.currentThread().getName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.warn("★★★ eulercls [{}].[{}] @ [{}] is called.",
                jobExecution.getJobInstance().getJobName(),
                "EulerlcsJobListener.afterJob",
                Thread.currentThread().getName());
    }
}
