package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_FlashCard_View_All_Collections extends AppCompatActivity {

    private static final String TAG = "FCViewCollectionActiv.";

    DatabaseHelper myDatabaseHelper;

    private ListView flashcardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__flash_card__view__collections);
        flashcardListView = (ListView) findViewById(R.id.fcListView);
        myDatabaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    /**
     * A method to populate the listview with data.
     */
    public void populateListView() {
        Log.d(TAG, "populateListView: Displaying data");

        Cursor data  = myDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            // get values from the database in column 1
            // then add to the arrayList
            listData.add(data.getString(1));
        }
        //create and set the list adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        flashcardListView.setAdapter(adapter);

        //create onItemClickListener and assign to the listview
        flashcardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: clicked: " +name);

                Cursor data = myDatabaseHelper.getItemID(name); // get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if(itemID>-1) {
                    Log.d(TAG, "onItemClick: the ID is: " + itemID);
                } else {
                    showToast("No ID associated with that name.");
                }
            }
        });
    }

    /**
     * A method to show a 'toast' message. Confirms creation of the new collection.
     *
     * @param text - the user's input for the collection's name.
     */
    public void showToast(String text) {
        Toast.makeText(Activity_FlashCard_View_All_Collections.this, text, Toast.LENGTH_SHORT).show();
    }
}
