package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.KnowledgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeTypeRepo extends JpaRepository<KnowledgeType, Long> {
    List<KnowledgeType> findAllByCandidateType_Id(Long CandidateType_id);
    KnowledgeType findKnowledgeTypeById(Long id);
}
