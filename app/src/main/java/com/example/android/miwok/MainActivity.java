/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        this.setListenerNumbersActivity();
        this.setListenerColorsActivity();
        this.setListenerFamilyActivity();
        this.setListenerPhrasesActivity();

    }

    /**
     * Set listener for numbers activity
     */
    private void setListenerNumbersActivity() {
        TextView numbersView = findViewById(R.id.numbers);
        // Register the onClick listener with the implementation in line
        numbersView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(view.getContext(), NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
    }

    /**
     * Set listener for colors activity
     */
    private void setListenerColorsActivity() {
        TextView colorsView = findViewById(R.id.colors);

        colorsView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(view.getContext(), ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });
    }

    /**
     * Set listener for family activity
     */
    private void setListenerFamilyActivity() {
        TextView familyVIew = findViewById(R.id.family);

        familyVIew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(view.getContext(), FamilyActivity.class);
                startActivity(familyIntent);
            }
        });
    }

    /**
     * Set listener for phrases activity
     */
    private void setListenerPhrasesActivity() {
        TextView phrasesView = findViewById(R.id.phrases);
        phrasesView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(view.getContext(), PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }
}
