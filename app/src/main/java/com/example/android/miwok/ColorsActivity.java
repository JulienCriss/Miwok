package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the mMediaPlayer has completed playing the audio
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // release the mMediaPlayer resource
            ColorsActivity.this.releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("red", "weṭeṭṭi",
                R.drawable.color_red, R.raw.color_red));
        wordsArray.add(new Word("green", "chokokki",
                R.drawable.color_green, R.raw.color_green));
        wordsArray.add(new Word("brown", "ṭakaakki",
                R.drawable.color_brown, R.raw.color_brown));
        wordsArray.add(new Word("gray", "ṭopoppi",
                R.drawable.color_gray, R.raw.color_gray));
        wordsArray.add(new Word("black", "kululli",
                R.drawable.color_black, R.raw.color_black));
        wordsArray.add(new Word("white", "kelelli",
                R.drawable.color_white, R.raw.color_white));
        wordsArray.add(new Word("dusty yellow", "ṭopiisә",
                R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        wordsArray.add(new Word("mustard yellow", "chiwiiṭә",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray, R.color.category_colors);

        // Get the List view from our app
        ListView listView = findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);

        // Attach a listener to play the correct sound for each item whens is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word currentWord = wordsArray.get(position);
                int audioFile = currentWord.getAudioResourceId();

                // Release the mMediaPlayer resource because we are about to play a different
                // sound file
                ColorsActivity.this.releaseMediaPlayer();
                // Create and setup the mMediaPlayer for the audio resource associated with the
                // current word
                ColorsActivity.this.mMediaPlayer = MediaPlayer.create(ColorsActivity.this,
                        audioFile);
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release
                // the media player once the sound has finish
                mMediaPlayer.setOnCompletionListener(ColorsActivity.this.mCompletionListener);
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (this.mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            this.mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            this.mMediaPlayer = null;
        }
    }
}
