package com.example.admin_cc_questionback.repository.candidate;

import com.example.admin_cc_questionback.entities.candidate.KnowledgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeTypeRepo extends JpaRepository<KnowledgeType, Long> {
    KnowledgeType findKnowledgeTypeById(Long id);
    List<KnowledgeType> findAllByCandidateType_Id(Long CandidateType_id);
}
