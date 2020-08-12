package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * The 'home' screen of the app. Acts as a launcher to access features of the app.
 *
 * @author jonathanbowen
 */
public class MainActivity extends AppCompatActivity {

    //Declare Buttons
    ImageButton quizButton;
    ImageButton subjectsButton;
    ImageButton flashcardsButton;
    ImageButton videosButton;
    ImageButton notesButton;
    ImageButton settingsButton;

    /**
     * onCreate - Initialises UI and applies logic to buttons.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Buttons
        quizButton = (ImageButton) findViewById(R.id.imgbtn_menu_quiz);
        subjectsButton = (ImageButton) findViewById(R.id.imgbtn_menu_subjects);
        flashcardsButton = (ImageButton) findViewById(R.id.imgbtn_menu_flashcards);
        videosButton = (ImageButton) findViewById(R.id.imgbtn_menu_videos);
        notesButton = (ImageButton) findViewById(R.id.imgbtn_menu_notes);
        settingsButton = (ImageButton) findViewById(R.id.imgbtn_menu_settings);


        //The quiz button. Takes the user to the quiz feature when clicked.
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(MainActivity.this, QuizMenuActivity.class);
                startActivity(quizIntent);
            }
        });

        //The subjects button. Takes the user to the subjects menu when clicked
        subjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubjectsMenuActivity.class);
                startActivity(intent);
            }
        });

        //The flashcards button. Takes the user to the flashcards menu when clicked
        flashcardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlashcardMenuActivity.class);
                startActivity(intent);
            }
        });

        //The video button. Takes the user to the video menu when clicked
        videosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoMenuActivity.class);
                startActivity(intent);
            }
        });

        //The notes button. Takes the user to the notes menu when clicked
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteMenuActivity.class);
                startActivity(intent);
            }
        });

    }


}
