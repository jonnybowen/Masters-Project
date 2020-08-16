package com.example.SDLA;

import android.provider.BaseColumns;

/**
 * A contract class which acts as a container for constants which is needed for SQLite queries.
 */
public final class DbContract {

    private DbContract() {
    }

    /**
     * Contract for the questions table.
     */
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

    /**
     * Contract for the subjects table.
     */
    public static class SubjectsTable implements BaseColumns {
        public static final String TABLE_NAME = "Subjects";
        public static final String COLUMN_SUBJECT_NAME = "subjectName";
    }

    /**
     * Contract for the Flashcards table.
     */
    public static class FlashcardsTable implements BaseColumns {
        public static final String TABLE_NAME = "Flashcards";
        public static final String COLUMN_TERM = "term";
        public static final String COLUMN_DEFINITION = "definition";
        public static final String COLUMN_SUBJECT_ID = "subjectId";
    }

    /**
     * Contract for the Videos table.
     */
    public static class VideosTable implements BaseColumns {
        public static final String TABLE_NAME = "Videos";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_SUBJECT_ID = "subjectId";
    }

    /**
     * Contract for the Notes table.
     */
    public static class NotesTable implements BaseColumns {
        public static final String TABLE_NAME = "Notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_SUBJECT_ID = "subjectId";
    }
}
