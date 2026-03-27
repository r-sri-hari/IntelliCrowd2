package com.example.Backend.controller;

import com.example.Backend.model.Client;
import com.example.Backend.model.EmergencyStatus;
import com.example.Backend.repository.ClientRepository;
import com.example.Backend.repository.EmergencyStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmergencyStatusRepository emergencyRepository;

    // Get all clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Trigger emergency
    @PostMapping("/emergency/on")
    public String triggerEmergency() {

        EmergencyStatus status =
                emergencyRepository.findById(1).orElse(null);

        if(status != null){
            status.setStatus("ON");
            emergencyRepository.save(status);
        }

        return "Emergency Activated";
    }

    // Reset emergency
    @PostMapping("/emergency/off")
    public String resetEmergency() {

        EmergencyStatus status =
                emergencyRepository.findById(1).orElse(null);

        if(status != null){
            status.setStatus("OFF");
            emergencyRepository.save(status);
        }

        return "Emergency Reset";
    }
}