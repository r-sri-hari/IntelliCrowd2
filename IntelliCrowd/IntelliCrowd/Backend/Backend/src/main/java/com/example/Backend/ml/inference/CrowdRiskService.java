package com.example.Backend.ml.inference;

import com.example.Backend.ml.result.DensityResult;
import org.opencv.core.Mat;

public class CrowdRiskService {

    private final AHDSCalculator ahdsCalculator = new AHDSCalculator();
    private int redFrameCount = 0;

    public DensityResult analyzeFrame(Mat frame) {
        DensityResult result = ahdsCalculator.calculateDensity(frame);

        if ("RED".equals(result.getAlertLevel())) {
            redFrameCount++;
        } else {
            redFrameCount = 0;
        }

        if (redFrameCount >= 3) {
            result.setEmergencyTriggered(true);
        } else {
            result.setEmergencyTriggered(false);
        }

        return result;
    }
}