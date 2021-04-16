package com.jarosz.zadanie_rekrutacyjne.web;


import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.xml.bind.JAXBException;



@Controller
@AllArgsConstructor
public class FormController {

    private final UserService userService;


    @GetMapping("/")
    String displayMainPage()  {
        userService.createXml();
        return "dataList" ;
    }
    @GetMapping("/elo")
    String displayMainPage1() throws JAXBException{
        userService.readXml();
        return "main" ;
    }

}
