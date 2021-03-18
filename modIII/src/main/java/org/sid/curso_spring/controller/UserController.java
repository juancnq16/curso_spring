package org.sid.curso_spring.controller;

import org.sid.curso_spring.dto.UserDto;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    
    //@Autowired
    //private AuthenticationManager authenticationManager;

    //@Autowired
    //private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        System.out.println("--------------------"+user.getName());
        return userService.save(user);
    }
}