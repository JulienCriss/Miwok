package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the mMediaPlayer has completed playing the audio
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // release the mMediaPlayer resource
            PhrasesActivity.this.releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create an ArrayList with the words in default translation and Miwok translation
        final ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("Where are you going?",
                "minto wuksus", R.raw.phrase_where_are_you_going));
        wordsArray.add(new Word("What is your name?",
                "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        wordsArray.add(new Word("My name is...",
                "oyaaset...", R.raw.phrase_my_name_is));
        wordsArray.add(new Word("How are you feeling?",
                "michәksәs?", R.raw.phrase_how_are_you_feeling));
        wordsArray.add(new Word("I’m feeling good.",
                "kuchi achit", R.raw.phrase_im_feeling_good));
        wordsArray.add(new Word("Are you coming?",
                "temmokka", R.raw.phrase_are_you_coming));
        wordsArray.add(new Word("Yes, I’m coming.",
                "әәnәs'aa?", R.raw.phrase_yes_im_coming));
        wordsArray.add(new Word("I’m coming.",
                "hәә’ әәnәm", R.raw.phrase_im_coming));
        wordsArray.add(new Word("Let’s go.", "yoowutis",
                R.raw.phrase_lets_go));
        wordsArray.add(new Word("Come here.", "әnni'nem",
                R.raw.phrase_come_here));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray, R.color.category_phrases);

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
                PhrasesActivity.this.releaseMediaPlayer();
                // Create and setup the mMediaPlayer for the audio resource associated with the
                // current word
                PhrasesActivity.this.mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,
                        audioFile);
                mMediaPlayer.start();
                // Setup a listener on the media player, so that we can stop and release
                // the media player once the sound has finish
                mMediaPlayer.setOnCompletionListener(PhrasesActivity.this.mCompletionListener);
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
