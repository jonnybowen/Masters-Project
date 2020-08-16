package com.example.SDLA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * This activity starts a quiz with previously selected parameters.
 * Users are offered questions from a subject (based on difficulty) until no more questions remain.
 * Users score is recorded and compared against the high score.
 */
public class QuizActivity extends AppCompatActivity {

    //UI DECLARATIONS
    //-------------

    //Declare TextViews
    private TextView tv_question;
    private TextView tv_score;
    private TextView tv_timer;
    private TextView tv_counter;
    private TextView tv_difficulty;
    private TextView tv_subject;

    //Declare Buttons (and button groups)
    private RadioGroup radioGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private Button confirmNextButton; // Dynamically changes text once a question has been answered.

    //Colour variables
    private ColorStateList radioBtnDefColor; // holds the default colour of the radio buttons
    private ColorStateList countdownDefColor; // holds the default colour of the countdown timer

    //END OF UI DECLARATIONS
    //-------------

    //Declare Vars
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private Question currentQuestion;
    private int score;
    private boolean answerLocked; // Logic variable for the answer being 'locked in'.
    private long backPressedTime;

    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    //Declare constants
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "KeyQuestionList";
    public static final String EXTRA_SCORE = "SCORE_EXTRA";
    private static final long COUNTDOWN_MILLIS = 31000; // 30 seconds timer (extra second so that timer reads 00:30 at start)

    /**
     * On-Create - Initialise UI, Buttons, and setup quiz based on previous parameters and start it.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialise Views
        tv_question = findViewById(R.id.tv_quiz_question);
        tv_counter = findViewById(R.id.tv_quiz_questionCount);
        tv_score = findViewById(R.id.tv_quiz_score);
        tv_timer = findViewById(R.id.tv_quiz_timer);
        tv_difficulty = findViewById(R.id.tv_quiz_difficulty);
        tv_subject = findViewById(R.id.tv_quiz_subject);
        //Initialise Buttons
        radioGroup = findViewById(R.id.radio_quiz_group);
        button1 = findViewById(R.id.radio_option1);
        button2 = findViewById(R.id.radio_option2);
        button3 = findViewById(R.id.radio_option3);
        confirmNextButton = findViewById(R.id.btn_quiz_confirm);

        // Initialise colour variables
        radioBtnDefColor = button1.getTextColors(); // save the colour of button 1 (could be 1, 2 or 3).
        countdownDefColor = tv_timer.getTextColors();

        //Get difficulty and subject from previous screen and apply to UI
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(QuizMenuActivity.EXTRA_SUBJECT_ID, 0);
        String subjectName = intent.getStringExtra(QuizMenuActivity.EXTRA_SUBJECT_NAME);
        String difficulty = intent.getStringExtra(QuizMenuActivity.EXTRA_DIFFICULTY);

        tv_difficulty.setText("Difficulty: " + difficulty);
        tv_subject.setText("Subject: " + subjectName);

        //On-Create logic. Retrieve the question list, shuffle it, and show a question.
        if (savedInstanceState == null) {
            DbHelper dbHelper = DbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(subjectId, difficulty);
            questionCounterTotal = questionList.size(); //Establishes how many q's the quiz will be.
            Collections.shuffle(questionList); // Randomises the order of the question-list
            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCounterTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answerLocked = savedInstanceState.getBoolean(KEY_ANSWERED);
            if (!answerLocked) {
                startCountDown();
            } else {
                updateTimer();
                showSolution();
            }
        }
        //The dynamic confirm/next button - shows confirm if an answer is selected or shows next if
        // an answer has been locked in
        confirmNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If answer is not locked-in, lock it in then check the answer.
                if (!answerLocked) {
                    // Check an answer has been selected
                    if (button1.isChecked() || button2.isChecked() || button3.isChecked()) { // Positive path - check if answer is right
                        checkAnswer();
                    } else { // Negative path - inform user no answer has been selected.
                        Toast.makeText(QuizActivity.this, "No answer selected.", Toast.LENGTH_LONG);
                    }
                } else { // Negative Path -- Question has been answered. Show next question.
                    showNextQuestion();
                }
            }
        });
    }

    /**
     * A method to show the next question in the quiz. Resets UI elements to their defaults and
     * shows the next question from the list.
     */
    private void showNextQuestion() {
        //Reset Radio button colours to the default, using the variable that grabbed the colour on-create.
        button1.setTextColor(radioBtnDefColor);
        button2.setTextColor(radioBtnDefColor);
        button3.setTextColor(radioBtnDefColor);
        radioGroup.clearCheck();

        //Retrieves the next question from the (pre-randomised) question list until there are no more.
        if (questionCounter < questionCounterTotal) { // Positive Path - Questions are left
            currentQuestion = questionList.get(questionCounter);

            //Displays the question by filling views with info from currentQuestion object.
            tv_question.setText(currentQuestion.getQuestion());
            button1.setText(currentQuestion.getOption1());
            button2.setText(currentQuestion.getOption2());
            button3.setText(currentQuestion.getOption3());

            //Increment counter and update on-screen counter view.
            //Establish no answer is 'locked-in' and update dynamic button text accordingly.
            questionCounter++;
            tv_counter.setText("Question: " + questionCounter + " out of " + questionCounterTotal);
            answerLocked = false;
            confirmNextButton.setText("Confirm");

            timeLeftMillis = COUNTDOWN_MILLIS;
            startCountDown();
        } else { // Negative Path - No questions are left
            if (questionCounter == 12) {
                Toast.makeText(this, "No questions meet those parameters.", Toast.LENGTH_LONG).show();
                finishQuiz();
            } else {
                finishQuiz();
            }
        }
    }

