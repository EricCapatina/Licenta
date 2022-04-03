package com.licenta.demo.service.implementation;

import com.licenta.demo.dao.implementation.RoleDAOImpl;
import com.licenta.demo.dao.implementation.UserDAOImpl;
import com.licenta.demo.database.entity.Role;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAOImpl roleDAO;
    private final UserDAOImpl userDAO;

    public RoleServiceImpl(UserDAOImpl userDAO, RoleDAOImpl roleDAO){
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAll();
    }

    @Override
    public User updateRole(Long userId, Role newRole) {
        User user = userDAO.getById(userId);
        Role role = getRoleByName(newRole.getName());
        user.setRole(role);
        return userDAO.update(user);
    }
}
