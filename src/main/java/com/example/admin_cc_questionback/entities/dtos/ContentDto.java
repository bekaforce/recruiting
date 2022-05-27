package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class ContentDto {
    private String content;

    public ContentDto(String content) {
        this.content = content;
    }

    public ContentDto() {
    }
}
