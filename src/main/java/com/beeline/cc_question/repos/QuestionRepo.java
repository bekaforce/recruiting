package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    Question getQuestionById(Long question_id);

    @Query(value = "select * from vcv.question q " +
            "where q.question_type = :question_type " +
            "order by q.position", nativeQuery = true)
    List<Question> findAllByQuestionType(@Param(value = "question_type") String questionType);
}
