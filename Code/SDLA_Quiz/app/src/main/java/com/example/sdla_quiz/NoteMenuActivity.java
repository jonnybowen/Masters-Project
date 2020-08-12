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
 * This activity class allows users to launch a note related activity.
 * Users may select options to browse existing notes or delete notes.
 */
public class NoteMenuActivity extends AppCompatActivity {

    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    //Declare views
    Spinner subjectSpinner;

    //Declare Buttons
    Button viewBtn;

    /**
     * onCreate - intialise UI and button logic.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_menu);


        viewBtn = findViewById(R.id.imgbtn_notes_browse);
        subjectSpinner = findViewById(R.id.noteMenu_subjectSpinner);
        loadSubjects();

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startViewing();
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
     * Launches the 'browse notes' activity for chosen subject. Pass relevant extras.
     */
    private void startViewing() {
        //Retrieve subject from spinner
        Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem();
        int subjectId = selectedSubject.getId();

        //Start new activity and pass retrieved subject
        Intent intent = new Intent(NoteMenuActivity.this, NoteBrowseNotesActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);
    }

}
