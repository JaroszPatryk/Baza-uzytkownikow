package com.jarosz.zadanie_rekrutacyjne.api;


import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Getter
public class UserApi {

    private final UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/search")
    public List<User> getByParams(@RequestBody SearchParams searchParams){
        return userService.searchByParams(searchParams);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getOne(@PathVariable Long userId){
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createMeal(@RequestBody  User user) {

        userService.create(user);
        return ResponseEntity.status(201).build();
    }
}
