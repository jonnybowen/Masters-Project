package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare Subjects ArrayList -- REPLACE WITH A LOAD LIST FUNCTION
    ArrayList<String> subjectList;

    // Declare Button
    private Button createFlashcardButton;
    private Button createSubjectButton;

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList<String> subjectList) {
        this.subjectList = subjectList;
    }

    //On-Create Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Subject List -- REPLACE WITH A LOAD LIST FUNCTION
         subjectList = new ArrayList<>();

        //Initialise Button
        createFlashcardButton = (Button) findViewById(R.id.btn_menu_newFlashCard);
        createSubjectButton = (Button) findViewById(R.id.btn_menu_newSubject);


        //Set Flashcard Button Functionality
        createFlashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createFlashcardIntent = new Intent(MainActivity.this, CreateFlashcardActivity.class);
                startActivity(createFlashcardIntent);
            }
        });

        //Set Subject Button Functionality
        createSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createSubjectIntent = new Intent(MainActivity.this, CreateSubjectActivity.class);
                startActivity(createSubjectIntent);
            }
        });

    }


    //Accessor Methods
    //----------------

    public Button getCreateFlashcardButton() {
        return createFlashcardButton;
    }

    public void setCreateFlashcardButton(Button createFlashcardButton) {
        this.createFlashcardButton = createFlashcardButton;
    }

    //----------------
    //End of Accessor Methods
}
