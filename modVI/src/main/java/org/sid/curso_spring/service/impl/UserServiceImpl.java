package org.sid.curso_spring.service.impl;

import java.util.List;
import java.util.Optional;
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
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
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
        nUser.setPassword(this.passwordEncoder().encode(user.getPassword()));
        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }
    public User CreateAdmin(UserDto user, Set<Role> roleSet) {

        User nUser = user.getUserFromDto();
        //Encriptador de contraseñas marca Spring en acción
        nUser.setPassword(this.passwordEncoder().encode(user.getPassword()));
        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
    public Optional<User> findUser(Long id) {
        return userDao.findById(id);

    }
}
