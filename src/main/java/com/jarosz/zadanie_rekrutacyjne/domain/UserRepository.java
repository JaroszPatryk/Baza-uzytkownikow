package com.jarosz.zadanie_rekrutacyjne.domain;

import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.external.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

//    Optional<User> findOne(Long id);

    Page<UserEntity> findAll(Pageable numberOfPages);

    List<User> findByParams(SearchParams searchParams);

    void saveAll(List<User> user);

    Page<UserEntity> findPaginatedByParams(SearchParams searchParams, Pageable pageable);
}
