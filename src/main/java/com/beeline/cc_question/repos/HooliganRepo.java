package com.beeline.cc_question.repos;

import com.beeline.cc_question.entities.Hooligan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooliganRepo extends JpaRepository<Hooligan, Long> {

}
