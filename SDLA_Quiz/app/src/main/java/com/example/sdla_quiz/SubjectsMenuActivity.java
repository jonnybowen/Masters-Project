package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectsMenuActivity extends AppCompatActivity {

    //Declare Buttons
    Button newSubjectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_menu);

        //Initialise Buttons
        newSubjectBtn = findViewById(R.id.btn_subjectsMenu_newSubject);


        newSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectsMenuActivity.this, SubjectsNewSubjectActivity.class);
                startActivity(intent);
            }
        });
    }
}
