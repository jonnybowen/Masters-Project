package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity_FlashCard_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__flash__card__menu);

        // Initialise Buttons
        ImageButton NewCollection = (ImageButton) findViewById(R.id.btn_new_collection);
        ImageButton viewCollections = (ImageButton) findViewById(R.id.btn_view_collections);


        /**
         * The New Collection button. Takes the user to the 'Create new flash-card collection' feature.
         */
        NewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewCollectionIntent = new Intent(getApplicationContext(), Activity_FlashCard_New_Collection.class);
                startActivity(NewCollectionIntent);

            }
        });

        /**
         * The view collections button. Takes the user to the collection viewing screen.
         */
        viewCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCollectionIntent = new Intent(getApplicationContext(), Activity_FlashCard_View_All_Collections.class);
                startActivity(viewCollectionIntent);
            }
        });

    }
}
