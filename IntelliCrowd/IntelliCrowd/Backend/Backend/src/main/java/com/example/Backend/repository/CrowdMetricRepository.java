package com.example.Backend.repository;

import com.example.Backend.model.CrowdMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrowdMetricRepository extends JpaRepository<CrowdMetric, Long> {
}