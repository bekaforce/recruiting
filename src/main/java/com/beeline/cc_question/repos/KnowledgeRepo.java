package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeRepo extends JpaRepository<Knowledge, Long> {
    List<Knowledge> findAllByKnowledgeType_Id(Long KnowledgeType_id);
}
