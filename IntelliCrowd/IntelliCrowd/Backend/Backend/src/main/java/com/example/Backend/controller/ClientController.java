package com.example.Backend.controller;

import com.example.Backend.model.EmergencyStatus;
import com.example.Backend.repository.EmergencyStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private EmergencyStatusRepository emergencyRepository;

    // Get emergency status
    @GetMapping("/emergency-status")
    public String getEmergencyStatus() {

        EmergencyStatus status =
                emergencyRepository.findById(1).orElse(null);

        if(status != null){
            return status.getStatus();
        }

        return "OFF";
    }
}