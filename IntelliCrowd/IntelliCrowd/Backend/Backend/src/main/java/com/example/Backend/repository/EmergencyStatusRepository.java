package com.example.Backend.repository;

import com.example.Backend.model.EmergencyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyStatusRepository extends JpaRepository<EmergencyStatus, Integer> {
}