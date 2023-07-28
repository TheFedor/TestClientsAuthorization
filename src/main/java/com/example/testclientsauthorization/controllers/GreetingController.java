package com.example.testclientsauthorization.controllers;

import com.example.testclientsauthorization.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    private final ClientRepository clientRepository;

    @Autowired
    public GreetingController(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String showGreetingPage(Model model) {
        model.addAttribute("username", "Anonymous"); // Заменяем principal.getName() на "Anonymous"
        return "greeting";
    }

}
