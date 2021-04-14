package com.jarosz.zadanie_rekrutacyjne.external;

import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;

import java.util.List;

public interface CustomDatabaseUserRepository {

    List<UserEntity> findBasedOnSearchParams (SearchParams searchParams);
}
