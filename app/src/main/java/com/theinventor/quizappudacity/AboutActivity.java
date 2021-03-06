package com.theinventor.quizappudacity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    //used to open the app's info page
    private final String packageName = "com.theinventor.quizappudacity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button app_info = findViewById(R.id.app_info);

        TextView terms = findViewById(R.id.terms_and_conditions_textView);

        //Used to underline the Terms and Conditions TextView
        SpannableString content = new SpannableString(getText(R.string.terms_and_conditions));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        terms.setText(content);
        //Called when app info textView is clicked
        app_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //Open the specific App Info page:
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + packageName));
                    startActivity(intent);

                } catch (ActivityNotFoundException e) {
                    //Open the generic Apps page:
                    Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    startActivity(intent);
                }
            }
        });
    }

    //Open the TermsAndConditionsActivity
    public void termsAndConditions(View view) {
        Intent termsIntent = new Intent(getApplicationContext(), TermsAndConditionsActivity.class);
        startActivity(termsIntent);
    }
}
