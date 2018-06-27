package com.theinventor.quizappudacity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    private String name;
    private TextView timer;
    private int mScore = 0;

    private Intent intent;


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
        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
        }
        intent.putExtra("score", mScore);
        intent.putExtra("name", name);
        startActivity(intent);
    }

}
