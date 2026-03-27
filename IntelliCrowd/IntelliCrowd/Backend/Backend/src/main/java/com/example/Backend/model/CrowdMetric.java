package com.example.Backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crowd_metrics")
public class CrowdMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneName;
    private double density;
    private int estimatedHeads;
    private String alertLevel;
    private boolean emergencyTriggered;
    private LocalDateTime timestamp;

    public CrowdMetric() {
    }

    public CrowdMetric(String zoneName, double density, int estimatedHeads, String alertLevel, boolean emergencyTriggered, LocalDateTime timestamp) {
        this.zoneName = zoneName;
        this.density = density;
        this.estimatedHeads = estimatedHeads;
        this.alertLevel = alertLevel;
        this.emergencyTriggered = emergencyTriggered;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}