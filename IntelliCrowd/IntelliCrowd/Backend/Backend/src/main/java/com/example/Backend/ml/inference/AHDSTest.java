package com.example.Backend.ml.inference;

import com.example.Backend.ml.result.DensityResult;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class AHDSTest {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture cap = new VideoCapture(0);

        if (!cap.isOpened()) {
            System.out.println("Camera not opened");
            return;
        }

        CrowdRiskService crowdRiskService = new CrowdRiskService();
        Mat frame = new Mat();

        for (int i = 0; i < 10; i++) {
            if (cap.read(frame)) {
                DensityResult result = crowdRiskService.analyzeFrame(frame);

                System.out.println("Frame " + (i + 1));
                System.out.println("Density: " + result.getDensity());
                System.out.println("Estimated Heads: " + result.getEstimatedHeads());
                System.out.println("Alert Level: " + result.getAlertLevel());
                System.out.println("Emergency: " + result.isEmergencyTriggered());
                System.out.println("--------------------------------");
            }
        }

        cap.release();
    }
}