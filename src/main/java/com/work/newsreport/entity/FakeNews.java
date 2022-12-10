package com.work.newsreport.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FAKE_NEWS")
public class FakeNews {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text", name = "TITLE")
    private String title;

    @Column(columnDefinition = "text", name = "NEWS_URL")
    private String url;

    @Column(name = "SOURCE_DOMAIN")
    private String sourceDomain;

    @Column(name = "TWEET_NUM")
    private int tweetNum;

}
