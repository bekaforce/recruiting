package com.beeline.cc_question.dtos;

import com.beeline.cc_question.entities.Education;
import com.beeline.cc_question.entities.Language;
import com.beeline.cc_question.entities.Program;
import lombok.Data;

import java.util.List;


@Data
public class CandidateDto {
    private String vacancy;
    private String name;
    private String phoneNumber;
    private String email;
    private String citizenship;
    private String birthday;
    private String address;
    private String maritalStatus;
    private Education education;
    private List<Language> languages;
    private List<Program> programs;
    private String schedule;

    public CandidateDto(String vacancy, String name, String phoneNumber, String email, String citizenship, String birthday, String address, String maritalStatus, Education education, List<Language> languages, List<Program> programs, String schedule) {
        this.vacancy = vacancy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenship = citizenship;
        this.birthday = birthday;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.languages = languages;
        this.programs = programs;
        this.schedule = schedule;
    }
}
