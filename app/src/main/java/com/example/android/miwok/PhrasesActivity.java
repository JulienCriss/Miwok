package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create an ArrayList with the words in default translation and Miwok translation
        ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("Where are you going?",
                "minto wuksus"));
        wordsArray.add(new Word("What is your name?",
                "tinnә oyaase'nә"));
        wordsArray.add(new Word("My name is...",
                "oyaaset..."));
        wordsArray.add(new Word("How are you feeling?",
                "michәksәs?"));
        wordsArray.add(new Word("I’m feeling good.",
                "kuchi achit"));
        wordsArray.add(new Word("Are you coming?",
                "temmokka"));
        wordsArray.add(new Word("Yes, I’m coming.",
                "әәnәs'aa?"));
        wordsArray.add(new Word("I’m coming.",
                "hәә’ әәnәm"));
        wordsArray.add(new Word("Let’s go.", "yoowutis"));
        wordsArray.add(new Word("Come here.", "әnni'nem"));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray);

        // Get the ListView from our app
        ListView listView = findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);

    }
}
