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
            "Why did the chicken cross the road?",
            "What did the traffic light say to the car?",
            "I am something, I kiss my mother before I die?",
            "Who said \"Baby girl amma stick with you pass e bamboo\"?",
            "What is Donald Trump's middle name?",
            "Did you like my questions?",
    };

//    public String mChoices[][] = {
//            {"HTML", "CSS", "Vala", "PHP"},
//            {"Punctuation Marks", "Back-Slash", "Brackets", "Semi Colon"},
//            {"Siemens Applications", "Student Applications", "Social Applications", "Commercial Applications"},
//            {"Monrovia", "Abuja", "Dakar", "Cape Town"},
//            {"CSS", "Vala", "HTML", "PHP"},
//            {"Punctuation Marks", "Brackets", "Semi Colon", "Back-Slash"},
//            {"Siemens Applications", "Student Applications", "Social Applications", "Commercial Applications"},
//            {"HTML", "PHP", "CSS", "Vala"},
//            {"Punctuation Marks", "Back-Slash", "Brackets", "Semi Colon"},
//            {"Siemens Applications", "Commercial Applications", "Student Applications", "Social Applications"}
//    };

    public String mCorrectAnswer[] = {
            "PHP",
            "Brackets",
            "Commercial Applications",
            "Correct",
            "PHP",
            "Brackets",
            "Commercial Applications",
            "Correct",
            "Joshua",
            "Yes"
    };

    public String getQuestion(int a) {
        return mQuestions[a];
    }

//    public String getChoice1(int a) {
//        return mChoices[a][0];
//    }
//
//    public String getChoice2(int a) {
//        return mChoices[a][1];
//    }
//
//    public String getChoice3(int a) {
//        return mChoices[a][2];
//    }
//
//    public String getChoice4(int a) {
//        return mChoices[a][3];
//    }

    public String getCorrectAnswer(int a) {
        return mCorrectAnswer[a];
    }
}
