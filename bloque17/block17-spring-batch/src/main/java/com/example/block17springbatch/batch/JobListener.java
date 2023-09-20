package com.example.block17springbatch.batch;

import com.example.block17springbatch.error.ErrorCounterComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static ch.qos.logback.core.CoreConstants.MAX_ERROR_COUNT;

@Component
@EnableBatchProcessing
public class JobListener implements JobExecutionListener {

    @Autowired
    ErrorCounterComponent errorCounterComponent;

    private static final int MAX_ERROR_COUNT = 100;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            System.out.println("Job iniciado.");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (errorCounterComponent.getErrorCounter().getCount() >= MAX_ERROR_COUNT) {
            jobExecution.setStatus(BatchStatus.FAILED);
            System.out.println("Hay más de 100 errores, deteniendo batch.");
        } else if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Job acabado.");
        }
    }
}
