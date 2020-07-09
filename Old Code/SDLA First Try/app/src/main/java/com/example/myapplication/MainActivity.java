package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise Buttons
        ImageButton FlashCardMenu = (ImageButton) findViewById(R.id.btn_main_FlashCard);


        /**
         * The FlashCard Feature button. Takes the user to the FlashCards feature.
         */
        FlashCardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FlashCardIntent = new Intent(getApplicationContext(), Activity_FlashCard_Menu.class);
                startActivity(FlashCardIntent);

            }
        });

    }
}
