package com.example.SDLA;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * This activity class allows users to create new quiz questions.
 */
public class QuizCreateQuestionActivity extends AppCompatActivity {
    //Declare Buttons
    private Button addQuestionButton;

    //Declare spinners
    private Spinner difficultySpinner;
    private Spinner subjectSpinner;

    //Declare EditTexts
    private EditText et_Question;
    private EditText et_Option1;
    private EditText et_Option2;
    private EditText et_Option3;
    private EditText et_AnswerId;

    //Declare variables
    private String newQuestion;
    private String newOption1;
    private String newOption2;
    private String newOption3;
    private int newAnswerId;
    private String newDifficulty;
    private int newSubject;

    /**
     * OnCreate - Initialise UI and apply logic to buttons.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_create_question);

        //Initialise Buttons
        addQuestionButton = findViewById(R.id.btn_newQuestion_confirm);

        //Initialise EditTexts
        et_Question = findViewById(R.id.et_newQuestion_question);
        et_Option1 = findViewById(R.id.et_newQuestion_option1);
        et_Option2 = findViewById(R.id.et_newQuestion_option2);
        et_Option3 = findViewById(R.id.et_newQuestion_option3);
        et_AnswerId = findViewById(R.id.et_newQuestion_answer);


        //Initialise Spinners
        subjectSpinner = findViewById(R.id.spinner_newQuestion_subject);
        difficultySpinner = findViewById(R.id.spinner_newQuestion_difficulty);

        //Populate Spinners
        loadSubjects();
        loadDifficultyLevels();

        //Add Button - Creates a new Question from user inputs and saves it to the database.
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewQuestion(et_Question.getText().toString());
                setNewOption1(et_Option1.getText().toString());
                setNewOption2(et_Option2.getText().toString());
                setNewOption3(et_Option3.getText().toString());
                setNewAnswerId(Integer.parseInt(et_AnswerId.getText().toString()));
                setNewDifficulty(difficultySpinner.getSelectedItem().toString());

                Subject selectedSubject = (Subject) subjectSpinner.getSelectedItem(); //temp holder for subject string
                setNewSubject(selectedSubject.getId()); //convert subject string to int

                Question q = new Question(newQuestion, newOption1, newOption2, newOption3, newAnswerId, newDifficulty, newSubject);
                DbHelper.getInstance(QuizCreateQuestionActivity.this).insertQuestion(q);
                Toast.makeText(QuizCreateQuestionActivity.this, "Question added to database", Toast.LENGTH_SHORT).show();

                //Reset UI for new input.
                et_Question.setText("");
                et_Option1.setText("");
                et_Option2.setText("");
                et_Option3.setText("");
                et_AnswerId.setText("");
            }
        });

    }

    /**
     * Loads a list of all available subjects into a spinner (UI).
     */
    private void loadSubjects() {
        DbHelper dbHelper = DbHelper.getInstance(this);
        List<Subject> subjects = dbHelper.getAllSubjects();
        ArrayAdapter<Subject> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapterSubjects);
    }

    /**
     * Loads a list of all difficulty levels into a spinner (UI).
     */
    private void loadDifficultyLevels() {
        //Assign difficulty levels array to spinner using adapter.
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapterDifficulty);
    }


    /**
     * Accessor method to set the new question
     *
     * @param newQuestion the newly created question
     */
    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }


    /**
     * Accessor method to set the new option 1
     *
     * @param newOption1 option 1 of the new question
     */
    public void setNewOption1(String newOption1) {
        this.newOption1 = newOption1;
    }


    /**
     * Accessor method to set the new option 2
     *
     * @param newOption2 option 2 of the new question
     */
    public void setNewOption2(String newOption2) {
        this.newOption2 = newOption2;
    }

    /**
     * Accessor method to set the new option 3
     *
     * @param newOption3 option 3 of the new question
     */
    public void setNewOption3(String newOption3) {
        this.newOption3 = newOption3;
    }

    /**
     * Accessor method to set the answer id
     *
     * @param newAnswerId the answer number of the new question
     */
    public void setNewAnswerId(int newAnswerId) {
        this.newAnswerId = newAnswerId;
    }

    /**
     * Accessor method to set the new difficulty
     *
     * @param newDifficulty difficulty of the new question
     */
    public void setNewDifficulty(String newDifficulty) {
        this.newDifficulty = newDifficulty;
    }

    /**
     * Accessor method to set the new subject
     *
     * @param newSubject the subject of the new question
     */
    public void setNewSubject(int newSubject) {
        this.newSubject = newSubject;
    }
}
