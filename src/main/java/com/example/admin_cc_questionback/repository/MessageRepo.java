package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Message getMessageById(Long id);
}
