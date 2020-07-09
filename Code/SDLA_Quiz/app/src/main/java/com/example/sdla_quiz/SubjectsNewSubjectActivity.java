package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubjectsNewSubjectActivity extends AppCompatActivity {

    //Declare Buttons
    Button confirmButton;

    //Declare TextViews
    EditText subjectNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_new_subject);

        //Init Buttons
        confirmButton = (Button) findViewById(R.id.btn_newSubject_confirm);

        //Init Textviews
        subjectNameEditText = (EditText) findViewById(R.id.et_newSubject_name);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectNameNew;
                subjectNameNew = subjectNameEditText.getText().toString();

                Subject subject = new Subject(subjectNameNew);
                QuizDbHelper.getInstance(SubjectsNewSubjectActivity.this).addSubject(subject);
                Toast.makeText(SubjectsNewSubjectActivity.this, "Subject added to database", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
