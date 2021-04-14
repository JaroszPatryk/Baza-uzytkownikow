package com.jarosz.zadanie_rekrutacyjne.external;


import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return jpaUserRepository.findById(id)
                .map(this::toDomain);
    }



    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByParams(SearchParams searchParams) {
        return jpaUserRepository.findBasedOnSearchParams(searchParams)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void create(User user) {
        jpaUserRepository.save(toEntity(user));
    }

    private User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .login(entity.getLogin())
                .build();
    }

    private UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .build();
    }
}
