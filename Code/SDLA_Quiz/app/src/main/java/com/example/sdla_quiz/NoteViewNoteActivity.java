package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NoteViewNoteActivity extends AppCompatActivity {

    //Declare Views
    private LinedEditText mContent;
    private EditText mEditTitle;
    private TextView mViewTitle;

    //Declare Variables
    private boolean mIsNewNote;
    private Note mInitialNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view_note);

        //Init views
        mContent = findViewById(R.id.viewNote_content);
        mEditTitle = findViewById(R.id.edit_note_title);
        mViewTitle = findViewById(R.id.view_note_title);

        if(getIncomingIntent()){
            // new note, go to edit mode
            setNewNoteProperties();
        } else {
            //old note, go to view mode
            setNoteProperties();
        }


    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            //Retrieve Note from previous(browse) activity
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            mIsNewNote = false;
            return false;
        }
        mIsNewNote = true;
        return true;
    }

    private void setNoteProperties(){
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mContent.setText(mInitialNote.getContent());
    }

    private void setNewNoteProperties(){
        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");
    }
}
