package com.example.dandersen.app.mydictionaryproviderexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        TextView dictTextView = (TextView) findViewById(R.id.dictionary_text_view);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        ContentHelper.insertContent(resolver, "Wauuuuv");

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        String txt = "The UserDictionary contains " + cursor.getCount() + " words\n";
        txt += "COLUMNS " + UserDictionary.Words._ID + " - "
                + UserDictionary.Words.LOCALE + "- "
                + UserDictionary.Words.WORD + " - "
                + UserDictionary.Words.FREQUENCY;
        while (cursor.moveToNext()) {
            txt += "\n";
            txt += cursor.getString(cursor.getColumnIndex(UserDictionary.Words._ID)) + " - "
                    + cursor.getString(cursor.getColumnIndex(UserDictionary.Words.LOCALE)) + " - "
                    + cursor.getString(cursor.getColumnIndex(UserDictionary.Words.WORD)) + " - "
                    + cursor.getString(cursor.getColumnIndex(UserDictionary.Words.FREQUENCY));
        }
        dictTextView.setText(txt);

        cursor.close();
    }
}
