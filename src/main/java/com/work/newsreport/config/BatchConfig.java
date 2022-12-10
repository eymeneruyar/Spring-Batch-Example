package com.work.newsreport.config;

import com.work.newsreport.entity.FakeNews;
import com.work.newsreport.entity.NewsReport;
import com.work.newsreport.entity.RealNews;
import com.work.newsreport.job.FakeNewsProcessor;
import com.work.newsreport.job.RealNewsProcessor;
import com.work.newsreport.listener.JobCompletionNotificationListener;
import com.work.newsreport.listener.StepCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step stepNewsReport(@Qualifier("readerNewsReportCSVFile") ItemReader<NewsReport> itemReader, @Qualifier("writerNewsReportTable") ItemWriter<NewsReport> itemWriter, StepCompletionNotificationListener listener){
        return stepBuilderFactory
                .get("stepNewsReport")
                .listener(listener)
                .<NewsReport, NewsReport>chunk(50)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step stepRealNewsReport(@Qualifier("readerNewsReportByRealStatus") ItemReader<NewsReport> itemReader, @Qualifier("writerRealNewsReportTable") ItemWriter<RealNews> itemWriter, StepCompletionNotificationListener listener){
        return stepBuilderFactory
                .get("stepRealNewsReport")
                .listener(listener)
                .<NewsReport, RealNews>chunk(50)
                .reader(itemReader)
                .processor(realNewsProcessor())
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step stepFakeNewsReport(@Qualifier("readerNewsReportByFakeStatus") ItemReader<NewsReport> itemReader, @Qualifier("writerFakeNewsReportTable") ItemWriter<FakeNews> itemWriter, StepCompletionNotificationListener listener){
        return stepBuilderFactory
                .get("stepFakeNewsReport")
                .listener(listener)
                .<NewsReport, FakeNews>chunk(50)
                .reader(itemReader)
                .processor(fakeNewsProcessor())
                .writer(itemWriter)
                .build();
    }

    @Bean
    public RealNewsProcessor realNewsProcessor(){
        return new RealNewsProcessor();
    }

    @Bean
    public FakeNewsProcessor fakeNewsProcessor(){
        return new FakeNewsProcessor();
    }

    @Bean
    public Job job(JobCompletionNotificationListener listener,Step stepNewsReport,Step stepRealNewsReport,Step stepFakeNewsReport){
        return jobBuilderFactory
                .get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(stepNewsReport)
                .next(stepRealNewsReport)
                .next(stepFakeNewsReport)
                .build();
    }

}
