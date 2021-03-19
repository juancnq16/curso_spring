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
    @Override
    public Optional<Nota> readNota(long id){
        return notaDao.findById(id);
    }
    public Iterable<Nota> findAll(){
        return notaDao.findAll();
    }
    @Override
    public Nota save(Nota nota) {
        // TODO Auto-generated method stub

        return notaDao.save(nota);
    }
    @Override
    public void delete(Nota nota) {
        notaDao.delete(nota);
        // TODO Auto-generated method stub
        
    }
}
/**
 * @PostMapping("/modNota")
    public Nota modNota (@RequestBody NotaDto body, HttpServletResponse response) throws IOException{
        Optional<Nota> existingNote = notaService.findNota(body.getId());
        Nota nota = new Nota();
        if(existingNote.isPresent()){
            nota = existingNote.get();
            nota.setContenido(nota.getContenido());
            nota.setTitulo(nota.getTitulo());
            return notaService.save(nota);
        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"La nota no existe");
            return nota;
        }
        
    }
 */
