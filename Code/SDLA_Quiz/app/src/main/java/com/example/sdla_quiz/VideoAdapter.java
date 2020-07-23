package com.example.sdla_quiz;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public VideoAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText;
        public TextView urlText;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_delVideo_title);
            urlText = itemView.findViewById(R.id.tv_delVideo_url);

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
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String title = mCursor.getString(mCursor.getColumnIndex(QuizContract.VideosTable.COLUMN_TITLE));
        String url = mCursor.getString(mCursor.getColumnIndex(QuizContract.VideosTable.COLUMN_URL));

        //---Trying to get subject name displayed.---
        int id = mCursor.getInt(mCursor.getColumnIndex(QuizContract.VideosTable._ID));
        holder.itemView.setTag(id);
        //-----

        holder.titleText.setText(title);
        holder.urlText.setText(url);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
