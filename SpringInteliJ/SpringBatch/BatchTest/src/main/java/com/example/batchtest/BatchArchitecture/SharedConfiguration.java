package com.example.batchtest.BatchArchitecture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SharedConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SharedConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job shareJob() {
        return jobBuilderFactory.get("shareJob")
                .incrementer(new RunIdIncrementer())
                .start(this.shareStep1())//excutecontext에 임의의 값 넣을 거
                .next(this.shareStep2())   //이름과 런 아이디 파라미터 꺼내서 로그 찍어봄
                .build();
    }

    @Bean
    public Step shareStep2() {
        return stepBuilderFactory.get("shareStep2")
                .tasklet((contribution, chunkContext) -> {
                    // step ExecutionContext.get
                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();

                    // job ExecutionContext.get
                    JobExecution jobExecution = stepExecution.getJobExecution();
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
    //job Excute를 살행해서 stepExecutionContext에 넣고
 //              ExecutionContext을 jobExecution해서  jobExecutionContext에 넣는다.
                    // log
                    log.info("jobValue : {}, stepValue : {}",
                            jobExecutionContext.getString("job", "emptyJob"),
                            stepExecutionContext.getString("step", "emptyStep"));

                    return RepeatStatus.FINISHED;

                }).build();
    }

    @Bean
    public Step shareStep1() {
        return stepBuilderFactory.get("shareStep1")
                .tasklet((contribution, chunkContext) -> {
                    // step ExecutionContext.put
                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();
                    stepExecutionContext.putString("step", "step execution context");   //step에 step execution context 를 저장함

                    // job ExecutionContext.put
                    JobExecution jobExecution = stepExecution.getJobExecution();
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
                    jobExecutionContext.putString("job", "job execution context");

                    // log
                    JobInstance jobInstance = jobExecution.getJobInstance();
                    JobParameters jobParameters = jobExecution.getJobParameters();
//                    JobParameters jobParameters1 = stepExecution.getJobParameters();

                    log.info("jobName : {}, stepName : {}, run.id : {}",
                            jobInstance.getJobName(),
                            stepExecution.getStepName(),
                            jobParameters.getLong("run.id"));

                    return RepeatStatus.FINISHED;
                }).build();
    }
}