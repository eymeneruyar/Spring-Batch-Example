package com.work.newsreport.job;

import com.work.newsreport.entity.FakeNews;
import com.work.newsreport.entity.NewsReport;
import com.work.newsreport.entity.RealNews;
import com.work.newsreport.repository.FakeNewsRepository;
import com.work.newsreport.repository.NewsReportRepository;
import com.work.newsreport.repository.RealNewsRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Writer {

    final NewsReportRepository newsReportRepository;
    final RealNewsRepository realNewsRepository;
    final FakeNewsRepository fakeNewsRepository;

    public Writer(NewsReportRepository newsReportRepository, RealNewsRepository realNewsRepository, FakeNewsRepository fakeNewsRepository) {
        this.newsReportRepository = newsReportRepository;
        this.realNewsRepository = realNewsRepository;
        this.fakeNewsRepository = fakeNewsRepository;
    }

    @Bean(name = "writerNewsReportTable")
    public RepositoryItemWriter<NewsReport> writerNewsReportTable(){
        RepositoryItemWriter<NewsReport> writer = new RepositoryItemWriter<>();
        writer.setRepository(newsReportRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean(name = "writerRealNewsReportTable")
    public RepositoryItemWriter<RealNews> writerRealNewsReportTable(){
        RepositoryItemWriter<RealNews> writer = new RepositoryItemWriter<>();
        writer.setRepository(realNewsRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean(name = "writerFakeNewsReportTable")
    public RepositoryItemWriter<FakeNews> writerFakeNewsReportTable(){
        RepositoryItemWriter<FakeNews> writer = new RepositoryItemWriter<>();
        writer.setRepository(fakeNewsRepository);
        writer.setMethodName("save");
        return writer;
    }

}
