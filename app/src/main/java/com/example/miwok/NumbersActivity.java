package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //            // Creating array of words
        //            String[] words = new String[10];
        //            words[0] = "one";
        //            words[1] = "two";
        //            words[2] = "three";
        //            words[3] = "four";
        //            words[4] = "five";
        //            words[5] = "six";
        //            words[6] = "seven";
        //            words[7] = "eight";
        //            words[8] = "nine";
        //            words[9] = "ten";
        //
        //
        //            // Verify the contents of the array by printing out array elements to the logs
        //            Log.v("NumbersActivity", "Word at index 0: " + words[0]);
        //            Log.v("NumbersActivity", "Word at index 1: " + words[1]);

        // Creating ArrayList
        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");

        //            Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
        //            Log.v("NumbersActivity", "Word at index 1: " + words.get(1));

        // This line finds the LinearLayout called rootView
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        // Every time we declare a View in java code, we need to pass the context
        // so that the view knows the theme and other information about the app.
        TextView wordView = new TextView(this); // this refers to the context NumbersActivity class.
        wordView.setText(words.get(0));
        // Add view as child to the parent view called rootView
        rootView.addView(wordView);

    }
}