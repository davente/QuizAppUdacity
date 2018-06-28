package com.theinventor.quizappudacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    View[] optionsViewsArray;
    String[] optionsMethodArray;
    RadioGroup[] radioGroupsArray;
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
    private RadioGroup radioGroupQuestionOne;
    private RadioGroup radioGroupQuestionTwo;
    private RadioGroup radioGroupQuestionThree;
    private RadioGroup radioGroupQuestionFour;
    private RadioGroup radioGroupQuestionFive;
    private RadioGroup radioGroupQuestionSix;
    private RadioGroup radioGroupQuestionSeven;
    private RadioGroup radioGroupQuestionEight;
    private RadioGroup radioGroupQuestionNine;
    private RadioGroup radioGroupQuestionTen;


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
                if (intent == null) {
                    showSummary();
                }
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

        radioGroupQuestionOne = findViewById(R.id.radio_group_question_one);
        radioGroupQuestionTwo = findViewById(R.id.radio_group_question_two);
        radioGroupQuestionThree = findViewById(R.id.radio_group_question_three);
        radioGroupQuestionFour = findViewById(R.id.radio_group_question_four);
        radioGroupQuestionFive = findViewById(R.id.radio_group_question_five);
        radioGroupQuestionSix = findViewById(R.id.radio_group_question_six);
        radioGroupQuestionSeven = findViewById(R.id.radio_group_question_seven);
        radioGroupQuestionEight = findViewById(R.id.radio_group_question_eight);
        radioGroupQuestionNine = findViewById(R.id.radio_group_question_nine);
        radioGroupQuestionTen = findViewById(R.id.radio_group_question_ten);

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
        hideAllViews();
        optionsViewsArray[numb].setVisibility(View.VISIBLE);
        optionsMethodArray = new String[]{question.getChoice1(numb),
                question.getChoice2(numb),
                question.getChoice3(numb),
                question.getChoice4(numb)};
        radioGroupsArray = new RadioGroup[]{radioGroupQuestionOne,
                radioGroupQuestionTwo,
                radioGroupQuestionThree,
                radioGroupQuestionFour,
                radioGroupQuestionFive,
                radioGroupQuestionSix,
                radioGroupQuestionSeven,
                radioGroupQuestionEight,
                radioGroupQuestionNine,
                radioGroupQuestionTen,};

    }

    public void submit(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Submit?")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
        }
        int score = 0;
        //intent.putExtra("score", score);
        intent.putExtra("name", name);
        startActivity(intent);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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

    private void getOptions(int num) {
        for (int a = 0; ak < questionLength; a++) {
            for (int b = 0; b < 3; b++) {
                radioGroupsArray[b].getChildAt(b).setText(optionsViewsArray[b]);
            }
        }
    }
}
