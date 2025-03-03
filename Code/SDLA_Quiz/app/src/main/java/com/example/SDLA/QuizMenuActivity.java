package com.example.SDLA;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * This activity class allows users to launch a quiz, or launch the activity to add quiz questions.
 * Quiz is launched based on subject and difficulty.
 */
public class QuizMenuActivity extends AppCompatActivity {

    //Declare Buttons
    private Button beginButton;
    private Button addQuestionButton;

    //Declare Constants
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_SUBJECT_ID = "extraSubjectId";
    public static final String EXTRA_SUBJECT_NAME = "extraSubjectName";
    public static final String SHARED_PREFS = "Shared Preferences";
    public static final String KEY_HIGHSCORE = "key HighScore";

    //Declare Views
    private TextView tv_HighScore;

    //Declare Spinners
    private Spinner spin_subject;
    private Spinner spin_difficulty;

    //Declare Vars
    private int highScore;

    /**
     * onCreate - Initialise UI and apply logic to buttons.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        //Initialise UI elements
        spin_subject = (Spinner) findViewById(R.id.spinner_flashcardMenu_subject);
        spin_difficulty = (Spinner) findViewById(R.id.spinner_quiz_menu_difficulty);
        tv_HighScore = findViewById(R.id.tv_quiz_highScore);
        beginButton = (Button) findViewById(R.id.btn_quizMenu_start);
        addQuestionButton = (Button) findViewById(R.id.btn_quizMenu_addQuestions);

        loadSubjects(); // populate spinner with subjects
        loadDifficultyLevels(); // populate spinner with difficulty levels
        loadHighScore(); //Populates high-score textview with a score.

        // The start button. Starts a quiz.
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginQuiz();
            }
        });

        // The add questions button. Takes the user to the 'add a new question' screen.
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizMenuActivity.this, QuizCreateQuestionActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method to launch the quiz activity. Stores relevant parameters passes them to QuizActivity
     */
    private void beginQuiz() {
        Subject selectedSubject = (Subject) spin_subject.getSelectedItem();
        int subjectId = selectedSubject.getId();
        String subjectName = selectedSubject.getName();
        String difficulty = spin_difficulty.getSelectedItem().toString();
        Intent beginQuizIntent = new Intent(QuizMenuActivity.this, QuizActivity.class);
        beginQuizIntent.putExtra(EXTRA_DIFFICULTY, difficulty);
        beginQuizIntent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        beginQuizIntent.putExtra(EXTRA_SUBJECT_NAME, subjectName);
        startActivityForResult(beginQuizIntent, REQUEST_CODE_QUIZ);
    }

    /**
     * Loads a list of all available subjects into a spinner (UI).
     */
    private void loadSubjects() {
        DbHelper dbHelper = DbHelper.getInstance(this);
        List<Subject> subjects = dbHelper.getAllSubjects();
        ArrayAdapter<Subject> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_subject.setAdapter(adapterSubjects);
    }

    /**
     * Loads a list of all available difficulty levels into a spinner (UI).
     */
    private void loadDifficultyLevels() {
        //Assign difficulty levels array to spinner using adapter.
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_difficulty.setAdapter(adapterDifficulty);
    }

    /**
     * Retrieve high-score from saved location (sharedPrefs) and apply to UI.
     */
    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        tv_HighScore.setText("High Score: " + highScore);

    }

    /**
     * Method to update the high score with a new one.
     *
     * @param highScoreNew the new highscore to be stored
     */
    private void updateHighScore(int highScoreNew) {
        highScore = highScoreNew;
        tv_HighScore.setText("High Score: " + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }

    /**
     * Checks high score upon quiz completion and updates if necessary
     *
     * @param requestCode the starting trigger
     * @param resultCode the ending trigger
     * @param data data to be updated
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highScore) {
                    updateHighScore(score);
                }
            }
        }
    }
}
