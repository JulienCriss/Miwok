package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the mMediaPlayer has completed playing the audio
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // release the mMediaPlayer resource
            FamilyActivity.this.releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create an ArrayList with the words in default translation and Miwok translation
        final ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("father", "әpә",
                R.drawable.family_father, R.raw.family_father));
        wordsArray.add(new Word("mother", "әṭa",
                R.drawable.family_mother, R.raw.family_mother));
        wordsArray.add(new Word("son", "angsi",
                R.drawable.family_son, R.raw.family_son));
        wordsArray.add(new Word("daughter", "tune",
                R.drawable.family_daughter, R.raw.family_daughter));
        wordsArray.add(new Word("older brother", "taachi",
                R.drawable.family_older_brother, R.raw.family_older_brother));
        wordsArray.add(new Word("younger brother", "chalitti",
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        wordsArray.add(new Word("older sister", "teṭe",
                R.drawable.family_older_sister, R.raw.family_older_sister));
        wordsArray.add(new Word("younger sister", "kolliti",
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        wordsArray.add(new Word("grandmother", "ama",
                R.drawable.family_grandmother, R.raw.family_grandmother));
        wordsArray.add(new Word("grandfather", "paapa",
                R.drawable.family_grandfather, R.raw.family_grandfather));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray, R.color.category_family);

        // Get the ListView from our app
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
                FamilyActivity.this.releaseMediaPlayer();
                // Create and setup the mMediaPlayer for the audio resource associated with the
                // current word

                FamilyActivity.this.mMediaPlayer = MediaPlayer.create(FamilyActivity.this,
                        audioFile);
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release
                // the media player once the sound has finish
                mMediaPlayer.setOnCompletionListener(FamilyActivity.this.mCompletionListener);
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
