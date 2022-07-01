package com.example.admin_cc_questionback.entities.candidate;

public enum QuestionType {
    TEST("TEST"), VIDEO("VIDEO");

    private final String type;
    QuestionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
