package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an ArrayList with the words in default translation and Miwok translation

        final ArrayList<Word> wordsArray = new ArrayList<>();
        wordsArray.add(new Word("one", "lutti",
                R.drawable.number_one, R.raw.number_one));
        wordsArray.add(new Word("two", "otiiko",
                R.drawable.number_two, R.raw.number_two));
        wordsArray.add(new Word("three", "tolookosu",
                R.drawable.number_three, R.raw.number_three));
        wordsArray.add(new Word("four", "oyyisa",
                R.drawable.number_four, R.raw.number_four));
        wordsArray.add(new Word("five", "massokka",
                R.drawable.number_five, R.raw.number_five));
        wordsArray.add(new Word("six", "temmokka",
                R.drawable.number_six, R.raw.number_six));
        wordsArray.add(new Word("seven", "kenekaku",
                R.drawable.number_seven, R.raw.number_seven));
        wordsArray.add(new Word("eight", "kawinta",
                R.drawable.number_eight, R.raw.number_eight));
        wordsArray.add(new Word("nine", "wo'e",
                R.drawable.number_nine, R.raw.number_nine));
        wordsArray.add(new Word("ten", "na'aacha",
                R.drawable.number_ten, R.raw.number_ten));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray, R.color.category_numbers);

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
                NumbersActivity.this.mMediaPlayer = MediaPlayer.create(NumbersActivity.this,
                        audioFile);
                mMediaPlayer.start();
            }
        });

    }
}
