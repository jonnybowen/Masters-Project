package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * This activity class allows users to browse and delete subjects from their database.
 * Deleting a subject causes all of its notes, videos, etc to be deleted too.
 */
public class SubjectsDeleteSubjectActivity extends AppCompatActivity {

    //Declare UI
    Spinner spin_subject;
    Button confirmDelete;

    /**
     * onCreate - Initialise UI and set button logic.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_delete_subject);

        confirmDelete = (Button) findViewById(R.id.btn_delSubject_confirm);
        spin_subject = (Spinner) findViewById(R.id.spinner_delSubjects_subjects);
        loadSubjects();

        confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectNameDel = spin_subject.getSelectedItem().toString();
                DbHelper.getInstance(SubjectsDeleteSubjectActivity.this).deleteSubject(subjectNameDel);
                Toast.makeText(SubjectsDeleteSubjectActivity.this, "Subject deleted from database", Toast.LENGTH_SHORT).show();
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
        spin_subject.setAdapter(adapterSubjects);
    }
}
