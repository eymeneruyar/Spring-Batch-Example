package com.work.newsreport.repository;

import com.work.newsreport.entity.NewsReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsReportRepository extends JpaRepository<NewsReport,Long> {

    Page<NewsReport> findByReal(String real, Pageable pageable);

}
