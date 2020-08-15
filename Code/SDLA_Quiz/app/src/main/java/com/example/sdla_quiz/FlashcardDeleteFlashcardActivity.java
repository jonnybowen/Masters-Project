package com.example.sdla_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity class allows users to browse and delete flashcards from their database.
 */
public class FlashcardDeleteFlashcardActivity extends AppCompatActivity {

    //Declare Views
    RecyclerView recyclerView;
    TextView introText;

    //Declare Adapter
    private FlashcardAdapter mAdapter;

    //Declare Database
    private SQLiteDatabase mDatabase;

    /**
     * Initialise UI (using custom adapter) and database. Assigns an itemtouchhelper to the
     * recyclerview.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_delete_flashcard);

        //Initialise Database
        DbHelper dbHelper = new DbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(FlashcardMenuActivity.EXTRA_SUBJECT_ID, 0);

        //Initialise Views
        introText = findViewById(R.id.tv_delFlashcards_intro);
        recyclerView = findViewById(R.id.recyclerView_delFlashcards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FlashcardAdapter(this, DbHelper.getInstance(FlashcardDeleteFlashcardActivity.this).getFlashcardsCursor(subjectId));
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //TODO get ID of swiped viewholder and delete it from database.
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((int) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }


    /**
     * Deletes selected flashcard from the database then informs user
     *
     * @param id the id of the database entry
     */
    private void removeItem(int id) {
        mDatabase.delete(DbContract.FlashcardsTable.TABLE_NAME,
                DbContract.FlashcardsTable._ID + "=" + id, null);
        Toast.makeText(FlashcardDeleteFlashcardActivity.this, "Flashcard deleted from collection.", Toast.LENGTH_LONG).show();
    }
}
