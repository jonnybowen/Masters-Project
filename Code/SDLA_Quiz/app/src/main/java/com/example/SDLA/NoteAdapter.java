package com.example.SDLA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Custom adapter class to convert note objects to a recycler view
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    //Declare vars
    private ArrayList<Note> noteList;
    private OnNoteListener mOnNoteListener;

    /**
     * Constructor for the adapter
     *
     * @param noteList the list of notes to adapt
     * @param onNoteListener an onNoteListener to enable clicking on separate entries
     */
    public NoteAdapter(ArrayList<Note> noteList, OnNoteListener onNoteListener) {
        this.noteList = noteList;
        this.mOnNoteListener = onNoteListener;
    }

    /**
     * Instantiates and returns a new viewholder object
     *
     * @param parent the layout's parent view group
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    /**
     * Sets attributes to the viewholder object.
     *
     * @param holder a row in the recycler view
     * @param position the position in the recyclerview
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timestamp.setText(noteList.get(position).getTimestamp());
        holder.title.setText(noteList.get(position).getTitle());
    }

    /**
     * Returns size of the array list
     *
     * @return notelist size
     */
    @Override
    public int getItemCount() {
        return noteList.size();
    }


    /**
     * Custom view holder class to display the title and a timestamp for each note.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView timestamp;
        OnNoteListener onNoteListener;

        /**
         * Constructor for the view holder
         *
         * @param itemView the item view
         * @param onNoteListener the click listener to attack to the itemview
         */
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }


    /**
     * interface to interpret click on item
     */
    public interface OnNoteListener {
        void onNoteClick(int position);
    }

}
