package com.beeline.cc_question.repos.interview;

import com.beeline.cc_question.entities.interview.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query(value = "select * from vcv.question q " +
            "where q.question_type = :question_type " +
            "and q.candidate_type_id = :candidateType_id " +
            "order by q.position", nativeQuery = true)
    List<Question> allByQuestionType(@Param(value = "question_type") String questionType, @Param(value = "candidateType_id") Long candidateType_id);

    @Query(value = "select max(q.position) from vcv.question q where q.question_type = :type and q.candidate_type_id = :candidate_type_id", nativeQuery = true)
    long maxPosition(@Param("type") String type, @Param(value = "candidate_type_id") Long candidateType_id);
}
