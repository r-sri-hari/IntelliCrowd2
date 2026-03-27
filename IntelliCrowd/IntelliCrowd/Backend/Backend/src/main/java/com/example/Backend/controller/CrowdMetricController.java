package com.example.Backend.controller;

import com.example.Backend.ml.inference.CrowdRiskService;
import com.example.Backend.ml.result.DensityResult;
import com.example.Backend.model.CrowdMetric;
import com.example.Backend.service.CrowdMetricService;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crowd")
@CrossOrigin(origins = "*")
public class CrowdMetricController {

    @Autowired
    private CrowdMetricService crowdMetricService;

    private final CrowdRiskService crowdRiskService = new CrowdRiskService();

    @GetMapping("/test-save")
    public CrowdMetric testSaveMetric() {
        DensityResult result = new DensityResult(0.0014, 12, "YELLOW", false);
        return crowdMetricService.saveMetric("Zone A", result);
    }

    @GetMapping("/analyze-dummy")
    public String analyzeDummyFrame() {
        return "OpenCV dummy analysis is temporarily disables";
    }

    @GetMapping("/all")
    public List<CrowdMetric> getAllMetrics() {
        return crowdMetricService.getAllMetrics();
    }
}