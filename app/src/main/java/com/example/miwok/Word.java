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

    /** Audio resource ID for the word */
    private int mAudioResourceId;

    /**
     * Constructor
     *
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a lang that the user is familiar with (such as English)
     * @param miwokTranslation is the word in Miwok Language
     * @param audioResourceId is the resource ID for the audio file associated with this word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Constructor
     *
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a lang that the user is familiar with (such as English)
     * @param miwokTranslation is the word in Miwok Language
     * @param imageResourceId is the drawable resource ID for the image associated
     * @param audioResourceId is the resource ID for the audio file associated with this word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
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

    /**
     *
     * Returns the audio resource ID of the word.
     */
    public int getAudioResourceId()
    {
        return mAudioResourceId;
    }

    /**
     * Shortcut used to generate toString() method is Alt+Insert
     * Returns the representation of the Word object.
     * The method concatenates a bunch of variables and text, in order to print out all the variables in Word class as a String.
     * The return value of method is a single string.
     *
     * Then whenever we have a Word object, such as within the onItemClick() method of the OnItemClickListener,
     * we can print out the Word object to the logs.
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
