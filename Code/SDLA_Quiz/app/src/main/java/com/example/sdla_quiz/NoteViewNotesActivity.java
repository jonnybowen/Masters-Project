package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class NoteViewNotesActivity extends AppCompatActivity {

    //Declare Views
    private RecyclerView recyclerView;

    //Declare Variables
    private ArrayList<Note> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view_notes);
        recyclerView = findViewById(R.id.NoteRecyclerView);

        initRecyclerView();
        populateNotes();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        noteAdapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(noteAdapter);
    }

    private void populateNotes() {
        for(int i = 0; i < 1000; i++){
            Note note = new Note();
            note.setTitle("Title number: " + i);
            note.setTimestamp("Year: " + (2000 + i));
            note.setTitle("Title number: " + i);
            noteList.add(note);
        }
        noteAdapter.notifyDataSetChanged();
    }
}
