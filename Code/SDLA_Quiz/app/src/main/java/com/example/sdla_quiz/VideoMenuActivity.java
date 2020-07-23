package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class VideoMenuActivity extends AppCompatActivity {

    //Declare Constants
    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    //Declare Buttons
    Button addBtn;
    Button editBtn;
    Button browseBtn;

    //Declare Views
    TextView intro;
    Spinner subjectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_menu);

        //Init Buttons
        addBtn = (Button) findViewById(R.id.btn_videoMenu_add);
        //editBtn = (Button) findViewById(R.id.btn_videoMenu_edit);
        browseBtn = (Button) findViewById(R.id.btn_videoMenu_browse);

        //Init Views
        intro = (TextView) findViewById(R.id.tv_videoMenu_intro);
        subjectSpinner = (Spinner) findViewById(R.id.spinner_videoMenu);
        loadSubjects(); // populate spinner with subjects


        //Browse Button
        browseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseVideos();
            }
        });

        //Add button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoMenuActivity.this, VideoNewVideoActivity.class);
                startActivity(intent);
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

    private void browseVideos() {
        //Retrieve subject from spinner
        Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem();
        int subjectId = selectedSubject.getId();

        Intent intent = new Intent(VideoMenuActivity.this, VideoBrowseVideosActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);
    }
}
