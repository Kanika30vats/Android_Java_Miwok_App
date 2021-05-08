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
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of AndroidFlavor objects to display in a list
     *
     */
      public WordAdapter(Activity context, ArrayList<Word> words)
      {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            // 0 is passed as resource id because we don't need to rely on superclass ArrayAdapter inflating or creating a List Item view for us.
            //getView method will manually handle the inflating.
            super(context, 0, words);
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
        // Set the ImageView to the image resource specified in the current Word.
        imageView.setImageResource(currentWord.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews )
        // so that it can be shown in the ListView
        return listItemView;

    }
}
