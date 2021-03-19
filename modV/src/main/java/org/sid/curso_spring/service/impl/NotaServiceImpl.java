package org.sid.curso_spring.service.impl;

import java.util.List;

import org.sid.curso_spring.dao.NotaDao;
import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "notaService")
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaDao notaDao;
    public void test(){
        Long uId = (long) 1;
        List <Nota> userNotes = notaDao.findUserNotes(uId);
        for (Nota nota: userNotes){
            System.out.println("Titulo::::::: "+nota.getTitulo()+"\n Contenido--------"+nota.getContenido());
        }
    }
}
