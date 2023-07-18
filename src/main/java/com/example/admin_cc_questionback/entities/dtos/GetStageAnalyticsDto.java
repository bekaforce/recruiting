package com.example.admin_cc_questionback.entities.dtos;

public interface GetStageAnalyticsDto {
    String getCandidateType();
    Long getOverall();
    Long getTesting();
    Long getFailed();
    Long getChoice();
    Long getEssay();
    Long getVideo();
    Long getCompleted();
    Long getInvited();

}
