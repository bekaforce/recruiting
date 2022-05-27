package com.example.admin_cc_questionback.repository;

import com.example.admin_cc_questionback.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    Question getQuestionById(Long question_id);

    @Query(value = "select max(q.position) from vcv.question q where q.question_type = :type", nativeQuery = true)
    long maxPosition(@Param("type") String type);


    @Query(value = "select * from vcv.question q " +
            "where q.question_type = :question_type " +
            "order by q.position", nativeQuery = true)
    List<Question> findAllByQuestionType(@Param(value = "question_type") String questionType);
}
