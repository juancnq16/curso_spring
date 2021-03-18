package org.sid.curso_spring.service.impl;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.sid.curso_spring.dao.UserDao;
import org.sid.curso_spring.dto.UserDto;
import org.sid.curso_spring.model.Role;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.RoleService;
import org.sid.curso_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    
    // METODOS DEFINITOS POR LA INTERFAZ
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    
    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }
    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        //Encriptador de contraseñas marca Spring en acción
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        
        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }
}
