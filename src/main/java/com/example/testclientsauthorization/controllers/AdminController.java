package com.example.testclientsauthorization.controllers;

import com.example.testclientsauthorization.entity.Client;
import com.example.testclientsauthorization.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClientRepository clientRepository;

    @Autowired
    public AdminController(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String showAdminPage(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "admin";
    }

}
