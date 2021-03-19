package org.sid.curso_spring.controller;

import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.service.impl.NotaServiceImpl;
import org.sid.curso_spring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NotaServiceImpl notaService;
    
    @GetMapping("/getAll")
    public Iterable<Nota> getAll(){
        return notaService.findAll();
        //return userService.findUser(id);
    }
}
