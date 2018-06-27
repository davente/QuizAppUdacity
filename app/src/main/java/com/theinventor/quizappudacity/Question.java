package com.theinventor.quizappudacity;

/**
 * Created by The Inventor on 25/06/2018.
 */

public class Question {

    public String mQuestions[] = {
            "Which is a Programming Language?",
            "In COMAL language program, after name of procedure parameters must be in?",
            "Programming language COBOL works best for use in?",
            "What is the capital of Liberia?",
            "Which of the following are capitals of South Africa\n" +
                    "(Select all that apply)"
    };

    public String mChoices[][] = {
            {"HTML", "CSS", "Vala", "PHP"},
            {"Punctuation Marks", "Back-Slash", "Brackets", "Semi Colon"},
            {"Siemens Applications", "Student Applications", "Social Applications", "Commercial Applications"},
            {"Monrovia", "Abuja", "Dakar", "Cape Town"},
            {"Johannesburg", "Cape Town", "Pretoria", "Bleomfontain"}
    };

    public String mCorrectAnswer[] = {
            "PHP",
            "Brackets",
            "Commercial Applications",
            "Correct",
            "Correct"
    };

    public String getQuestion(int a) {
        return mQuestions[a];
    }

    public String getChoice1(int a) {
        return mChoices[a][0];
    }

    public String getChoice2(int a) {
        return mChoices[a][1];
    }

    public String getChoice3(int a) {
        return mChoices[a][2];
    }

    public String getChoice4(int a) {
        return mChoices[a][3];
    }

    public String getCorrectAnswer(int a) {
        return mCorrectAnswer[a];
    }
}
