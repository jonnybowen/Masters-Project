package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This activity class allows users to browse videos by subject.
 */
public class VideoBrowseVideosActivity extends AppCompatActivity {

    //Declare Views
    RecyclerView recyclerView;
    TextView introText;

    //Declare Adapter
    private VideoAdapter mAdapter;

    /**
     * onCreate - Initialises ui components, then initialises the recycler view based on subject.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
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
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(itemDecorator);
        mAdapter = new VideoAdapter(this, DbHelper.getInstance(VideoBrowseVideosActivity.this).getVideosCursor(subjectId));
        recyclerView.setAdapter(mAdapter);
    }
}
