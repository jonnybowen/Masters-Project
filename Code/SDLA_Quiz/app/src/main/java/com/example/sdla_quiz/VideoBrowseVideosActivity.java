package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class VideoBrowseVideosActivity extends AppCompatActivity {

    //Declare Views
    RecyclerView recyclerView;
    TextView introText;

    //Declare Adapter
    private VideoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_browse_videos);


        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(VideoMenuActivity.EXTRA_SUBJECT_ID, 0);

        //Initialise Views
        introText = findViewById(R.id.tv_browseVideos_intro);
        recyclerView = findViewById(R.id.recyclerView_browseVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VideoAdapter(this, QuizDbHelper.getInstance(VideoBrowseVideosActivity.this).getVideosCursor(subjectId));
        recyclerView.setAdapter(mAdapter);
    }
}
