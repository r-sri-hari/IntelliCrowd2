package com.example.Backend.service;

import com.example.Backend.ml.result.DensityResult;
import com.example.Backend.model.CrowdMetric;
import com.example.Backend.repository.CrowdMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrowdMetricService {

    @Autowired
    private CrowdMetricRepository crowdMetricRepository;

    public CrowdMetric saveMetric(String zoneName, DensityResult result) {
        CrowdMetric metric = new CrowdMetric();
        metric.setZoneName(zoneName);
        metric.setDensity(result.getDensity());
        metric.setEstimatedHeads(result.getEstimatedHeads());
        metric.setAlertLevel(result.getAlertLevel());
        metric.setEmergencyTriggered(result.isEmergencyTriggered());
        metric.setTimestamp(LocalDateTime.now());

        return crowdMetricRepository.save(metric);
    }

    public List<CrowdMetric> getAllMetrics() {
        return crowdMetricRepository.findAll();
    }
}