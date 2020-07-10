package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class FlashcardMenuActivity extends AppCompatActivity {

    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";


    Spinner subjectSpinner;
    Button createBtn;
    Button viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_menu);

        subjectSpinner = findViewById(R.id.spinner_flashcardMenu_subject);
        createBtn = findViewById(R.id.btn_flashcardMenu_create);
        viewBtn = findViewById(R.id.btn_flashcardMenu_view);

        loadSubjects();

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginViewing();
            }
        });

    }

    private void loadSubjects() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Subject> subjects = dbHelper.getAllSubjects();
        ArrayAdapter<Subject> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapterSubjects);
    }

    private void beginViewing(){
        //Retrieve subject from spinner
        Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem();
        int subjectId = selectedSubject.getId();

        Intent intent = new Intent(FlashcardMenuActivity.this, FlashcardViewFlashcardActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);
    }
}
