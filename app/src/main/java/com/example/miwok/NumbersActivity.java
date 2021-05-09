package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //            // Creating array of words
        //            String[] words = new String[10];
        //            words[0] = "one";
        //            words[1] = "two";
        //            and so on till words[9]
        //
        //            // Verify the contents of the array by printing out array elements to the logs
        //            Log.v("NumbersActivity", "Word at index 0: " + words[0]);
        //            Log.v("NumbersActivity", "Word at index 1: " + words[1]);

        // Creating ArrayList
        //        ArrayList<String> words = new ArrayList<String>();
        //        words.add("one");
        //        words.add("two");
        //        words.add("three");
        //        words.add("four");
        //        words.add("five");
        //        words.add("six");
        //        words.add("seven");
        //        words.add("eight");
        //        words.add("nine");
        //        words.add("ten");

        //        //            Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
        //        //            Log.v("NumbersActivity", "Word at index 1: " + words.get(1));
        //
        //        // ADDING TEXTVIEW TO LAYOUT WITHOUT LOOP
        //        // This line finds the LinearLayout called rootView
        //        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //        // Every time we declare a View in java code, we need to pass the context
        //        // so that the view knows the theme and other information about the app.
        //        TextView wordView = new TextView(this); // this refers to the context NumbersActivity class.
        //        wordView.setText(words.get(0));
        //        // Add view as child to the parent view called rootView
        //        rootView.addView(wordView);

        //                // ADDING TEXTVIEW TO LAYOUT USING LOOP
        //                // Find the root view of the whole layout
        //                LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //
        //                int index = 0;
        //                while(index<words.size())
        //                {
        //                    // Create a new {@link TextView} that displayed the word and add the View as a child to the rootView
        //                    TextView wordView = new TextView(this); // explanation of 'this' is written above in comment
        //                    wordView.setText(words.get(index));
        //                    rootView.addView(wordView);
        //
        //                    index++;
        //                }

        // ArrayList containing Word objects.
        ArrayList<Word> words = new ArrayList<Word>();

        //        Word words = new Word("one", "lutti");
        //        words.add(w);
        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "toloookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five));
        words.add(new Word("six", "temmokka",R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten));




        // Using ListView for ViewRecycling
        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}