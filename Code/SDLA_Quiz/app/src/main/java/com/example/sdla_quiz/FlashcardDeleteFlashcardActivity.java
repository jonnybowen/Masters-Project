package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

public class FlashcardDeleteFlashcardActivity extends AppCompatActivity {

    //Declare Views
    RecyclerView recyclerView;
    TextView introText;

    //Declare Adapter
    private FlashcardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_delete_flashcard);

        //Initialise Views
        introText = findViewById(R.id.tv_deleteFlashcard_intro);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(FlashcardMenuActivity.EXTRA_SUBJECT_ID, 0);


        mAdapter = new FlashcardAdapter(this, QuizDbHelper.getInstance(FlashcardDeleteFlashcardActivity.this).getFlashcardsCursor(subjectId));
        recyclerView.setAdapter(mAdapter);
    }

}
