package com.example.sdla_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FlashcardViewFlashcardActivity extends AppCompatActivity {

    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";

    //Declare TextViews
    private TextView mainTv;
    private TextView sideTv;
    private TextView tv_cardCounter;

    //Declare Buttons
    private Button nextBtn;
    private Button flipBtn;
    private Button hintBtn;



    //Declare Variables
   private List<Flashcard> flashcardList = new ArrayList<>();
   private int flashcardCounter;
   private int flashcardCounterTotal;
   private Flashcard currentFlashcard;
   private boolean faceUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view_flashcard);

        //Initialise TextViews
        mainTv = findViewById(R.id.tv_viewFlashcards_main);

        faceUp = true;
        sideTv = findViewById(R.id.tv_viewFlashcards_side);
        sideTv.setText("Side: Term");

        tv_cardCounter = findViewById(R.id.tv_viewFlashcard_counter);

        //Initialise Buttons
        nextBtn = findViewById(R.id.btn_viewFlashcards_next);
        flipBtn = findViewById(R.id.btn_viewFlashcards_flip);
        hintBtn = findViewById(R.id.btn_viewFlashcards_hint);


        //Retrieve subject from previous screen
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(FlashcardMenuActivity.EXTRA_SUBJECT_ID, 0);

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        flashcardList = dbHelper.getFlashcards(subjectId);
        flashcardCounterTotal = flashcardList.size();
        showNextFlashcard();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextFlashcard();
            }
        });

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


    private void showNextFlashcard() {

        //Retrieves the next question from the (pre-randomised) question list until there are no more.
        if (flashcardCounter < flashcardCounterTotal) { // Positive Path - Questions are left
            currentFlashcard = flashcardList.get(flashcardCounter);

            //Displays the question by filling views with info from currentFlashcard object.
            mainTv.setText(currentFlashcard.getTerm());

            //Increment counter and update on-screen counter view.
            //Establish no answer is 'locked-in' and update dynamic button text accordingly.
            flashcardCounter++;
            tv_cardCounter.setText("Card : " + flashcardCounter + " / " + flashcardCounterTotal );
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