    /**
     * Method to start the count-down timer. Contains logic for timer ticks and finish.
     */
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftMillis, 250) {
            @Override
            // Clock ticks, update UI.
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            // Time ran out, update UI and check answer
            public void onFinish() {
                timeLeftMillis = 0;
                updateTimer();
                checkAnswer();
            }
        }.start();
    }

    /**
     * A method to update the UI with the timer's current time.
     */
    private void updateTimer() {
        //convert milliseconds to human time
        int minutes = (int) (timeLeftMillis / 1000 / 60);
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tv_timer.setText(timeString);

        //Change timer colour to red if <10 seconds left.
        if (timeLeftMillis < 10000) {
            tv_timer.setTextColor(Color.RED);
        } else {
            //Change timer colour to default if >10 seconds left.
            tv_timer.setTextColor(countdownDefColor);
        }
    }

    /**
     * Method to finish the quiz. Will take the current score as extra
     * for comparison against high-score.
     */
    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish(); // close the activity
    }

    /**
     * Checks if user's answer matches the correct answer then shows the solution.
     */
    private void checkAnswer() {
        answerLocked = true;
        countDownTimer.cancel();

        RadioButton radioButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(radioButtonSelected) + 1; // add 1 to match answer numbers

        //Increment score if user is correct
        if (answerNo == currentQuestion.getAnswerNo()) {
            score++;
            tv_score.setText("Correct: " + score);
        }

        showSolution();
    }

    /**
     * Reveals the correct answer to the user. Colour all answers red, but make the correct answer green.
     */
    private void showSolution() {
        button1.setTextColor(Color.RED);
        button2.setTextColor(Color.RED);
        button3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNo()) {
            case 1:
                button1.setTextColor(Color.GREEN);
                Toast.makeText(QuizActivity.this, "The answer is: Option 1!", Toast.LENGTH_LONG).show();
                break;
            case 2:
                button2.setTextColor(Color.GREEN);
                Toast.makeText(QuizActivity.this, "The answer is: Option 2!", Toast.LENGTH_LONG).show();
                break;
            case 3:
                button3.setTextColor(Color.GREEN);
                Toast.makeText(QuizActivity.this, "The answer is: Option 3!", Toast.LENGTH_LONG).show();
                break;
        }
        if (questionCounter < questionCounterTotal) {
            // Quiz still going
            confirmNextButton.setText("Next");
        } else {
            // Quiz finished, offer finish button.
            confirmNextButton.setText("Finish!");
        }
    }

    /**
     * Override back arrow press. Require a double-tap confirmation of the back arrow to exit.
     * Intention is to prevent accidentally losing quiz progress.
     */
    @Override
    public void onBackPressed() {
        //Require 2 seconds of press time to quit
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            //Less than 2 seconds pressed, remind user how to quit.
            Toast.makeText(QuizActivity.this, "Press back again to quit.", Toast.LENGTH_LONG).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    /**
     * Stop countdown if activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    /**
     * Save screen state when changing orientation of screen.
     *
     * @param outState the saved instance state
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Deleted Persistent bundle state parameter because it crashed the quiz when rotated.
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftMillis);
        outState.putBoolean(KEY_ANSWERED, answerLocked);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
