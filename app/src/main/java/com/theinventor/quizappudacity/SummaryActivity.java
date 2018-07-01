package com.theinventor.quizappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    //Interval between back clicks to show toast
    private static final int TIME_INTERVAL = 2000;
    private final Question question = new Question();
    private final int questionLength = question.mQuestions.length;
    private String name;
    private int score;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        TextView numberOfQuestions = findViewById(R.id.number_of_questions);

        //TextViews that show the questions
        TextView summaryQuestionOne = findViewById(R.id.summary_question_one);
        TextView summaryQuestionTwo = findViewById(R.id.summary_question_two);
        TextView summaryQuestionThree = findViewById(R.id.summary_question_three);
        TextView summaryQuestionFour = findViewById(R.id.summary_question_four);
        TextView summaryQuestionFive = findViewById(R.id.summary_question_five);
        TextView summaryQuestionSix = findViewById(R.id.summary_question_six);
        TextView summaryQuestionSeven = findViewById(R.id.summary_question_seven);
        TextView summaryQuestionEight = findViewById(R.id.summary_question_eight);
        TextView summaryQuestionNine = findViewById(R.id.summary_question_nine);
        TextView summaryQuestionTen = findViewById(R.id.summary_question_ten);

        //TextViews that show the correct answers
        TextView correctAnswerQuestionOne = findViewById(R.id.correct_answer_question_one);
        TextView correctAnswerQuestionTwo = findViewById(R.id.correct_answer_question_two);
        TextView correctAnswerQuestionThree = findViewById(R.id.correct_answer_question_three);
        TextView correctAnswerQuestionFour = findViewById(R.id.correct_answer_question_four);
        TextView correctAnswerQuestionFive = findViewById(R.id.correct_answer_question_five);
        TextView correctAnswerQuestionSix = findViewById(R.id.correct_answer_question_six);
        TextView correctAnswerQuestionSeven = findViewById(R.id.correct_answer_question_seven);
        TextView correctAnswerQuestionEight = findViewById(R.id.correct_answer_question_eight);
        TextView correctAnswerQuestionNine = findViewById(R.id.correct_answer_question_nine);
        TextView correctAnswerQuestionTen = findViewById(R.id.correct_answer_question_ten);

        //TextView containing exclamation mark
        TextView exclamation = findViewById(R.id.exclamation_mark);

        //Array of questions' TextViews
        TextView[] summaryQuestionsArray = new TextView[]{summaryQuestionOne,
                summaryQuestionTwo,
                summaryQuestionThree,
                summaryQuestionFour,
                summaryQuestionFive,
                summaryQuestionSix,
                summaryQuestionSeven,
                summaryQuestionEight,
                summaryQuestionNine,
                summaryQuestionTen};

        //Array of correct answers' TextViews
        TextView[] correctAnswersArray = new TextView[]{correctAnswerQuestionOne,
                correctAnswerQuestionTwo,
                correctAnswerQuestionThree,
                correctAnswerQuestionFour,
                correctAnswerQuestionFive,
                correctAnswerQuestionSix,
                correctAnswerQuestionSeven,
                correctAnswerQuestionEight,
                correctAnswerQuestionNine,
                correctAnswerQuestionTen};

        //Get and set the questions for each question from the Question class
        for (int i = 0; i < summaryQuestionsArray.length; i++) {
            summaryQuestionsArray[i].setText(question.getQuestion(i));
        }

        //Get and set the correct answers for each question from the Question class
        for (int i = 0; i < correctAnswersArray.length; i++) {
            correctAnswersArray[i].setText(question.getCorrectAnswer(i));
        }

        //Get the name and user score. Scores above 7 end with an '!'
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
            score = bundle.getInt("score");
            if (score > 7) {
                exclamation.setVisibility(View.VISIBLE);
            } else {
                exclamation.setVisibility(View.INVISIBLE);
            }
        }

        nameTextView.setText(name);
        scoreTextView.setText(String.format("%d", score));
        numberOfQuestions.setText(String.format("%d", questionLength));

    }

    //You need to press back consecutively within the space of 2 secs to exit
    @Override
    public void onBackPressed() {
        Toast backToast = Toast.makeText(this, "Touch again to exit", Toast.LENGTH_SHORT);
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast.show();
            mBackPressed = System.currentTimeMillis();
        }
    }

    //Go to the StartActivity while clearing back stack
    public void playAgain(View view) {
        Intent intent = new Intent(SummaryActivity.this, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
