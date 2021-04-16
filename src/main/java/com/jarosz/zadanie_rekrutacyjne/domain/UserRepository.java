package com.jarosz.zadanie_rekrutacyjne.domain;

import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findOne(Long id);

    List<User> findAll();

    List<User> findByParams(SearchParams searchParams);

    void saveAll(List<User> user);
}
