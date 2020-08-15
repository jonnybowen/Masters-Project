package com.example.sdla_quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This questionnaire launches the first time the app is launched. It assesses a user's readiness to
 * learn based on a fixed set of questions, then shows the user their score at the end with a message.
 */
public class QuestionnaireFirstTime extends AppCompatActivity {

    //Declare TextViews
    TextView tvTitle;
    TextView tvQuestion;
    TextView tvInstructions;
    EditText etImportance;
    EditText etSkill;

    //Declare Buttons
    Button btnNext;

    //Declare Vars
    private ArrayList<String> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private int importanceScore;
    private int skillScore;

    /**
     * Oncreate - Initalise UI and button logic. Generate dialog box to explain point of survey.
     * generate question list, shuffle and show the first question.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_first_time);

        //Init UI
        tvTitle = findViewById(R.id.tv_survey_heading);
        tvQuestion = findViewById(R.id.tv_survey_question);
        tvInstructions = findViewById(R.id.tv_survey_instruction);
        etImportance = findViewById(R.id.et_survey_input_importance);
        etSkill = findViewById(R.id.et_survey_input_skill);
        btnNext = findViewById(R.id.btn_survey_next);

        Toolbar toolbar = findViewById(R.id.survey_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setTitle("Learning Readiness Survey");

        //Explain Survey with dialog box
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("This is the first time you have launched Studbud. Please complete this short survey to determine your learning readiness.");
        builder1.setCancelable(true);
        builder1.setNeutralButton(
                "OK!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder1.create();
        alert.show();

        questionList = new ArrayList<>();

        //Init vars
        importanceScore = 0;
        skillScore = 0;
        questionCounter = 0;

        //Create question list
        initQuestions();

        //Shuffle question list, and show a question.
        questionCounterTotal = questionList.size(); //Establishes how many q's the quiz will be.
        Collections.shuffle(getQuestionList()); // Randomises the order of the question-list
        showNextQuestion();


        //Confirm button. Add inputs to total score and show the next question.
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If empty string, don't process
                if (etImportance.getText().toString().equals("") || etSkill.getText().toString().equals("")) {
                    Toast.makeText(QuestionnaireFirstTime.this, "Please select valid answers", Toast.LENGTH_LONG).show();
                    etSkill.setText("");
                    etImportance.setText("");
                } else {
                    //Non-empty string
                    int tempImportance = Integer.parseInt(etImportance.getText().toString());
                    int tempSkill = Integer.parseInt(etSkill.getText().toString());

                    //Check validity and proceed
                    if (tempSkill < 5 && tempSkill > 0 && tempImportance < 5 && tempImportance > 0) {
                        //if valid, add to score
                        setSkillScore(getSkillScore() + tempSkill);
                        setImportanceScore(getImportanceScore() + tempImportance);
                        showNextQuestion();
                    } else {
                        //Negative path, invalid answer
                        Toast.makeText(QuestionnaireFirstTime.this, "Please select valid answers", Toast.LENGTH_LONG).show();
                        etSkill.setText("");
                        etImportance.setText("");
                    }
                }
            }
        });
    }


    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    public int getImportanceScore() {
        return importanceScore;
    }

    public void setImportanceScore(int importanceScore) {
        this.importanceScore = importanceScore;
    }

    public int getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(int skillScore) {
        this.skillScore = skillScore;
    }

    /**
     * Initialise questions. Adds fixed list of learning-readiness questions to the question list.
     */
    public void initQuestions() {
        questionList.add("Life skills: Organisation of time and resources in your life, co-operation in working with others, available support network");
        questionList.add("Independence: Autonomy, self-motivation, self-reliance, resourcefulness, initiative, and judgment");
        questionList.add("Basic skills: Literacy, numeracy, graphicity, computer literacy, etc.");
        questionList.add("Information skills: Ability to find information by: using libraries; abstracts; community resources; interpreting data, charts, tables, timetables, etc.");
        questionList.add("Study skills: Organisation of material for projects, note-taking and reading for different purposes, understanding assignment requirements");
        questionList.add("Learning to learn: Awareness of task demands, flexibility, self-knowledge of learning preferences, awareness of learning process, self-evaluation");
        questionList.add("Planning skills: Ability to design a plan of strategies for meeting learning needs, ability to carry out a plan systematically and sequentially");
        questionList.add("Problem development skills: Ability to formulate questions that are answerable through various research activities (projects, library, readings)");
        questionList.add("Analytical skills: Ability to select and use most effective means of acquiring information, ability to analyse and organise information, ability to select most relevant and reliable information sources");
        questionList.add("Communication skills: Ability to write reports, essays, instructions, discourse, display data, etc.");
        questionList.add("Evaluation skills: Ability to collect evidence of accomplishments and have it evaluated, ability to accept constructive feedback from others");
        questionList.add("Completion skills: Ability to identify problem areas, ability to revise work, commitment to completing units and program");
    }

    /**
     * A method to show the next question in the question list. If no more questions are left, initiate
     * the score evaluation.
     */
    public void showNextQuestion() {
        //Retrieves the next question from the (pre-randomised) question list until there are no more.
        if (questionCounter < questionCounterTotal) { // Positive Path - Questions are left
            //Clear inputs
            etSkill.setText("");
            etImportance.setText("");
            //Displays the next question in the list.
            tvQuestion.setText(questionList.get(questionCounter));

            questionCounter++;

        } else { // Negative Path - No questions are left
            evaluateScore(QuestionnaireFirstTime.this);
        }
    }

    /**
     * A method to evaluate the score, generates a dialog box informing the user of passing or failing the survey
     *
     * @param context
     */
    public void evaluateScore(Context context) {
        //Declare vars
        int totalSkillScore;
        int totalImportanceScore;

        //Init vars
        totalSkillScore = getSkillScore();
        totalImportanceScore = getImportanceScore();
        int finalImpScore = (totalImportanceScore / questionList.size());
        int finalSkillScore = (totalSkillScore / questionList.size());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

        //Negative path, failed assessment
        if (finalImpScore > 3 || finalSkillScore > 3) {
            builder1.setMessage("For importance you scored: " + (finalImpScore) + ". For skill you scored: " + (finalSkillScore) + ". This assessment has determined you are not ready for self-directed learning. Please try again another time.");
            builder1.setCancelable(true);
            builder1.setNeutralButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                            System.exit(1);
                        }
                    });
            AlertDialog alert = builder1.create();
            alert.show();
        }


        //Positive path, assessment passed.
        if (finalImpScore < 3 && finalSkillScore < 3) {
            builder1.setMessage("For importance you scored: " + (getImportanceScore() / 12) + ". For skill you scored: " + (getSkillScore() / 12) + ". This assessment has determined you are ready for self-directed learning. Press ok to proceed.");
            builder1.setNeutralButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                            Intent intent = new Intent(QuestionnaireFirstTime.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog alert = builder1.create();
            alert.show();
        }
    }
}