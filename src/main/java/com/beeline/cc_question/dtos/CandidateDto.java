package com.beeline.cc_question.dtos;

import com.beeline.cc_question.entities.Experience;
import com.beeline.cc_question.entities.Questionnaire;
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
    private String address;
    private Experience experience;
    private String education;
    private List<Questionnaire> questionnaireList;
    private String schedule;
    private Long candidateType_id;

    public CandidateDto(String name, String phoneNumber, String email, String citizenship, LocalDate birthday, String address, Experience experience, String education, List<Questionnaire> questionnaireList, String schedule, Long candidateType_id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenship = citizenship;
        this.birthday = birthday;
        this.experience = experience;
        this.education = education;
        this.schedule = schedule;
        this.candidateType_id = candidateType_id;
        this.questionnaireList = questionnaireList;
    }
}
