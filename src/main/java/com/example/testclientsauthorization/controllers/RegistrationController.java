package com.example.testclientsauthorization.controllers;

import com.example.testclientsauthorization.entity.Client;
import com.example.testclientsauthorization.repository.ClientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private  final ClientRepository clientRepository;

    @Autowired
    public RegistrationController(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "registration";
    }

    @PostMapping
    public String processRegistrationForm(Client client, Model model, HttpSession session) {

        //Проверяем, существует ли пользователь с таким же логином
        Client existingClientWithLogin = clientRepository.findById(client.getClientLogin()).orElse(null);
        if (existingClientWithLogin != null) {
            model.addAttribute("errorMessageRegistration", "Пользователь с таким логином уже зарегистрирован");
            return "registration";
        }

        // Проверяем, существует ли пользователь с таким же адресом электронной почты
        Client existingClientWithEmail = clientRepository.findByEmailAddress(client.getEmailAddress());
        if (existingClientWithEmail != null) {
            model.addAttribute("errorMessageRegistration", "Пользователь с таким адресом эл. почты уже был зарегистрирован");
            return "registration";
        }

        //сохраняем нашего клиента в сессии, чтобы не потерялся
        session.setAttribute("client", client);

        //переходим на новый адрес, не забывая передать сообщения модели
        return "redirect:/emailConfirmation";

    }

}
