package com.beeline.cc_question.entities;

public enum QuestionType {
    SURVEY("SURVEY"), TEST("TEST"), VIDEO("VIDEO");

   private final String type;
   QuestionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
