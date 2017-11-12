package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> wordsArray = new ArrayList<Word>();

        wordsArray.add(new Word("red", "weṭeṭṭi"));
        wordsArray.add(new Word("green", "chokokki"));
        wordsArray.add(new Word("brown", "ṭakaakki"));
        wordsArray.add(new Word("gray", "ṭopoppi"));
        wordsArray.add(new Word("black", "kululli"));
        wordsArray.add(new Word("white", "kelelli"));
        wordsArray.add(new Word("dusty yellow", "ṭopiisә"));
        wordsArray.add(new Word("mustard yellow", "chiwiiṭә"));

        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray);

        // Get the List view from our app
        ListView listView = findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);

    }
}
