package com.jarosz.zadanie_rekrutacyjne.external;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, CustomDatabaseUserRepository {

    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findBySurname(String surname);
    Optional<UserEntity> findByLogin(String login);
}
