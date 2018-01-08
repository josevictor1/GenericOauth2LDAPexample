package com.study.oauth2.service;

import com.study.oauth2.domain.Permission;
import com.study.oauth2.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenericService {

    User findByUsername(String username);

    List<User> findAllUsers();

    List<Permission> findAllRandomPermissions();

    boolean checkUser(String username, String password);
}