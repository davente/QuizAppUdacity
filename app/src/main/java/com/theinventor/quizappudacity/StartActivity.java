package com.theinventor.quizappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    //Interval between back clicks to show toast
    private static final int TIME_INTERVAL = 2000;
    private EditText nameField;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        nameField = findViewById(R.id.name_field);
    }

    //Called when the start button is clicked
    public void start(View view) {
        String name = nameField.getText().toString();
        if (name.isEmpty()) {
            nameField.setError("This field is required");
            return;
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("name", name);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);

            // Start AboutActivity activity
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
