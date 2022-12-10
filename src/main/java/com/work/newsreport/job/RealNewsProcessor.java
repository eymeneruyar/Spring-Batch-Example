package com.work.newsreport.job;

import com.work.newsreport.entity.NewsReport;
import com.work.newsreport.entity.RealNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RealNewsProcessor implements ItemProcessor<NewsReport, RealNews>{

    @Override
    public RealNews process(NewsReport newsReport) throws Exception {
        log.info("Processing real news.....{}", newsReport);
        RealNews realNews = new RealNews();
        realNews.setUrl(newsReport.getNews_url());
        realNews.setTitle(newsReport.getTitle());
        realNews.setTweetNum(Integer.parseInt(newsReport.getTweet_num()));
        realNews.setSourceDomain(newsReport.getSource_domain());
        return realNews;
    }

}
