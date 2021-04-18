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

    @GetMapping("/{page}")
    ModelAndView displayUsersPage(@PathVariable("page") int page) {
        int pageSize = 10;
        SearchParams params = new SearchParams("","","");
        ModelAndView mav = new ModelAndView("dataList");
        Page<UserEntity> paginated = userService.findPaginated(page, pageSize);
        getUserListWithPagination(page, mav, paginated);
        mav.addObject("path", "/user/");
        mav.addObject("params", params);
        mav.addObject("query", "");
        return mav;
    }

    private void getUserListWithPagination(int page, ModelAndView mav, Page<UserEntity> paginated) {

        List<User> content = paginated.getContent().stream()
                .map(databaseUserRepository::toDomain)
                .collect(Collectors.toList());

        Integer startPagination;
        Integer endPagination;
        Integer totalPages = paginated.getTotalPages();
        List<Integer> pagination = new ArrayList<>();

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
        mav.addObject("totalPages", totalPages);
        mav.addObject("page", page);
        mav.addObject("pagination", pagination);
    }

    @GetMapping("/search")
    ModelAndView diplayFirstPageOfSearchResult(@RequestParam("name") String name,
                                               @RequestParam("surname") String surname,
                                               @RequestParam("login") String login) {
        return handleUserFiltering(name, surname, login, 1);
    }
    @GetMapping("/search/{page}")
    ModelAndView handleUserFiltering(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "surname", required = false) String surname,
                                     @RequestParam(value = "login", required = false) String login,
                                     @PathVariable("page") Integer page) {
        SearchParams params = new SearchParams(name, surname, login);
        ModelAndView mav = new ModelAndView("dataList.html");
        Page<UserEntity> paginated = userService.findPaginatedWithParams(params, page, 10);
        getUserListWithPagination(page, mav, paginated);

        mav.addObject("params", params);

        mav.addObject("path", "/user/search/");
        mav.addObject("query", "?name=" + name + "&surname=" + surname + "&login=" + login);

        return mav;
    }

}
