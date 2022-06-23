package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Message getMessageById(Long id);
    @Query(value = "select * from vcv.message m order by m.id", nativeQuery = true)
    List<Message> findAllOrderById();
}
