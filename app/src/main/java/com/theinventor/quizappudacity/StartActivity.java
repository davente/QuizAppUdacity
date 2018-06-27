package com.theinventor.quizappudacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    private EditText nameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        nameField = findViewById(R.id.name_field);

    }

    public void start(View view) {
        String name = nameField.getText().toString();
        if (name.isEmpty()) {
            nameField.setError("This field is required");
            return;
        }

        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
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
