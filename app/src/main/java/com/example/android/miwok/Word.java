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

    // Image ID resource that will be displayed in GUI
    private int mImageResourceId;

    // Audio resource id
    private int mAudioResourceId;

    // Set this flag in order to know if a Word object has an image assigned or not
    private boolean HAS_IMAGE;

    /**
     * Create a new word object
     *
     * @param defaultTranslation is the word in a language that user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in Miwok language
     * @param audioResourceId    Audio resource id
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mAudioResourceId = audioResourceId;
        this.HAS_IMAGE = false;

    }

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is the word in a language that user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in Miwok language
     * @param imageResourceId    is the resource ID of image that we will display to user
     * @param audioResourceId    is the resource ID of audio file
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int
            audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
        // set the flag
        this.HAS_IMAGE = true;
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

    /**
     * Get image resource ID
     *
     * @return An integer that represent image resource ID
     */
    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    /**
     * Get audio resource id
     *
     * @return An integer that represent audio resource id
     */
    public int getAudioResourceId() {
        return this.mAudioResourceId;
    }

    /**
     * Return whether or not there is an image for this word
     *
     * @return Boolean value
     */
    public boolean hasImage() {
        return this.HAS_IMAGE;
    }
}
