package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class NoteBrowseNotesActivity extends AppCompatActivity implements NoteAdapter.OnNoteListener {

    //Declare Views
    private RecyclerView recyclerView;

    //Declare Variables
    private ArrayList<Note> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_browse_notes);
        recyclerView = findViewById(R.id.NoteRecyclerView);

        initRecyclerView();
        populateNotes();

        Toolbar toolbar = findViewById(R.id.browseNotes_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(itemDecorator);
        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
    }

    private void populateNotes() {
        for(int i = 0; i < 1000; i++){
            Note note = new Note();
            note.setTitle("Title number: " + i);
            note.setTimestamp("Year: " + (2000 + i));
            note.setContent("Content number: " + i);
            noteList.add(note);
        }
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(NoteBrowseNotesActivity.this, NoteViewNoteActivity.class);
        intent.putExtra("selected_note", noteList.get(position));
        startActivity(intent);
    }
}

























