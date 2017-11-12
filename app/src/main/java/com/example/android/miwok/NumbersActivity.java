package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create an ArrayList with the words in default translation and Miwok translation
        ArrayList<Word> wordsArray = new ArrayList<>();

        wordsArray.add(new Word("one", "lutti"));
        wordsArray.add(new Word("two", "otiiko"));
        wordsArray.add(new Word("three", "tolookosu"));
        wordsArray.add(new Word("four", "oyyisa"));
        wordsArray.add(new Word("five", "massokka"));
        wordsArray.add(new Word("six", "temmokka"));
        wordsArray.add(new Word("seven", "kenekaku"));
        wordsArray.add(new Word("eight", "kawinta"));
        wordsArray.add(new Word("nine", "wo'e"));
        wordsArray.add(new Word("ten", "na'aacha"));


        // Create an WordAdapter in oder to use view recycling to optimize memory
        WordAdapter adapter = new WordAdapter(this, wordsArray);

        // Get the List view from our app
        ListView listView = findViewById(R.id.list);

        // Attach the adapter to our ListView
        listView.setAdapter(adapter);

    }
}
