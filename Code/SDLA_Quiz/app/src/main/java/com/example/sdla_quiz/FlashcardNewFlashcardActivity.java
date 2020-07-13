package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlashcardNewFlashcardActivity extends AppCompatActivity {

    //Declare TextViews
    private EditText etTerm;
    private EditText etDefinition;


    //Declare Spinners
    private Spinner subjectSpinner;

    //Declare Buttons
    private Button addBtn;

    //Declare Variables
    private String term;
    private String definition;
    private int subjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_new_flashcard);

        //Initialise TextViews
        etTerm = findViewById(R.id.et_newFlashcard_term);
        etDefinition = findViewById(R.id.et_newFlashcard_definition);

        //Initialise Buttons
        addBtn = findViewById(R.id.btn_newFlashcard_add);

        //Initialise Spinner
        subjectSpinner = findViewById(R.id.spinner_newFlashcard);

        //Populate Spinners
        loadSubjects();

        //Add Button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gather data from inputs
                setTerm(etTerm.getText().toString());
                setDefinition(etDefinition.getText().toString());
                Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem(); //temp holder for subject string
                setSubjectId(selectedSubject.getId()); //convert subject string to int

                //Create flashcard from inputs and add to collection
                Flashcard f = new Flashcard(getTerm(), getDefinition(), getSubjectId());
                QuizDbHelper.getInstance(FlashcardNewFlashcardActivity.this).addFlashcard(f);

                //Inform user of success.
                Toast.makeText(FlashcardNewFlashcardActivity.this, "Flashcard added to collection: " + subjectSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                //Clear TextViews
                etTerm.setText("");
                etDefinition.setText("");
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


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
