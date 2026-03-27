package com.example.Backend.ml.inference;

import com.example.Backend.ml.result.DensityResult;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class TestMain {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat dummyFrame = Mat.zeros(480, 640, 0);

        CrowdRiskService crowdRiskService = new CrowdRiskService();
        DensityResult result = crowdRiskService.analyzeFrame(dummyFrame);

        System.out.println("Density: " + result.getDensity());
        System.out.println("Estimated Heads: " + result.getEstimatedHeads());
        System.out.println("Alert Level: " + result.getAlertLevel());
        System.out.println("Emergency: " + result.isEmergencyTriggered());
    }
}