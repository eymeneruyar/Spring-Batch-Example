package com.work.newsreport.repository;

import com.work.newsreport.entity.FakeNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakeNewsRepository extends JpaRepository<FakeNews,Long> {
}
