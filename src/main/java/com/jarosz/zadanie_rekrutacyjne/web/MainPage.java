package com.jarosz.zadanie_rekrutacyjne.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;


@Controller
@RequestMapping
public class MainPage {

    @GetMapping("/")
    String displayMainPage() {
        return "main.html";
    }
}
