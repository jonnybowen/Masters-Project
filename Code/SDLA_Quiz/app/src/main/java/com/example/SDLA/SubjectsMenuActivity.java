package com.example.SDLA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This activity class allows users to launch a Subject related activity.
 * Users may create a new subject, or delete existing ones.
 */
public class SubjectsMenuActivity extends AppCompatActivity {

    //Declare Buttons
    Button newSubjectBtn;
    Button delSubjectBtn;

    /**
     * OnCreate - Initialise UI and apply button logic.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_menu);

        //Initialise Buttons
        newSubjectBtn = findViewById(R.id.btn_subjectsMenu_newSubject);
        delSubjectBtn = findViewById(R.id.btn_subjectsMenu_deleteSubject);


        //New subject Button - take user to new subject activity
        newSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectsMenuActivity.this, SubjectsNewSubjectActivity.class);
                startActivity(intent);
            }
        });

        //Delete subject Button - take user to delete subject activity
        delSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectsMenuActivity.this, SubjectsDeleteSubjectActivity.class);
                startActivity(intent);
            }
        });
    }
}
