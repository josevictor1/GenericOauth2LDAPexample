package com.study.oauth2.repository;

import com.study.oauth2.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
