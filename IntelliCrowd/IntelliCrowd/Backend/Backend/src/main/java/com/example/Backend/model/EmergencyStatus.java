package com.example.Backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "emergency_status")
public class EmergencyStatus {

    @Id
    private int id;

    private String status;

    public EmergencyStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
