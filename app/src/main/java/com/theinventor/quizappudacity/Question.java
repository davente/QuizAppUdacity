package com.theinventor.quizappudacity;

/**
 * Created by The Inventor on 25/06/2018.
 */

class Question {

    //Array of Questions
    final String[] mQuestions = {
            "What is the fastest land animal?",
            "A flock of crows is known as what?",
            "Which of the following is a musician?",
            "What is the most expensive currency?",
            "What is Donald Trump's middle name?",
            "How many letters are in question one's question?",
            "Which of the following is(are) the capital(s) of South Africa?\n" +
                    "(Select All That Apply)",
            "Which of the following is an Android version?\n" +
                    "(Select All That Apply)",
            "What can you catch but not throw?",
            "Did you like my questions?",
    };

    //Array of Correct Answers
    private final String[] mCorrectAnswer = {
            "Cheetah",
            "Murder",
            "Chris Brown",
            "Kuwait Dinar",
            "John",
            "26",
            "Pretoria, Cape Town and Bloemfontein",
            "Oreo and Froyo",
            "A Cold",
            "Yes"
    };

    //Method to get the question from the questionArray
    String getQuestion(int a) {
        return mQuestions[a];
    }

    //Method to get the answers from the answersArray
    String getCorrectAnswer(int a) {
        return mCorrectAnswer[a];
    }
}
