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
 * A custom adapter for flashcards. Allows for flashcards to be read from the database and listed
 * in a custom recyclerview layout.
 */
public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    //Declare vars
    private Context mContext;
    private Cursor mCursor;

    /**
     * Constructor for the flashcard adapter.
     *
     * @param context
     * @param cursor
     */
    public FlashcardAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    /**
     * Custom view holder class to display the term and definition for each flashcard.
     */
    public class FlashcardViewHolder extends RecyclerView.ViewHolder {

        public TextView termText;
        public TextView definitionText;

        /**
         * View holder constructor that accepts a view and assigns textviews to the layout.
         *
         * @param itemView
         */
        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            termText = itemView.findViewById(R.id.tv_delFlashcards_term);
            definitionText = itemView.findViewById(R.id.tv_delFlashcards_definition);

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
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.flashcard_item, parent, false);
        return new FlashcardViewHolder(view);
    }

    /**
     * Recycles view by assigning new data to it
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String term = mCursor.getString(mCursor.getColumnIndex(DbContract.FlashcardsTable.COLUMN_TERM));
        String definition = mCursor.getString(mCursor.getColumnIndex(DbContract.FlashcardsTable.COLUMN_DEFINITION));

        holder.termText.setText(term);
        holder.definitionText.setText(definition);

    }

    /**
     * Return number of items currently in the cursor.
     *
     * @return mCursor.getCount()
     */
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


}
