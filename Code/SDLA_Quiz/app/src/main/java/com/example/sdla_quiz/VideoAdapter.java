package com.example.sdla_quiz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public static final String EXTRA_URL = "extraUrl";

    private String title;
    private String url;


    public VideoAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

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


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        title = mCursor.getString(mCursor.getColumnIndex(QuizContract.VideosTable.COLUMN_TITLE));
         url = mCursor.getString(mCursor.getColumnIndex(QuizContract.VideosTable.COLUMN_URL));


        //---Trying to get subject name displayed.---
       // int id = mCursor.getInt(mCursor.getColumnIndex(QuizContract.VideosTable._ID));
       // holder.itemView.setTag(id);
        //-----

        holder.titleText.setText(title);
        holder.urlText.setText(url);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoViewVideosActivity.class);
                intent.putExtra(EXTRA_URL, url);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
