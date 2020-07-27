package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoNewVideoActivity extends AppCompatActivity {

    //Declare Buttons
    Button submitBtn;

    //Declare Views
    TextView intro;
    Spinner subjectSpinner;
    EditText title;
    EditText url;

    //Declare Variables
    private String titleString;
    private String urlString;
    private int subjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_new_video);

        //Init Buttons
        submitBtn = findViewById(R.id.btn_newVideo_submit);

        //Init Views
        intro = findViewById(R.id.tv_newVideo_intro);
        subjectSpinner = findViewById(R.id.spinner_newVideo);
        loadSubjects();
        title = findViewById(R.id.et_newVideo_title);
        url = findViewById(R.id.et_newVideo_url);


        //Submit Button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleString(title.getText().toString());
                setUrlString(extractYTId(url.getText().toString())); //Extract video ID and save it
                Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem(); //temp holder for subject string
                setSubjectId(selectedSubject.getId()); //convert subject string to int



                //Create video object from inputs and add to collection
                Video video = new Video(getTitleString(), getUrlString(), getSubjectId());
                QuizDbHelper.getInstance(VideoNewVideoActivity.this).addVideo(video);

                //Inform user of success.
                Toast.makeText(VideoNewVideoActivity.this, "Video added to collection: " + subjectSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                //Clear TextViews
                title.setText("");
                url.setText("");
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

    //Takes a full youtube video address and returns the youtube video id as a string.
    //Taken from https://stackoverflow.com/questions/24048308/how-to-get-the-video-id-from-a-youtube-url-with-regex-in-java
    public static String extractYTId(String ytUrl) {
        String videoID = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            videoID = matcher.group(1);
        }
        return videoID;
    }

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
