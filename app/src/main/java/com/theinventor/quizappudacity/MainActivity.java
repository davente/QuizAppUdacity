package com.theinventor.quizappudacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Create an instance of class Question and get the number of questions
    private final Question question = new Question();
    private final int questionLength = question.mQuestions.length;
    private CountDownTimer countDownTimer;
    private View[] optionsViewsArray;
    private Intent intent;
    private String name;
    private Button next_button, previous_button;
    private TextView timer;
    private TextView questionNumberTextView;
    //TextView that contains the questions
    private TextView questionPlaceHolder;
    private int numb = 0;
    private int score;

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

    private Spinner spinnerQuestions;


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
                if (!(intent == new Intent(getApplicationContext(), SummaryActivity.class))) {
                    Toast.makeText(getApplicationContext(), "Time Up", Toast.LENGTH_SHORT).show();
                    showSummary();
                }
            }
        }.start();

        next_button = findViewById(R.id.next_button);
        previous_button = findViewById(R.id.previous_button);
        previous_button.setVisibility(View.INVISIBLE);

        questionNumberTextView = findViewById(R.id.question_number_text_view);
        questionPlaceHolder = findViewById(R.id.question_place_holder);


        View questionOneOptions = findViewById(R.id.question_one_options);
        View questionTwoOptions = findViewById(R.id.question_two_options);
        View questionThreeOptions = findViewById(R.id.question_three_options);
        View questionFourOptions = findViewById(R.id.question_four_options);
        View questionFiveOptions = findViewById(R.id.question_five_options);
        View questionSixOptions = findViewById(R.id.question_six_options);
        View questionSevenOptions = findViewById(R.id.question_seven_options);
        View questionEightOptions = findViewById(R.id.question_eight_options);
        View questionNineOptions = findViewById(R.id.question_nine_options);
        View questionTenOptions = findViewById(R.id.question_ten_options);

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

        spinnerQuestions = findViewById(R.id.spinner_questions);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.questions_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinnerQuestions.setAdapter(adapter);
        spinnerQuestions.setDropDownWidth(300);
        spinnerQuestions.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

        spinnerQuestions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numb = position;
                getQuestions(position);
                hideAllViews();
                optionsViewsArray[position].setVisibility(View.VISIBLE);
                questionNumberTextView.setText(String.format("%d/%d", position + 1, questionLength));


                if (numb == questionLength - 1) {
                    previous_button.setVisibility(View.VISIBLE);
                    next_button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    next_button.setText(R.string.submit);
                }
                if ((numb == 0) || (position < questionLength - 1)) {
                    previous_button.setVisibility(View.VISIBLE);
                    next_button.setText(R.string.next);
                }
                if (numb == 0) {
                    previous_button.setVisibility(View.INVISIBLE);
                }
                if (!(numb == questionLength - 1)) {
                    next_button.setBackgroundColor(getResources().getColor(R.color.colorButton));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        getQuestions(0);
        //hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);


    }

    public void next(View view) {
        if (numb == questionLength - 1) {
            numb++;
        }

        if ((numb == 0) || (numb < questionLength - 1)) {
            numb++;
            getQuestions(numb);
            previous_button.setVisibility(View.VISIBLE);
            next_button.setText(R.string.next);
        }
        if (numb == questionLength - 1) {
            next_button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            next_button.setText(R.string.submit);
        }

        if (numb == questionLength) {
            numb--;
            showSubmitDialog();
        }

        hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);
        questionNumberTextView.setText(String.format("%d/%d", numb + 1, questionLength));
        spinnerQuestions.setSelection(numb);
    }

    public void previous(View view) {
        if (numb == 0) {
            previous_button.setVisibility(View.INVISIBLE);
        } else {
            numb--;
            getQuestions(numb);
            next_button.setBackgroundColor(getResources().getColor(R.color.colorButton));
            next_button.setText(R.string.next);
            if (numb == 0) {
                previous_button.setVisibility(View.INVISIBLE);
            }

        }
        hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);
        questionNumberTextView.setText(String.format("%d/%d", numb + 1, questionLength));
        spinnerQuestions.setSelection(numb);
    }

    private void getQuestions(int num) {
        questionPlaceHolder.setText(question.getQuestion(num));
    }

    //Hide all the option views
    private void hideAllViews() {
        for (View anOptionsViewsArray : optionsViewsArray) {
            anOptionsViewsArray.setVisibility(View.GONE);
        }
    }

    private void showSubmitDialog() {
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

    private void calculateScore() {

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

        if ((q7Option1.isChecked() && q7Option2.isChecked() && q7Option4.isChecked()) && !(q7Option3.isChecked())) {
            score++;
        }

        if ((q8Option2.isChecked() && q8Option4.isChecked()) && !(q8Option1.isChecked() || q8Option3.isChecked())) {
            score++;
        }

        if (editTextQuestionNine.getText().toString().equalsIgnoreCase(question.getCorrectAnswer(8))) {
            score++;
        }

        if (editTextQuestionTen.getText().toString().equalsIgnoreCase(question.getCorrectAnswer(9))) {
            score++;
        }
        Toast scoreToast = Toast.makeText(getApplicationContext(), "Score: " + score + " out of " + questionLength, Toast.LENGTH_SHORT);
        scoreToast.show();
    }

    private void showSummary() {
        intent = new Intent(getApplicationContext(), SummaryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
        }

        intent.putExtra("score", score);
        intent.putExtra("name", name);
        countDownTimer.cancel();
        startActivity(intent);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
