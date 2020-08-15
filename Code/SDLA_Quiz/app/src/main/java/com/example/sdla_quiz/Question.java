package com.example.sdla_quiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Custom data class to represent a quiz question.
 * A question, assigned to a subject and a difficulty level , consists of a question and
 * three options (one answer),
 * of when it was made.
 */
public class Question implements Parcelable {

    //Constants - used to set question difficulty
    public static final String difficultyEasy = "Easy";
    public static final String difficultyMedium = "Medium";
    public static final String difficultyHard = "Hard";

    //Declare Variables
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answerNo;
    private String difficulty;
    private int subjectID;

    /**
     * No-arg constructor
     */
    public Question() {

    }


    /**
     * Constructor for the question class.
     *
     * @param question the question
     * @param option1 answer option 1
     * @param option2 answer option 2
     * @param option3 answer option 3
     * @param answerNo a number representing the correct answer option
     * @param difficulty the question's difficulty
     * @param subjectID the question's subject id
     */
    public Question(String question, String option1, String option2,
                    String option3, int answerNo, String difficulty, int subjectID) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNo = answerNo;
        this.difficulty = difficulty;
        this.subjectID = subjectID;
    }

    /**
     * Constructor that creates a question from a parcel
     */
    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNo = in.readInt();
        difficulty = in.readString();
        subjectID = in.readInt();
    }

    /**
     * Creates a parcel from a question.
     *
     * @param dest the parcel to write to
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNo);
        dest.writeString(difficulty);
        dest.writeInt(subjectID);
    }

    //Required implementation, no functionality added
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Returns a parcel created from a note.
     */
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };


    /**
     * Accessor method to get the question
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Accessor method to set the question
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Accessor method to get option 1
     *
     * @return option1
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Accessor method to set option 1
     *
     * @param option1 answer option 1
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Accessor method to get option 2
     *
     * @return option2 answer option 2
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Accessor method to set option 2
     *
     * @param option2 answer option 2
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Accessor method to get option 3
     *
     * @return option3 answer option 3
     */
    public String getOption3() {
        return option3;
    }

    /**
     * Accessor method to set option 3
     *
     * @param option3 answer option 3
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * Accessor method to get the answer number
     *
     * @return answerNo the number of the correct answer
     */
    public int getAnswerNo() {
        return answerNo;
    }

    /**
     * Accessor method to set the answer number
     *
     * @param answerNo the number of the correct answer
     */
    public void setAnswerNo(int answerNo) {
        this.answerNo = answerNo;
    }

    /**
     * Accessor method to get the difficulty
     *
     * @return difficulty the difficulty of the question
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Accessor method to set the difficulty
     *
     * @param difficulty the difficulty of the question
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Accessor method to get the subject id
     *
     * @return subjectId the subject id of the question
     */
    public int getSubjectID() {
        return subjectID;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectID the subject id of the quetion
     */
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Accessor method to get all difficulty levels
     *
     * @return string[] with all difficulties
     */
    public static String[] getAllDifficultyLevels() {
        return new String[]{
                difficultyEasy, difficultyMedium, difficultyHard
        };
    }
}
