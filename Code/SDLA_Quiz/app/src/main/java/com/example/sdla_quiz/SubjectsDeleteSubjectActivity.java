package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SubjectsDeleteSubjectActivity extends AppCompatActivity {

    Spinner spin_subject;
    Button confirmDelete;

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
                QuizDbHelper.getInstance(SubjectsDeleteSubjectActivity.this).deleteSubject(subjectNameDel);
                Toast.makeText(SubjectsDeleteSubjectActivity.this, "Subject deleted from database", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadSubjects() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Subject> subjects = dbHelper.getAllSubjects();
        ArrayAdapter<Subject> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_subject.setAdapter(adapterSubjects);
    }
}
