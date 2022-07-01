package com.beeline.cc_question.entities.dtos.candidate;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.candidate.Questionnaire;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class CandidateDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String citizenship;
    private LocalDate birthday;
    private Experience experience;
    private String education;
    private List<Questionnaire> questionnaireList;
    private String schedule;
    private String address;
    private Long candidateType_id;

    public CandidateDto(String name, String phoneNumber, String email, String citizenship, LocalDate birthday, Experience experience, String education, List<Questionnaire> questionnaireList, String schedule, String address, Long candidateType_id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenship = citizenship;
        this.birthday = birthday;
        this.experience = experience;
        this.education = education;
        this.questionnaireList = questionnaireList;
        this.schedule = schedule;
        this.address = address;
        this.candidateType_id = candidateType_id;
    }
}
