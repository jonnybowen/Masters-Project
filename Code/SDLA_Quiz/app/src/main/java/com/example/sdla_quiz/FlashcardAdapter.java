package com.example.sdla_quiz;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public FlashcardAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class FlashcardViewHolder extends RecyclerView.ViewHolder {

        public TextView termText;
        public TextView definitionText;

        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            termText = itemView.findViewById(R.id.textView_delFlashcard_Term);
            definitionText = itemView.findViewById(R.id.textView_delFlashcard_Definition);

        }
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.flashcard_item, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String term = mCursor.getString(mCursor.getColumnIndex(QuizContract.FlashcardsTable.COLUMN_TERM));
        String definition = mCursor.getString(mCursor.getColumnIndex(QuizContract.FlashcardsTable.COLUMN_DEFINITION));

        //Trying to get subject name displayed.
        int id = mCursor.getInt(mCursor.getColumnIndex(QuizContract.FlashcardsTable._ID));


        holder.termText.setText(term);
        holder.definitionText.setText(definition);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



     public void swapCursor(Cursor newCursor){
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

}
