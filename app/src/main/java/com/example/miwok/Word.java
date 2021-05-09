package com.example.miwok;

/**
 * {@Link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */
public class Word
{
    /**
     * Default translation for the word.
     */
    private String mDefaultTranslation;

    /**
     * Miwok translation for the word.
     */
    private String mMiwokTranslation;

    /**
     * Image resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Constructor
     *
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a lang that the user is familiar with (such as English)
     * @param miwokTranslation is the word in Miwok Language
     */
    public Word(String defaultTranslation, String miwokTranslation)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Constructor
     *
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a lang that the user is familiar with (such as English)
     * @param miwokTranslation is the word in Miwok Language
     * @param imageResourceId is the drawable resource ID for the image associated
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get default translation method.
     */
    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    /**
     * Get Miwok translation method.
     */
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId()
    {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     *
     */
    public boolean hasImage()
    {
        // returns true if mImageResource is not equal to No_IMAGE_PROVIDED.
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
