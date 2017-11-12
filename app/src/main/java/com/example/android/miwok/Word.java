package com.example.android.miwok;

/**
 * Created by Julien on 12.11.2017.
 * Word class represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    // Miwok translation for the word (e.g lutti)
    private String mMiwokTranslation;

    // Default translation for the word (e.g one)
    private String mDefaultTranslation;

    /**
     * Create a new word object
     *
     * @param defaultTranslation is the word in a language that user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;

    }

    /**
     * Get the default translation for the word
     *
     * @return A string that represents the default translation of the word
     */
    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    /**
     * Get the Miwok translation for the word
     *
     * @return A string that represents Miwok translation of the word
     */
    public String getMiwokTranslation() {
        return this.mMiwokTranslation;
    }
}
