package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * This activity class allows users to create new flashcards.
 */
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

    /**
     * onCreate - Loads UI and applies relevant logic to buttons.
     *
     * @param savedInstanceState
     */
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
                setSubjectId(selectedSubject.getId()); //convert subject string to int and store

                //Create flashcard from inputs and add to collection
                Flashcard f = new Flashcard(getTerm(), getDefinition(), getSubjectId());
                DbHelper.getInstance(FlashcardNewFlashcardActivity.this).insertFlashcard(f);

                //Inform user of success.
                Toast.makeText(FlashcardNewFlashcardActivity.this, "Flashcard added to collection: " + subjectSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                //Clear TextViews
                etTerm.setText("");
                etDefinition.setText("");
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
     * Accessor method to get the term
     *
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Accessor method to set the term
     *
     * @param term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Accessor method to get the definition
     *
     * @return
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Accessor method to set the definition
     *
     * @param definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Accessor method to get the subject id
     *
     * @return subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectId
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
