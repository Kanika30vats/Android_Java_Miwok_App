package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * Since we need to pass in this listener object every time we request audio focus,
     * so we have declared it as global variable (so that we can use the same each time).
     *
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange)
        {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        // This helps to get reference to AudioManger system service.
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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
        // words is declared final because we are using it in anonymous class OnItemClickListener.
        final ArrayList<Word> words = new ArrayList<Word>();

        //        Word words = new Word("one", "lutti");
        //        words.add(w);
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "toloookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));




        // Using ListView for ViewRecycling
        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, words);
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        // Passed in OnItemClickListener object as an argument in setOnItemClickListener method.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                Log.v("NumbersActivity", "Current word: " + word);
                /**
                 * If you concatenate (with the “+” operator) a string with a Word object,
                 * then Java will implicitly call the toString() method on the object.
                 * Hence, the above statement is equivalent to the following.
                 * Log.v("NumbersActivity", "CurrentWord: " + word.toString());
                 */

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                // This method returns an integer.
                // The int return type may be AUDIOFOCUS_REQUEST_FAILED or AUDIOFOCUS_REQUEST_GRANTED
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated with the current word.
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start(); //no need to call prepare(); create() does that for you.

                    // Toast msg displayed to test the working.
                    // Toast.makeText(NumbersActivity.this, "List Item Clicked", Toast.LENGTH_SHORT).show();
                    // Output of makeText method would be a new Toast object. So, we can call a show method on that.

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        /**
         * Whenever we override the activity lifecycle method, we should always call super class version of that method.
         * Because the activity knows how to handle stopping the other activity & doing other behind the scenes cleanup work that we as developer don't need to worry about.
         * If this line is commented, then app will crash unexpectedly.
         */
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        /**
         * Instead of calling release() method on MediaPlayer object,
         * releaseMediaPlayer() method is called because with release() we can set MediaPlayer object back to null if it is currently not configured to play some file.
         */
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            //
            // The onCompletion() and onStop() both are calling releaseMediaPlayer().
            // Hence we called abandonAudioFocus here in releaseMediaPlayer() method.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}