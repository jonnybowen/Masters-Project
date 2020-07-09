package com.example.sdla_quiz;

import android.provider.BaseColumns;

/**
 * A contract class which acts as a container for constants which we will need for SQLite queries.
 */
public final class QuizContract {

    private QuizContract(){}

    public static class QuestionsTable implements BaseColumns { //BaseColumns implements id key automagically
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NO = "answer_no";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_SUBJECT_ID = "subjectId";
    }

    public static class SubjectsTable implements BaseColumns {
        public static final String TABLE_NAME = "Subjects";
        public static final String COLUMN_SUBJECT_NAME = "subjectName";
    }



}
