package com.example.testclientsauthorization.controllers;

import com.example.testclientsauthorization.entity.Client;
import com.example.testclientsauthorization.repository.ClientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/emailConfirmation")
public class emailConfirmationController {

    private final ClientRepository clientRepository;

    @Autowired
    public emailConfirmationController(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    @GetMapping//("/emailConfirmation")
    public String showEmailConfirmationPage(Model model) {

        //генерируем шестизначный код и выводим его в терминал (но это пока что)
        Random rnd = new Random();
        int randomSixDigitCode = 100000 + rnd.nextInt(900000);
        model.addAttribute("sixDigitCode", randomSixDigitCode);
        System.out.println(randomSixDigitCode);
        return "emailConfirmation";
    }

    @PostMapping
    public String processEmailConfirmationForm(Model model, HttpSession session) {

        //сохраняем нашего клиента из сессии в модель, чтобы он стал типом Client
        model.addAttribute("client", session.getAttribute("client"));
        //сохраняем модель в базу данных
        clientRepository.save((Client) model.getAttribute("client"));
        //переходим на страницу приветствия
        return "greeting";
    }

}
