package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    /**
     * Handles playback off all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the mMediaPlayer has completed playing the audio
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // release the mMediaPlayer resource
            FamilyFragment.this.releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager
            .OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                FamilyFragment.this.mMediaPlayer.pause();
                FamilyFragment.this.mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Here we will return resuming playback
                FamilyFragment.this.mMediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means that we've lost audio focus and stop the
                // playback and clean up resources
                FamilyFragment.this.releaseMediaPlayer();
            }

        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        this.mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter adapter = new WordAdapter(getActivity(), wordsArray, R.color.category_family);

        // Get the ListView from our app
        ListView listView = rootView.findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);

        // Attach a listener to play the correct sound for each item whens is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Release the mMediaPlayer resource because we are about to play a different
                // sound file
                FamilyFragment.this.releaseMediaPlayer();

                // Get the word object
                Word currentWord = wordsArray.get(position);
                int audioFile = currentWord.getAudioResourceId();


                // Request audio focus for playback
                int result = FamilyFragment.this.mAudioManager.requestAudioFocus(FamilyFragment
                                .this.mOnAudioFocusChangeListener,
                        // Use the music stream
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // start the playback

                    // Create and setup the mMediaPlayer for the audio resource associated with the
                    // current word
                    FamilyFragment.this.mMediaPlayer = MediaPlayer.create(getActivity(),
                            audioFile);

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release
                    // the media player once the sound has finish
                    mMediaPlayer.setOnCompletionListener(FamilyFragment.this.mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resource because we won't
        // be playing any more sounds
        this.releaseMediaPlayer();
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

            // Abandon audio focus when playback complete
            this.mAudioManager.abandonAudioFocus(this.mOnAudioFocusChangeListener);
        }
    }

}
