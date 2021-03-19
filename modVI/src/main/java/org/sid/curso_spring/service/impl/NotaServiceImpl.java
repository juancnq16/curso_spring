package org.sid.curso_spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.sid.curso_spring.dao.NotaDao;
import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "notaService")
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaDao notaDao;
    @Autowired
    private UserServiceImpl userService;
    
    public List <Nota> test(long uId){
        System.out.println("kakakakakakak"	);
        Optional<User> usr = userService.findUser(uId);
        
        return notaDao.findByUser(usr.get());
        /*
        System.out.println("gggggggg"	);
        System.out.println("CLASSSSSSSS "+ uId);
        for (Nota nota: userNotes){
            System.out.println("Titulo::::::: "+nota.getTitulo()+"\n Contenido--------"+nota.getContenido());
        }
        */
    }
    public Optional<Nota> findNota(long id){
        return notaDao.findById(id);
    }
    public Iterable<Nota> findAll(){
        return notaDao.findAll();
    }
    @Override
    public Nota save(Nota role) {
        // TODO Auto-generated method stub

        return notaDao.save(role);
    }
}
