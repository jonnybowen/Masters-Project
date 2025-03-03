package com.example.SDLA;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This activity class allows users to create new flashcards.
 */
public class NoteAddNoteActivity extends AppCompatActivity {

    //Declare Buttons
    Button confirmBtn;

    //Declare Views
    TextView intro;
    LinedEditText newTitle, newContent;

    //Declare Vars
    String titleHolder;
    String contentHolder;
    int subjectIdHolder;

    /**
     * onCreate - Loads UI and applies relevant logic to buttons.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add_note);

        //Init UI
        confirmBtn = findViewById(R.id.btn_newNote_confirm);
        intro = findViewById(R.id.TV_newNote_intro);
        newTitle = findViewById(R.id.LET_newNote_title);
        newContent = findViewById(R.id.LET_newNote_content);

        //Retrieve subject from previous screen
        Intent intent = getIntent();
        setSubjectIdHolder(intent.getIntExtra(NoteMenuActivity.EXTRA_SUBJECT_ID, 0));

        //Confirm Button - creates a new note object from data on screen and adds it to the database.
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleHolder(newTitle.getText().toString());
                setContentHolder(newContent.getText().toString()); //Extract video ID and save it

                //Get current date and assign to a holder-string.
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                //Create note object from inputs and add to collection
                Note newNote = new Note(getTitleHolder(), getContentHolder(), currentDate, getSubjectIdHolder());
                DbHelper.getInstance(NoteAddNoteActivity.this).insertNote(newNote);

                //Inform user of success.
                Toast.makeText(NoteAddNoteActivity.this, "Note added to collection.", Toast.LENGTH_LONG).show();

                //Clear TextViews
                newTitle.setText("");
                newContent.setText("");

                finish();
            }
        });
    }

    /**
     * Accessor method to get the title holder-string
     *
     * @return titleHolder temporary holder for the title
     */
    public String getTitleHolder() {
        return titleHolder;
    }

    /**
     * Accessor method to set the title holder-string
     *
     * @param titleHolder temporary holder for the title
     */
    public void setTitleHolder(String titleHolder) {
        this.titleHolder = titleHolder;
    }

    /**
     * Accessor method to get the content holder-string
     *
     * @return contentHolder temporary holder for the content
     */
    public String getContentHolder() {
        return contentHolder;
    }

    /**
     * Accessor method to set the content holder-string
     *
     * @param contentHolder temporary holder for the content
     */
    public void setContentHolder(String contentHolder) {
        this.contentHolder = contentHolder;
    }

    /**
     * Accessor method to get the subject id holder-int
     *
     * @return subjectIdHolder temporary holder for the subject id
     */
    public int getSubjectIdHolder() {
        return subjectIdHolder;
    }

    /**
     * Accessor method to set the subject id holder-int
     *
     * @param subjectIdHolder  temporary holder for the subject id
     */
    public void setSubjectIdHolder(int subjectIdHolder) {
        this.subjectIdHolder = subjectIdHolder;
    }
}
