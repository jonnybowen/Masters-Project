package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoteMenuActivity extends AppCompatActivity {

    //Declare views

    //Declare Buttons
    Button viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_menu);

        viewBtn = findViewById(R.id.btn_noteMenu_viewNotes);



        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteMenuActivity.this, NoteBrowseNotesActivity.class);
                startActivity(intent);
            }
        });
    }
}
