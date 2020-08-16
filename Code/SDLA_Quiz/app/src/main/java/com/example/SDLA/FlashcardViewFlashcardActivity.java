package com.example.SDLA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity class allows users to view flashcards.
 */
public class FlashcardViewFlashcardActivity extends AppCompatActivity {

    //Container for the subject id to be retrieved from the previous activity.
    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    //Declare TextViews
    private TextView mainTv;
    private TextView sideTv;
    private TextView tv_cardCounter;

    //Declare Buttons
    private Button nextBtn;
    private Button flipBtn;

    //Declare Variables
    private List<Flashcard> flashcardList = new ArrayList<>();
    private int flashcardCounter;
    private int flashcardCounterTotal;
    private Flashcard currentFlashcard;
    private boolean faceUp; // represents the current side on show to user. true = 'term' side

    /**
     * Initialises ui components, generates a list of flashcards from chosen subject
     * then loads first flashcard.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view_flashcard);

        //Initialise UI
        mainTv = findViewById(R.id.tv_viewFlashcards_main);
        faceUp = true;
        sideTv = findViewById(R.id.tv_viewFlashcards_side);
        sideTv.setText("Side: Term");
        tv_cardCounter = findViewById(R.id.tv_viewFlashcard_counter);
        nextBtn = findViewById(R.id.btn_viewFlashcards_next);
        flipBtn = findViewById(R.id.btn_viewFlashcards_flip);

        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(FlashcardMenuActivity.EXTRA_SUBJECT_ID, 0);

        DbHelper dbHelper = DbHelper.getInstance(this);
        flashcardList = dbHelper.getFlashcards(subjectId);
        flashcardCounterTotal = flashcardList.size();
        showNextFlashcard();

        // The next button - shows the next flashcard
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextFlashcard();
            }
        });

        // The flip button - flips the card to show the other side
        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (faceUp) {
                    mainTv.setText(currentFlashcard.getDefinition());
                    faceUp = false;
                    sideTv.setText("Side: Definition");
                } else {
                    mainTv.setText(currentFlashcard.getTerm());
                    faceUp = true;
                    sideTv.setText("Side: Term");

                }
            }
        });

    }

    /**
     * Method to retrieve the next question from the (pre-randomised) question list
     * until there are no more.
     */
    private void showNextFlashcard() {
        if (flashcardCounter < flashcardCounterTotal) { // Positive Path - Questions are left
            currentFlashcard = flashcardList.get(flashcardCounter);

            //Displays the question by filling views with info from currentFlashcard object.
            mainTv.setText(currentFlashcard.getTerm());

            //Increment flashcard counter and update ui.
            flashcardCounter++;
            tv_cardCounter.setText("Card : " + flashcardCounter + " / " + flashcardCounterTotal);

            faceUp = true;
        } else { // Negative Path - No questions are left
            if (flashcardList.size() == 0) {
                Toast.makeText(this, "No flashcards meet those parameters.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "No more flashcards left.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
