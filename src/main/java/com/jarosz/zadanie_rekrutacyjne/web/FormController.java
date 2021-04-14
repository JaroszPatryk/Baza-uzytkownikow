package com.jarosz.zadanie_rekrutacyjne.web;


import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;


@Controller
@AllArgsConstructor
public class FormController {

    private final UserService userService;


    @GetMapping("/")
    String displayMainPage()  {
        userService.createXml();
        return "redirect:main.html" ;
    }
    @GetMapping("/elo")
    String displayMainPage1() throws JAXBException, FileNotFoundException, XMLStreamException {

        userService.readXml();
        return "redirect:main.html" ;
    }
    @PostMapping("/add")
    String handleAddUser(@ModelAttribute("user") User user) {
            userService.create(user);
        return "redirect:main.html";
    }

}
