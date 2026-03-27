package com.example.Backend.ml.inference;

import com.example.Backend.ml.result.DensityResult;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class AHDSCalculator {

    private final int windowSize = 64;
    private final int stride = 32;
    private final HeadPredictor headPredictor = new HeadPredictor();

    public DensityResult calculateDensity(Mat frame) {
        double densityScore = 0.0;
        int estimatedHeads = 0;

        int frameWidth = frame.cols();
        int frameHeight = frame.rows();

        int centerX = frameWidth / 2;
        int centerY = frameHeight / 2;

        double maxDistance = Math.sqrt(centerX * centerX + centerY * centerY);

        for (int y = 0; y <= frameHeight - windowSize; y += stride) {
            for (int x = 0; x <= frameWidth - windowSize; x += stride) {

                Rect rect = new Rect(x, y, windowSize, windowSize);
                Mat patch = new Mat(frame, rect);

                double probability = getPatchProbability(patch);

                double patchCenterX = x + (windowSize / 2.0);
                double patchCenterY = y + (windowSize / 2.0);

                double dx = patchCenterX - centerX;
                double dy = patchCenterY - centerY;
                double distance = Math.sqrt(dx * dx + dy * dy);

                double weight = 1 + (1 - distance / maxDistance);

                densityScore += probability * weight;

                if (probability >= 0.80) {
                    estimatedHeads++;
                }
            }
        }

        double normalizedDensity = densityScore / (frameWidth * frameHeight);

        String alertLevel = getAlertLevel(normalizedDensity);
        boolean emergencyTriggered = "RED".equals(alertLevel);

        return new DensityResult(normalizedDensity, estimatedHeads, alertLevel, emergencyTriggered);
    }

    private double getPatchProbability(Mat patch) {
        // temporary dummy probability
        // later this will come from your CNN model using HeadPredictor
        return headPredictor.predictProbability(patch);
    }

    private String getAlertLevel(double density) {
        if (density < 0.0008) {
            return "GREEN";
        } else if (density < 0.0015) {
            return "YELLOW";
        } else if (density < 0.0025) {
            return "ORANGE";
        } else {
            return "RED";
        }
    }
}