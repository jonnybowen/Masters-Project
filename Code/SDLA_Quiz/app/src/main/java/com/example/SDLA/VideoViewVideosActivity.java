package com.example.SDLA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * This activity class allows users to view videos without leaving the app.
 * (Currently only works for youtube videos.)
 */
public class VideoViewVideosActivity extends YouTubeBaseActivity {

    //Declare UI
    YouTubePlayerView youTubePlayer;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitListener;

    //Declare variables
    private String videoID;

    /**
     * onCreate - Initialise UI, set button logic and initialise video from selected URL.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
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

        //Initialise video but do not start.
        mOnInitListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        //Play button - starts the video
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayer.initialize(YouTubeConfig.getApiKey(), mOnInitListener);
            }
        });
    }


}
