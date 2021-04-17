package com.jarosz.zadanie_rekrutacyjne.web;

import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBException;

@Controller
@AllArgsConstructor()
@RequestMapping("/")
public class PageWithFormToAdd {

    private final UserService userService;

    @GetMapping("/add")
    String readFileXml(String path) throws JAXBException {
        userService.readXml(path);
        return "form" ;
    }
}
