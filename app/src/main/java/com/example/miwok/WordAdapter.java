package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

/**
 * This class is created to override the getView method of ArrayAdapter class.
 * This is because the ArrayAdapter class expects that the provided resource id is a single TextView.
 * But we have more than one views in list_item.xml.
 * And hence to use something other than TextViews, we have to use getView(int, View, ViewGroup)
 * To provide R.layout.list_item as the resource id, we have to override getView method.
 */
public class WordAdapter extends ArrayAdapter<Word>
{

    /**
     * Resource ID for the background color to the list of words
     */
    private int mColorResourceId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of AndroidFlavor objects to display in a list
     *
     */
      public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceId)
      {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            // 0 is passed as resource id because we don't need to rely on superclass ArrayAdapter inflating or creating a List Item view for us.
            //getView method will manually handle the inflating.
            super(context, 0, words);
            mColorResourceId = ColorResourceId;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * getView method is called when a ListView is trying to display a list of items at a given position.
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate. ListView will pass in a potential view that we could reuse, which is convertView.
     * @param parent The parent ViewGroup that is used for inflation. Parent ViewGroup (for all list items) which is the ListView itself
     * @return The View for the position in the AdapterView.

     * Use Ctrl+O to override methods of the inherited class.
     * Or go to Code > Override Methods.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link Word} object located at this position in the list.
        // This helps to get Word object at the given position of the list.
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and
        // set this text on the miwokTextView.
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and
        // set this text on the defaultTextView.
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image_view
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.hasImage())
        {
            // Set the ImageView to the image resource specified in the current Word.
            imageView.setImageResource(currentWord.getImageResourceId());

            // Make sure the view is visible.
            // We set this because the views get reused. So, if the view was previously hidden, we want to make sure that its made visible again.
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            // Otherwise hide the ImageView (set visibility to GONE)
            // GONE : View is hidden and doesn't take up any space in our app.
            // INVISIBLE : View is hidden but takes up space in app.
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews )
        // so that it can be shown in the ListView
        return listItemView;

        /**
         * The above line 'return listItemView' is problematic for PhraseActivity.
         * In PhraseActivity, the screen is blank.
         * The WordAdapter assumes each word has an image, but phrases don't have images.
         *
         * 1. Either we can create PhraseAdapter to use it in PhraseActivity and use WordAdapter only in NumbersActivity, ColorsActivity, FamilyActivity.
         *    But this involves a lot of duplicate code.
         * 2. So it's better to slightly modify WordAdapter class and use it in PhraseActivity, NumbersActivity, ColorsActivity, FamilyActivity.
         *    We can hide or show a view in android while the app is running using control flow statements.
         *
         * SOLUTION (To fix PhrasesActivity):
         *  Modify WordAdapter so we use an if/else statement.
         *  If there is an image associated with the Word object, show the ImageView; otherwise hide the ImageView.
         *  In order to determine whether we should hide or show an ImageView in the list item, we can check whether or not a constructor has set an Imageview (in Word.java)
         */

    }
}
