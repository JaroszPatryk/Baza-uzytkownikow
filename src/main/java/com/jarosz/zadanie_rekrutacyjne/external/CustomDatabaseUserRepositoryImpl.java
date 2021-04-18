package com.jarosz.zadanie_rekrutacyjne.external;

import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomDatabaseUserRepositoryImpl implements CustomDatabaseUserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findBasedOnSearchParams(SearchParams searchParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> rootFirst = query.from(UserEntity.class);

//        query.select(rootFirst);
        List<Predicate> predicates = new ArrayList<>();
        if(searchParams.getName() != null && !searchParams.getName().isEmpty()){
            predicates.add(criteriaBuilder.like(rootFirst.get("name"), "%" + searchParams.getName() + "%"));
        }
        if(searchParams.getSurname() != null && !searchParams.getSurname().isEmpty()){
            predicates.add(criteriaBuilder.like(rootFirst.get("surname"), "%" + searchParams.getSurname() + "%"));
        }
        if(searchParams.getLogin() != null && !searchParams.getLogin().isEmpty()){
            predicates.add(criteriaBuilder.like(rootFirst.get("login"), "%" + searchParams.getLogin() + "%"));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}
