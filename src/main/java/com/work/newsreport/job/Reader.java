package com.work.newsreport.job;

import com.work.newsreport.entity.NewsReport;
import com.work.newsreport.repository.NewsReportRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class Reader {

    final NewsReportRepository newsReportRepository;
    public Reader(NewsReportRepository newsReportRepository) {
        this.newsReportRepository = newsReportRepository;
    }

    @Bean(name = "readerNewsReportCSVFile")
    public ItemReader<NewsReport> readerNewsReportCSVFile(){
        return new FlatFileItemReaderBuilder<NewsReport>()
                .name("readerNewsReportCSVFile")
                .resource(new FileSystemResource("dataset/FakeNewsNet.csv"))
                .linesToSkip(1)
                .delimited()
                .names("title","news_url","source_domain","tweet_num","real")
                .targetType(NewsReport.class)
                .build();
    }

    @Bean(name = "readerNewsReportByRealStatus")
    public RepositoryItemReader<NewsReport> readerNewsReportByRealStatus(){
        RepositoryItemReader<NewsReport> reader = new RepositoryItemReader<>();
        reader.setRepository(newsReportRepository);
        reader.setMethodName("findByReal");
        List<Object> queryMethodArguments = new ArrayList<>();
        queryMethodArguments.add("1");
        reader.setArguments(queryMethodArguments);
        Map<String, Sort.Direction> sorts = new LinkedHashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean(name = "readerNewsReportByFakeStatus")
    public RepositoryItemReader<NewsReport> readerNewsReportByFakeStatus(){
        RepositoryItemReader<NewsReport> reader = new RepositoryItemReader<>();
        reader.setRepository(newsReportRepository);
        reader.setMethodName("findByReal");
        List<Object> queryMethodArguments = new ArrayList<>();
        queryMethodArguments.add("0");
        reader.setArguments(queryMethodArguments);
        Map<String, Sort.Direction> sorts = new LinkedHashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

}
