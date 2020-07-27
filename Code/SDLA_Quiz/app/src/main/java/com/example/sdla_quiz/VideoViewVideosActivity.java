package com.example.sdla_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoViewVideosActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayer;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitListener;

    //Declare variables
    private String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_videos);

        //Init Views
        youTubePlayer = findViewById(R.id.youtubePlayer);

        //Init Buttons
        btnPlay = findViewById(R.id.btn_videoPlay);


        //Retrieve URL from previous screen
        Intent intent = getIntent();
        videoID = intent.getStringExtra(VideoAdapter.EXTRA_URL);

        mOnInitListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              youTubePlayer.initialize(YouTubeConfig.getApiKey(), mOnInitListener);
            }
        });
    }



}
