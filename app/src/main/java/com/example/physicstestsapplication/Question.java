package com.example.physicstestsapplication;

public class Question {
    private final String questionText;
    private final String[] answers;
    private final int correctAnswerIndex;

    public Question(String text, String[] options, int correctIndex) {
        this.questionText = text;
        this.answers = options;
        this.correctAnswerIndex = correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}