package com.jarosz.zadanie_rekrutacyjne.web;


import com.jarosz.zadanie_rekrutacyjne.domain.UserRepository;
import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.xml.bind.JAXBException;



@Controller
@AllArgsConstructor()
@RequestMapping("/user")
public class PageWithFormToAddXml {

    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/createxml")
    ModelAndView createFileXml()  {
        ModelAndView mav = new ModelAndView();
        mav.addObject("createXml", userService.createXml());
        mav.setViewName("form");
        return mav ;
    }
    @GetMapping("/readxml")
    String readFileXml() throws JAXBException{
        userService.readXml();
        return "form" ;
    }

    @GetMapping("/")
    ModelAndView displayUsersPage(){
        ModelAndView mav = new ModelAndView("dataList.html");
        mav.addObject("readUser", userService.getAll());
        return mav;
    }

}
