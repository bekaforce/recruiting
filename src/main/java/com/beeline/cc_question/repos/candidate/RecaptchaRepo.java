package com.beeline.cc_question.repos.candidate;

import com.beeline.cc_question.entities.candidate.recaptcha.Recaptcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecaptchaRepo extends JpaRepository<Recaptcha, Long> {
    Recaptcha findRecaptchaByName(String name);
}
