package com.beeline.cc_question.entities.interview;

public enum QuestionType {
    TEST("TEST"), INTERVIEW("INTERVIEW"), VIDEO("VIDEO"), ESSAY("ESSAY");

   private final String type;
   QuestionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
