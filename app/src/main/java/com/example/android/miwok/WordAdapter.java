package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julien on 12.11.2017.
 * This class that can provide the layout for each list dynamically
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    /**
     * This is our custom constructor
     * The context is used to inflate the layout file, and the List data is the data we want to
     * populate into the lists
     *
     * @param context      The current context. Used to inflate the layout file.
     * @param arrayAdapter A list of Word objects to display in a list
     */
    public WordAdapter(Activity context, ArrayList<Word> arrayAdapter) {
        super(context, 0, arrayAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);
        }

        // Get the Word object located at this position in the list
        Word currentWord = getItem(position);
        assert currentWord != null;

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);

        // Get the default translation from Word object and
        // set this text on the defaultTextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);

        // Get the Miwok translation from Word object and
        // set this text on miwokTextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        return listItemView;
    }
}
