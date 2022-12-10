package com.work.newsreport.repository;

import com.work.newsreport.entity.RealNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealNewsRepository extends JpaRepository<RealNews,Long> {
}
