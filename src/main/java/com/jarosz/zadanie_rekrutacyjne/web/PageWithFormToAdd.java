package com.jarosz.zadanie_rekrutacyjne.web;

import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;

@Controller
@AllArgsConstructor()
@RequestMapping("/form")
public class PageWithFormToAdd {

    private final UserService userService;

//    @GetMapping("/")
//    String readFileXml() throws JAXBException {
//
//        return "form.html" ;
//    }
//@GetMapping("/readxml")
//ModelAndView readFileXml() throws JAXBException{
//    ModelAndView mav = new ModelAndView();
//    mav.addObject("read", userService.readXml());
//    mav.setViewName("form");
//    return mav ;
//}
}
