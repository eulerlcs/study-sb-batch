package com.github.eulerlcs.study.sb.batch.sample.s04Decision;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class OddEvenDecider implements JobExecutionDecider {
    private int count;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;

        if (count > 2) {
            return FlowExecutionStatus.STOPPED;
        }

        return count % 2 == 0 ? new FlowExecutionStatus("EVEN") : new FlowExecutionStatus("ODD");
    }
}
