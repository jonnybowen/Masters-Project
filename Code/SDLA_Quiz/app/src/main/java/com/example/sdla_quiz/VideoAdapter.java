package com.example.sdla_quiz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A custom adapter for videos. Allows for videos to be read from the database and listed
 * in a custom recyclerview layout.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    //Declare vars
    private Context mContext;
    private Cursor mCursor;
    private String title;
    private String url;

    //Declare constants
    public static final String EXTRA_URL = "extraUrl";

    /**
     * Constructor
     *
     * @param context
     * @param cursor
     */
    public VideoAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    /**
     * Constructor for the Video view holder.
     */
    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText;
        public TextView urlText;
        LinearLayout parentLayout;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_delVideo_title);
            urlText = itemView.findViewById(R.id.tv_delVideo_url);
            parentLayout = itemView.findViewById(R.id.video_ParentLayout);
        }
    }


    /**
     * Assigns custom layout to the view, and returns a new Viewholder
     *
     * @param parent
     * @param viewType
     * @return FlashcardViewHolder
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    /**
     * Recycle view by assigning new data to it. If entry is clicked, launch view activity
     * using the selected video's url.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        title = mCursor.getString(mCursor.getColumnIndex(DbContract.VideosTable.COLUMN_TITLE));
        url = mCursor.getString(mCursor.getColumnIndex(DbContract.VideosTable.COLUMN_URL));


        holder.titleText.setText(title);
        holder.urlText.setText(url);

        //On-click - load the video in a new activity.
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoViewVideosActivity.class);
                intent.putExtra(EXTRA_URL, url);
                mContext.startActivity(intent);
            }
        });
    }

    // Override (required implementation) - return count of data in cursor.
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
