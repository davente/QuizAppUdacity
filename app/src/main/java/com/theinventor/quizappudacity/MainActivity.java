package com.theinventor.quizappudacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    View[] optionsViewsArray;

    private String name;
    private Button next_button, previous_button;
    private TextView timer;
    private TextView questionNumberTextView;

    //TextView that contains the questions
    private TextView questionPlaceHolder;

    //Create an instance of class Question and get the number of questions
    private Question question = new Question();
    private int questionLength = question.mQuestions.length;
    private int numb = 0;
    private int score;

    //These are the views that are included in the MainActivity
    private View questionOneOptions;
    private View questionTwoOptions;
    private View questionThreeOptions;
    private View questionFourOptions;
    private View questionFiveOptions;
    private View questionSixOptions;
    private View questionSevenOptions;
    private View questionEightOptions;
    private View questionNineOptions;
    private View questionTenOptions;
    private Intent intent;

    private RadioButton q1Answer;
    private RadioButton q2Answer;
    private RadioButton q3Answer;
    private RadioButton q4Answer;
    private RadioButton q5Answer;
    private RadioButton q6Answer;

    private CheckBox q7Option1;
    private CheckBox q7Option2;
    private CheckBox q7Option3;
    private CheckBox q7Option4;

    private CheckBox q8Option1;
    private CheckBox q8Option2;
    private CheckBox q8Option3;
    private CheckBox q8Option4;

    private EditText editTextQuestionNine;
    private EditText editTextQuestionTen;

    private String q1CorrectAnswer;
    private String q2CorrectAnswer;
    private String q3CorrectAnswer;
    private String q4CorrectAnswer;
    private String q5CorrectAnswer;
    private String q6CorrectAnswer;
    private String q7CorrectAnswer;
    private String q8CorrectAnswer;
    private String q9CorrectAnswer;
    private String q10CorrectAnswer;

    private String[] correctAnswersArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final long quizTime = 2 * 60 * 1000;
        timer = findViewById(R.id.timer);
        countDownTimer = new CountDownTimer(quizTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timer.setText(String.format("%2d:%02d", (seconds % 3600) / 60, seconds % 60));

            }

            @Override
            public void onFinish() {
                //TODO Show a dialog saying 'Times up'
                countDownTimer.cancel();
//                if (intent == null) {
//                    //showSummary();
//                }
                timer.setText("Done");
            }
        }.start();

        next_button = findViewById(R.id.next_button);
        previous_button = findViewById(R.id.previous_button);
        previous_button.setVisibility(View.INVISIBLE);

        questionNumberTextView = findViewById(R.id.question_number_text_view);
        questionPlaceHolder = findViewById(R.id.question_place_holder);


        questionOneOptions = findViewById(R.id.question_one_options);
        questionTwoOptions = findViewById(R.id.question_two_options);
        questionThreeOptions = findViewById(R.id.question_three_options);
        questionFourOptions = findViewById(R.id.question_four_options);
        questionFiveOptions = findViewById(R.id.question_five_options);
        questionSixOptions = findViewById(R.id.question_six_options);
        questionSevenOptions = findViewById(R.id.question_seven_options);
        questionEightOptions = findViewById(R.id.question_eight_options);
        questionNineOptions = findViewById(R.id.question_nine_options);
        questionTenOptions = findViewById(R.id.question_ten_options);

        q1Answer = findViewById(R.id.q1_answer);
        q2Answer = findViewById(R.id.q2_answer);
        q3Answer = findViewById(R.id.q3_answer);
        q4Answer = findViewById(R.id.q4_answer);
        q5Answer = findViewById(R.id.q5_answer);
        q6Answer = findViewById(R.id.q6_answer);

        q7Option1 = findViewById(R.id.q7_option_1);
        q7Option2 = findViewById(R.id.q7_option_2);
        q7Option3 = findViewById(R.id.q7_option_3);
        q7Option4 = findViewById(R.id.q7_option_4);

        q8Option1 = findViewById(R.id.q8_option_1);
        q8Option2 = findViewById(R.id.q8_option_2);
        q8Option3 = findViewById(R.id.q8_option_3);
        q8Option4 = findViewById(R.id.q8_option_4);


        editTextQuestionNine = findViewById(R.id.edit_text_q9);
        editTextQuestionTen = findViewById(R.id.edit_text_q10);


        optionsViewsArray = new View[]{questionOneOptions,
                questionTwoOptions,
                questionThreeOptions,
                questionFourOptions,
                questionFiveOptions,
                questionSixOptions,
                questionSevenOptions,
                questionEightOptions,
                questionNineOptions,
                questionTenOptions};

        //get the first question
        getQuestions(questionLength - questionLength);
        //hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);


    }

    public void submit(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Submit?")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        calculateScore();
                        showSummary();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.show();

    }

    public void showSummary() {
        intent = new Intent(getApplicationContext(), SummaryActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
        }

        intent.putExtra("score", score);
        intent.putExtra("name", name);
        startActivity(intent);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void next(View view) {
        if (numb == questionLength - 1) {

            next_button.setVisibility(View.INVISIBLE);
        } else {
            numb++;
            getQuestions(numb);
            previous_button.setVisibility(View.VISIBLE);
            if (numb == questionLength - 1) {
                next_button.setVisibility(View.INVISIBLE);
            }
        }
        hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);
        questionNumberTextView.setText(numb + 1 + "/10");
        //Toast toast = Toast.makeText(getApplicationContext(), "Question 1 answer ID " + radioGroupQuestionOne.getCheckedRadioButtonId(), Toast.LENGTH_LONG);
        //toast.show();
    }

    public void previous(View view) {
        if (numb == 0) {
            previous_button.setVisibility(View.INVISIBLE);
        } else {
            numb--;
            getQuestions(numb);
            next_button.setVisibility(View.VISIBLE);
            if (numb == 0) {
                previous_button.setVisibility(View.INVISIBLE);
            }
        }
        hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);
        questionNumberTextView.setText(numb + 1 + "/10");
    }

    private void getQuestions(int num) {
        questionPlaceHolder.setText(question.getQuestion(num));
    }

    //Hide all the option views
    private void hideAllViews() {
        for (int i = 0; i < optionsViewsArray.length; i++) {
            optionsViewsArray[i].setVisibility(View.GONE);
        }
    }

//    private void getOptions(int num) {
//        for (int a = 0; a < questionLength; a++) {
//            for (int b = 0; b < 3; b++) {
//                //radioGroupsArray[b].getChildAt(b).setText(optionsViewsArray[b]);
//            }
//        }
//    }

    public void calculateScore() {

        score = 0;
        if (q1Answer.isChecked()) {
            score++;
        }
        if (q2Answer.isChecked()) {
            score++;
        }
        if (q3Answer.isChecked()) {
            score++;
        }
        if (q4Answer.isChecked()) {
            score++;
        }
        if (q5Answer.isChecked()) {
            score++;
        }
        if (q6Answer.isChecked()) {
            score++;
        }

        if ((q7Option1.isChecked() && q7Option3.isChecked()) && !(q7Option2.isChecked() || q7Option4.isChecked())) {
            score++;
        }

        if ((q8Option2.isChecked() && q8Option4.isChecked()) && !(q8Option1.isChecked() || q8Option3.isChecked())) {
            score++;
        }

        if (Objects.equals(editTextQuestionNine.getText().toString(), question.getCorrectAnswer(8))) {
            score++;
        }

        if (Objects.equals(editTextQuestionTen.getText().toString(), question.getCorrectAnswer(9))) {
            score++;
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Score: " + score + " out of " + questionLength, Toast.LENGTH_LONG);
        toast.show();
    }
}
