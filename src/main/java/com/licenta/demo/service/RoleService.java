package com.licenta.demo.service;

import com.licenta.demo.database.entity.Role;
import com.licenta.demo.database.entity.User;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);

    public Role getRoleById(Long id);

    User updateRole(Long userId, Role newRole);
}
