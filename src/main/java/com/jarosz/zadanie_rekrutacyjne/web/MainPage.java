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
    ModelAndView displayMainPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("date", LocalDate.now().toString());
        mav.setViewName("main.html");
        return mav;
    }
}
