package com.beeline.cc_question.entities.dtos.candidate;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.candidate.Questionnaire;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class CandidateDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate birthday;
    private Experience experience;
    private String education;
    private List<Questionnaire> questionnaireList;
    private String schedule;
    private String address;
    private Long candidateType_id;
    private String recaptcha;

    public CandidateDto(String name, String surname, String phoneNumber, String email, LocalDate birthday, Experience experience, String education, List<Questionnaire> questionnaireList, String schedule, String address, Long candidateType_id, String recaptcha) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.experience = experience;
        this.education = education;
        this.questionnaireList = questionnaireList;
        this.schedule = schedule;
        this.address = address;
        this.candidateType_id = candidateType_id;
        this.recaptcha = recaptcha;
    }
}
