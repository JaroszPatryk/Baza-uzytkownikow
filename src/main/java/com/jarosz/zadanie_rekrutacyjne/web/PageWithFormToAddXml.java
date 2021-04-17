package com.jarosz.zadanie_rekrutacyjne.web;


import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserRepository;
import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import com.jarosz.zadanie_rekrutacyjne.external.DatabaseUserRepository;
import com.jarosz.zadanie_rekrutacyjne.external.UserEntity;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.xml.bind.JAXBException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor()
@RequestMapping("/user")
public class PageWithFormToAddXml {

    private final UserService userService;
    private final DatabaseUserRepository databaseUserRepository;


    @GetMapping("/createxml")
    ModelAndView createFileXml()  {
        ModelAndView mav = new ModelAndView();
        mav.addObject("createXml", userService.createXml());
        mav.setViewName("form");
        return mav ;
    }
//    @GetMapping("/readxml")
//    String readFileXml() throws JAXBException{
//        userService.readXml();
//        return "form" ;
//    }

    @GetMapping("/{page}")
    ModelAndView displayUsersPage(@PathVariable("page") int page, String surname) throws NoSuchAlgorithmException {
        int pageSize = 100;
        ModelAndView mav = new ModelAndView("dataList.html");
        Page<UserEntity> paginated = userService.findPaginated(page, pageSize);
        List<User> content = paginated.getContent().stream()
                .map(databaseUserRepository::toDomain)
                .collect(Collectors.toList());
        mav.addObject("users", content);
//        mav.addObject("surnameWithMD5", userService.generateMD5(surname));
        mav.addObject("totalElements", paginated.getTotalElements());
        mav.addObject("totalPages", paginated.getTotalPages());
        mav.addObject("page", page);
        return mav;
    }
    @PostMapping("/search")
    ModelAndView handleUserFiltering(@ModelAttribute("params") SearchParams params) {
        ModelAndView mav = new ModelAndView("dataList.html");
        mav.addObject("user", userService.searchByParams(params));
        mav.addObject("params", params);

        return mav;
    }

}
