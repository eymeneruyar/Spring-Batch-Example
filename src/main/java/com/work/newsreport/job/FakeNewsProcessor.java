package com.work.newsreport.job;

import com.work.newsreport.entity.FakeNews;
import com.work.newsreport.entity.NewsReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class FakeNewsProcessor implements ItemProcessor<NewsReport, FakeNews> {

    @Override
    public FakeNews process(NewsReport newsReport) throws Exception {
        log.info("Processing real news.....{}", newsReport);
        FakeNews fakeNews = new FakeNews();
        fakeNews.setUrl(newsReport.getNews_url());
        fakeNews.setTitle(newsReport.getTitle());
        fakeNews.setTweetNum(Integer.parseInt(newsReport.getTweet_num()));
        fakeNews.setSourceDomain(newsReport.getSource_domain());
        return fakeNews;
    }

}
