package com.study.oauth2.repository;

import com.study.oauth2.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}