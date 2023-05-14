package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.models.Message;
import com.gestresmat.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("SELECT m FROM Message m, MessageRecepteur mr WHERE m=mr.message and mr.recepteur.id=:id")
    public List<Message> findMessageByRecepteurId(@Param("id") Long id);


    public Message findMessageById(Long id);

    public List<Message> findAllByOrderByIdDesc();
}






