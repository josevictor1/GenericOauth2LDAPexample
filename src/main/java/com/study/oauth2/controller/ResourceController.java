package com.study.oauth2.controller;



import com.study.oauth2.domain.Permission;
import com.study.oauth2.domain.User;
import com.study.oauth2.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@RestController
@RequestMapping("/springjwt")
public class ResourceController {
    @Autowired
    private GenericService userService;

    @RequestMapping(value ="/permissions")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Permission> getUser(){
        return userService.findAllRandomPermissions();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}