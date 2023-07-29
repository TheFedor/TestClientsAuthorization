package com.example.testclientsauthorization.controllers;

import com.example.testclientsauthorization.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.testclientsauthorization.repository.ClientRepository;

@Controller
public class AuthorizationController {

    private final ClientRepository clientRepository;

    @Autowired
    public AuthorizationController(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    @RequestMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String clientLogin, @RequestParam String clientPassword, Model model) {
        Client client = clientRepository.findById(clientLogin).orElse(null);

        if (client == null || !client.getClientPassword().equals(clientPassword)) {
            model.addAttribute("errorMessageAuthorization", "Неверный логин или пароль");
            return "login";
        }

        model.addAttribute("client", client);

        //проверяем не является ли входящий администратором
        if (client.getClientLogin().equals("admin") && client.getClientPassword().equals("adminPas"))
        {
            //на всякий случай все-равно сохраним данные в модель, вдруг понадобится
            model.addAttribute("client", client);
            return "redirect:/admin";
        }

        return "greeting";
    }

}
