package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The 'home' screen of the app. Acts as a launcher to access features of the app.
 *
 * @author jonathanbowen
 */
public class MainActivity extends AppCompatActivity {

    //Declare Buttons
    Button quizButton;
    Button subjectsButton;
    Button flashcardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Buttons
        quizButton = (Button) findViewById(R.id.btn_menu_quiz);
        subjectsButton = (Button) findViewById(R.id.btn_menu_Subjects);
        flashcardsButton = (Button) findViewById(R.id.btn_menu_flashcards);


        /**
         * The quiz button. Takes the user to the quiz feature when clicked.
         */
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        subjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubjectsMenuActivity.class);
                startActivity(intent);
            }
        });

        flashcardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlashcardMenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void startQuiz() {
        //Create intent for quiz activity and start it.
        Intent quizIntent = new Intent(MainActivity.this, QuizMenuActivity.class);
        startActivity(quizIntent);
    }
}
