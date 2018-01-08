package com.study.oauth2.service.impl;


import com.study.oauth2.domain.Permission;
import com.study.oauth2.domain.User;
import com.study.oauth2.repository.PermissionRepositoy;
import com.study.oauth2.repository.UserRepository;
import com.study.oauth2.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Service;

import java.util.List;



@Service

public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepositoy randomPermission;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<Permission> findAllRandomPermissions() {
        return (List<Permission>)randomPermission.findAll();
    }

    @Override
    public boolean checkUser(String username, String password){

        Filter filter = new EqualsFilter("sAMAccountName",username);

        boolean auth = ldapTemplate.authenticate("OU=TQI",filter.encode(),password);

        return auth;

    }
}