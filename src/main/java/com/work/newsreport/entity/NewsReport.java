package com.work.newsreport.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "NEWS_REPORT")
public class NewsReport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text", name = "TITLE")
    private String title;

    @Column(columnDefinition = "text", name = "NEWS_URL")
    private String news_url;

    @Column(name = "SOURCE_DOMAIN")
    private String source_domain;

    @Column(name = "TWEET_NUM", length = 16)
    private String tweet_num;

    @Column(name = "REAL",length = 1)
    private String real;

}
