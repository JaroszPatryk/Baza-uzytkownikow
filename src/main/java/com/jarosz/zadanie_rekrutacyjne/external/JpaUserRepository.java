package com.jarosz.zadanie_rekrutacyjne.external;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, CustomDatabaseUserRepository {

    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findBySurname(String surname);
    Optional<UserEntity> findByLogin(String login);

    @Query("select u from UserEntity u where u.name like concat('%', ?1, '%') and " +
            "u.surname like concat('%', ?2, '%') and " +
            "u.login like concat('%', ?3, '%')")
    Page<UserEntity> findPaginatedByParams(String name, String surname, String login, Pageable pageable);
}
