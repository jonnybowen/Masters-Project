package com.example.SDLA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This activity class allows users to browse notes by subject.
 */
public class NoteBrowseNotesActivity extends AppCompatActivity implements NoteAdapter.OnNoteListener, View.OnClickListener {

    //Container for the subject id to be retrieved from the previous activity.
    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    //Declare Views
    private RecyclerView recyclerView;

    //Declare Variables
    private ArrayList<Note> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private int subjectId;

    //Declare Database & helper
    private SQLiteDatabase db;
    DbHelper dbHelper = new DbHelper(this);

    /**
     * onCreate - Initialises ui components, then initialises the recycler view based on subject.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialise UI
        setContentView(R.layout.activity_note_browse_notes);
        Toolbar toolbar = findViewById(R.id.browseNotes_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
        recyclerView = findViewById(R.id.NoteRecyclerView);
        findViewById(R.id.floatingButton).setOnClickListener(this);

        //Initialise Database

        db = dbHelper.getWritableDatabase();

        //Retrieve subject from previous screen
        Intent intent = getIntent();
        setSubjectId(intent.getIntExtra(NoteMenuActivity.EXTRA_SUBJECT_ID, 0));


        initRecyclerView();


    }

    /**
     * Method to initialise the recyclerview with a custom layout. Uses custom adapter for notes.
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(itemDecorator);

        //Get refreshed list
        noteList = dbHelper.getNotesList(subjectId);


        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    //Click event - launches view note activity for selected note.
    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(NoteBrowseNotesActivity.this, NoteViewNoteActivity.class);
        intent.putExtra("selected_note", noteList.get(position));
        startActivity(intent);
    }

    //Click logic for the floating action button. Takes user to create note screen.
    @Override
    public void onClick(View v) {
        //Start new activity and pass retrieved subject
        Intent intent = new Intent(this, NoteAddNoteActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        startActivity(intent);
    }


    /**
     * Removes a note from the list and from the database, then informs user
     *
     * @param note - note to be deleted
     */
    private void deleteNote(Note note) {
        //TODO remove note from database - only deletes from list
        noteList.remove(note);
        noteAdapter.notifyDataSetChanged();
        Toast.makeText(NoteBrowseNotesActivity.this, "Note deleted from collection.", Toast.LENGTH_LONG).show();
    }

    //Item touch helper allows simple touch gestures to be registered. required implementation for swipe gesture.
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        //Swipe logic - deletes the swiped note from the recycler-view and database.
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(noteList.get(viewHolder.getAdapterPosition()));
        }
    };

    /**
     * Override - onResume - refresh the note list.
     */
    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    /**
     * Accessor method to get the subject id
     *
     * @return the subject id
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectId the subject id
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}

























