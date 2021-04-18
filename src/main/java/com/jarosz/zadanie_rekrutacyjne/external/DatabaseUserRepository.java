package com.jarosz.zadanie_rekrutacyjne.external;


import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;
import com.jarosz.zadanie_rekrutacyjne.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

//    @Override
//    public Optional<User> findOne(Long id) {
//        return jpaUserRepository.findById(id)
//                .map(this::toDomain);
//    }

    @Override
    public Page<UserEntity> findAll(Pageable numberOfPages) {
        return jpaUserRepository.findAll(numberOfPages);
    }


    @Override
    public List<User> findByParams(SearchParams searchParams) {
        return jpaUserRepository.findBasedOnSearchParams(searchParams)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public void saveAll(List<User> user) {
        List<UserEntity> collect = user.stream()
                .map(u -> toEntity(u))
                .collect(Collectors.toList());
        jpaUserRepository.saveAll(collect);
    }


    public User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .login(entity.getLogin())
                .build();
    }

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .build();
    }
}
