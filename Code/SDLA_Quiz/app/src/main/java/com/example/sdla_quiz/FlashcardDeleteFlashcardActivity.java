package com.example.sdla_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlashcardDeleteFlashcardActivity extends AppCompatActivity {

    //Declare Views
    RecyclerView recyclerView;
    TextView introText;

    //Declare Adapter
    private FlashcardAdapter mAdapter;

    //Declare Database
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_delete_flashcard);

        //Initialise Database
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(FlashcardMenuActivity.EXTRA_SUBJECT_ID, 0);

        //Initialise Views
        introText = findViewById(R.id.tv_deleteFlashcard_intro);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FlashcardAdapter(this, QuizDbHelper.getInstance(FlashcardDeleteFlashcardActivity.this).getFlashcardsCursor(subjectId));
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            removeItem((int) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }


    private void removeItem(int id){
        mDatabase.delete(QuizContract.FlashcardsTable.TABLE_NAME,
                QuizContract.FlashcardsTable._ID + "=" + id, null);
        Toast.makeText(FlashcardDeleteFlashcardActivity.this,"Flashcard deleted from collection.", Toast.LENGTH_LONG).show();
       // mAdapter.swapCursor(QuizDbHelper.getInstance(this).getFlashcardsCursor(id));
    }
}
