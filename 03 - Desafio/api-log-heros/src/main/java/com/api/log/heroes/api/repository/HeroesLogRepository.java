package com.api.log.heroes.api.repository;

import com.api.log.heroes.api.model.entities.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroesLogRepository extends JpaRepository<LogDetail,Long> {
}
