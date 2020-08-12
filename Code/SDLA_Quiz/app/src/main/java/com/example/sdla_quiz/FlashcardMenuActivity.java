package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

/**
 * This activity class allows users to launch a flashcard related activity.
 * Users may create a new card, view existing cards, or delete cards.
 */
public class FlashcardMenuActivity extends AppCompatActivity {

    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    // Declare UI
    Spinner subjectSpinner;
    Button createBtn;
    Button viewBtn;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_menu);

        // Init UI
        subjectSpinner = findViewById(R.id.spinner_flashcardMenu_subject);
        createBtn = findViewById(R.id.btn_flashcardMenu_create);
        viewBtn = findViewById(R.id.btn_flashcardMenu_view);
        deleteBtn = findViewById(R.id.btn_flashcardMenu_delete);

        loadSubjects();


        //  The view button
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginViewing();
            }
        });


        // The create button
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlashcardMenuActivity.this, FlashcardNewFlashcardActivity.class);
                startActivity(intent);
            }
        });

        // The Delete button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginDeleting(); // call custom function to start next activity
            }
        });


    }

    /**
     * Loads a list of all available subjects into a spinner (UI).
     */
    private void loadSubjects() {
        DbHelper dbHelper = DbHelper.getInstance(this);
        List<Subject> subjects = dbHelper.getAllSubjects();
        ArrayAdapter<Subject> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapterSubjects);
    }

    /**
     * Launches the 'view flashcard' activity for chosen subject. Pass relevant extras.
     */
    private void beginViewing() {
        //Retrieve subject from spinner
        Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem();
        int subjectId = selectedSubject.getId();

        Intent intent = new Intent(FlashcardMenuActivity.this, FlashcardViewFlashcardActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);
    }

    /**
     * Launches the 'delete flashcards' activity for chosen subject. Pass relevant extras.
     */
    private void beginDeleting() {
        //Retrieve subject from spinner
        Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem();
        int subjectId = selectedSubject.getId();

        Intent intent = new Intent(FlashcardMenuActivity.this, FlashcardDeleteFlashcardActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);

    }
}
