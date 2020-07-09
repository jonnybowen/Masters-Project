package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_FlashCard_New_Collection extends AppCompatActivity {

    //Declare Buttons
    private ImageButton createCollection;
    //Declare Views
    private EditText newCollectionName;
    //Declare Variables
    private String collectionName;
    //Declare Database Helper
    DatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__flash__card__new__collection);

        //Initialise Variables


        //Initialise Buttons
        createCollection = (ImageButton) findViewById(R.id.btn_new_collection_create);

        //Initialise Views
        newCollectionName = (EditText) findViewById(R.id.input_new_collection_name);

        //Initialise Database helper
        myDatabaseHelper = new DatabaseHelper(this);


        /**
         * The confirm button for the create new collection screen. Creates a new collection object
         * using the text from the 'name' input on this activity.
         */
        createCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Checks field is not empty first. Generate a new string and assign it to the user's input string
                //  to pass to the FlashCard Collection Constructor.

                if (newCollectionName.length() != 0) { // success path
                    setCollectionName(newCollectionName.getText().toString());
                    showToast(getCollectionName() + " has been added to the collection.");
                    addData(getCollectionName());
                    setCollectionName("");
                    Intent intent = new Intent(Activity_FlashCard_New_Collection.this, Activity_FlashCard_View_All_Collections.class);
                    startActivity(intent);
                } else { // failure path
                    showToast("You cannot leave the name field blank.");
                }

            }
        });
    }


    //Accessor Methods
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String newName) {
        collectionName = newName;
    }

    /**
     * A method to add data to the database.
     */
    public void addData(String newData) {
        boolean insertData = myDatabaseHelper.addData(newData);

        if (insertData) {
            showToast("Data inserted.");
        } else {
            showToast("Data insertion unsuccessful.");
        }

    }


    /**
     * A method to show a 'toast' message. Confirms creation of the new collection.
     *
     * @param text - the user's input for the collection's name.
     */
    public void showToast(String text) {
        Toast.makeText(Activity_FlashCard_New_Collection.this, text, Toast.LENGTH_SHORT).show();
    }
}
