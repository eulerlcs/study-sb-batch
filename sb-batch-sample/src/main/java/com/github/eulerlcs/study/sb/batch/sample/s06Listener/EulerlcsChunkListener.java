package com.github.eulerlcs.study.sb.batch.sample.s06Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EulerlcsChunkListener {
    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        log.warn("★★★ eulercls [{}.{}].[{}] @ [{}] is called.",
                context.getStepContext().getJobName(),
                context.getStepContext().getStepName(),
                "EulerlcsChunkListener.beforeChunk",
                Thread.currentThread().getName());
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        log.warn("★★★ eulercls [{}.{}].[{}] @ [{}] is called.",
                context.getStepContext().getJobName(),
                context.getStepContext().getStepName(),
                "EulerlcsChunkListener.afterChunk",
                Thread.currentThread().getName());
    }
}
