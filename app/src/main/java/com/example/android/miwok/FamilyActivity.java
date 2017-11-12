package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create an ArrayList with the words in default translation and Miwok translation
        ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("father", "әpә"));
        wordsArray.add(new Word("mother", "әṭa"));
        wordsArray.add(new Word("son", "angsi"));
        wordsArray.add(new Word("daughter", "tune"));
        wordsArray.add(new Word("older brother", "taachi"));
        wordsArray.add(new Word("younger brother", "chalitti"));
        wordsArray.add(new Word("older sister", "teṭe"));
        wordsArray.add(new Word("younger sister", "kolliti"));
        wordsArray.add(new Word("grandmother", "ama"));
        wordsArray.add(new Word("grandfather", "paapa"));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray);

        // Get the ListView from our app
        ListView listView = findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);
    }
}
