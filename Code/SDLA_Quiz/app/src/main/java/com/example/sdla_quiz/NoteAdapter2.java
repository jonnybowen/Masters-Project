package com.example.sdla_quiz;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A custom adapter for notes. Allows for notes to be read from the database and listed
 * in a custom recyclerview layout.
 */
class NoteAdapter2 extends RecyclerView.Adapter<NoteAdapter2.NoteViewHolder2> {

    private Context mContext;
    private Cursor mCursor;

    /**
     * @param context
     * @param cursor
     */
    public NoteAdapter2(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    /**
     * Custom view holder class to display the title and a timestamp for each note.
     */
    public class NoteViewHolder2 extends RecyclerView.ViewHolder {
        public TextView titleText;
        public TextView timestampText;

        /**
         * View holder constructor that accepts a view and assigns textviews to the layout.
         *
         * @param itemView
         */
        public NoteViewHolder2(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.note_title);
            timestampText = itemView.findViewById(R.id.note_timestamp);
        }
    }

    /**
     * Required implementation.
     * Creates a new note view-holder from the note_item layout.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NoteViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder2(view);
    }

    /**
     * Recycles the view by assigning new data to it.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder2 holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        //Retrieve data from database.
        String title = mCursor.getString(mCursor.getColumnIndex(DbContract.NotesTable.COLUMN_TITLE));
        String timestamp = mCursor.getString(mCursor.getColumnIndex(DbContract.NotesTable.COLUMN_TIMESTAMP));

        //Trying to get subject name displayed.
        int id = mCursor.getInt(mCursor.getColumnIndex(DbContract.NotesTable._ID));
        holder.itemView.setTag(id);
        //-----

        holder.titleText.setText(title);
        holder.timestampText.setText(timestamp);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
