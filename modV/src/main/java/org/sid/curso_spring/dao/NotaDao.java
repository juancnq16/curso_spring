package org.sid.curso_spring.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.sid.curso_spring.model.Nota;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NotaDao extends CrudRepository <Nota,Long>  {
    //
    @Query("SELECT n FROM Nota n WHERE n.user_id = :ownerId")
    List <Nota> findUserNotes(@Param("ownerId") long OwnUserId);    
}
