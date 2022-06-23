package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeRepo extends JpaRepository<Knowledge, Long> {
    Knowledge findKnowledgeById(Long id);
    List<Knowledge> findAllByKnowledgeType_Id(Long KnowledgeType_id);
}
