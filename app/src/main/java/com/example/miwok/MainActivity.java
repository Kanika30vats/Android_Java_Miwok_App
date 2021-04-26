package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);

    }


    public void openNumbersList(View view)
    {
        Intent i = new Intent(MainActivity.this, NumbersActivity.class);
        startActivity(i);
    }
}