package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity class allows users to create new flashcards.
 */
public class SubjectsNewSubjectActivity extends AppCompatActivity {

    //Declare Buttons
    Button confirmButton;

    //Declare TextViews
    EditText subjectNameEditText;

    /**
     * onCreate - Initialise UI and apply button logic.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_new_subject);

        //Init Buttons
        confirmButton = (Button) findViewById(R.id.btn_newSubject_confirm);

        //Init Textviews
        subjectNameEditText = (EditText) findViewById(R.id.et_newSubject_name);

        //Confirm button - create subject from inputs and save to database.
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectNameNew = subjectNameEditText.getText().toString();

                Subject subject = new Subject(subjectNameNew);
                DbHelper.getInstance(SubjectsNewSubjectActivity.this).insertSubject(subject);
                Toast.makeText(SubjectsNewSubjectActivity.this, "Subject added to database", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }
}
