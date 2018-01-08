package com.study.oauth2.repository;

import com.study.oauth2.domain.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepositoy extends CrudRepository<Permission, Long> {
}
