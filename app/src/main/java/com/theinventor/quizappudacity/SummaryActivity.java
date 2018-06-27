package com.theinventor.quizappudacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    private String name;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView scoreTextView = findViewById(R.id.score_text_view);

        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            name = bundle.getString("name");
            score = bundle.getString("score");
        }

        nameTextView.setText(String.format("%s%s", nameTextView.getText().toString(), name));
        scoreTextView.setText(String.format("%s%s", scoreTextView.getText().toString(), score));

    }

    public void playAgain(View view) {
        Intent intent = new Intent(SummaryActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
