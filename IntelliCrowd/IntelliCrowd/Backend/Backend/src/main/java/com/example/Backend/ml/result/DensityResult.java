
package com.example.Backend.ml.result;

public class DensityResult {

    private double density;
    private int estimatedHeads;
    private String alertLevel;
    private boolean emergencyTriggered;

    public DensityResult() {
    }

    public DensityResult(double density, int estimatedHeads, String alertLevel, boolean emergencyTriggered) {
        this.density = density;
        this.estimatedHeads = estimatedHeads;
        this.alertLevel = alertLevel;
        this.emergencyTriggered = emergencyTriggered;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public int getEstimatedHeads() {
        return estimatedHeads;
    }

    public void setEstimatedHeads(int estimatedHeads) {
        this.estimatedHeads = estimatedHeads;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public boolean isEmergencyTriggered() {
        return emergencyTriggered;
    }

    public void setEmergencyTriggered(boolean emergencyTriggered) {
        this.emergencyTriggered = emergencyTriggered;
    }

    @Override
    public String toString() {
        return "DensityResult{" +
                "density=" + density +
                ", estimatedHeads=" + estimatedHeads +
                ", alertLevel='" + alertLevel + '\'' +
                ", emergencyTriggered=" + emergencyTriggered +
                '}';
    }
}

