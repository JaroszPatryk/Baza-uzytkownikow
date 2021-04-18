package com.jarosz.zadanie_rekrutacyjne.web;


import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import com.jarosz.zadanie_rekrutacyjne.external.DatabaseUserRepository;
import com.jarosz.zadanie_rekrutacyjne.external.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.xml.bind.JAXBException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor()
@RequestMapping("/user")
public class PageWithFormList {

    private final UserService userService;
    private final DatabaseUserRepository databaseUserRepository;

    @GetMapping
    public ModelAndView displayMainPage() {
        return displayUsersPage(1);
    }
//    @GetMapping("/user")
//    String showFormPage(){
//        return "dataList";
//    }

//    @GetMapping("/user/createxml")
//    ModelAndView createFileXml()  {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("createXml", userService.createXml());
//        mav.setViewName("form");
//        return mav ;
//    }
//    @GetMapping("/readxml")
//    String readFileXml() throws JAXBException{
//        userService.readXml();
//        return "form" ;
//    }

    @GetMapping("/{page}")
    ModelAndView displayUsersPage(@PathVariable("page") int page) {
        int pageSize = 10;
        ModelAndView mav = new ModelAndView("dataList");
        Page<UserEntity> paginated = userService.findPaginated(page, pageSize);
        List<User> content = paginated.getContent().stream()
                .map(databaseUserRepository::toDomain)
                .collect(Collectors.toList());

        int startPagination;
        int endPagination;
        int totalPages = paginated.getTotalPages();

        if (totalPages > page + 3) {
            endPagination = page + 3;
        } else {
            endPagination = totalPages;
        }

        if (page - 3 < 1) {
            startPagination = 1;
        } else {
            startPagination = page - 3;
        }
        List<Integer> pagination = new ArrayList<>();

        for (int i = startPagination; i <= endPagination; i++) {
            if(i == startPagination && startPagination!=1){
                pagination.add(1);
            }

            pagination.add(i);
        }
        if(pagination.size() > 0){
            if(!pagination.get(pagination.size()-1).equals(totalPages)){
                pagination.add(totalPages);
            }
        }

        mav.addObject("users", content);
        mav.addObject("totalElements", paginated.getTotalElements());
        mav.addObject("totalPages", paginated.getTotalPages());
        mav.addObject("page", page);
        mav.addObject("pagination", pagination);
        return mav;
    }
    @GetMapping("/search")
    ModelAndView handleUserFiltering(@RequestParam("name") String name,
                                     @RequestParam("surname") String surname,
                                     @RequestParam("login") String login) {
        SearchParams params = new SearchParams(name, surname, login);
        ModelAndView mav = new ModelAndView("dataList.html");
        mav.addObject("users", userService.searchByParams(params));
        mav.addObject("params", params);

        return mav;
    }

}
