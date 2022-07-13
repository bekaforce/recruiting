package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.Hooligan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooliganRepo extends JpaRepository<Hooligan, Long> {

}
