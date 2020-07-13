package com.example.sdla_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sdla_quiz.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static QuizDbHelper instance;


    //Initialise and declare variables
    public static final String DATABASE_NAME = "StudyQuiz.db";
    public static final int DATABASE_VERSION = 1;

    //Declare Database
    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_SUBJECTS_TABLE = "CREATE TABLE " +
                SubjectsTable.TABLE_NAME + " ( " +
                SubjectsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SubjectsTable.COLUMN_SUBJECT_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " (" +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT ," +
                QuestionsTable.COLUMN_OPTION2 + " TEXT ," +
                QuestionsTable.COLUMN_OPTION3 + " TEXT ," +
                QuestionsTable.COLUMN_ANSWER_NO + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_SUBJECT_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_SUBJECT_ID + ") REFERENCES " +
                SubjectsTable.TABLE_NAME + "(" + SubjectsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_FLASHCARDS_TABLE = "CREATE TABLE " +
                FlashcardsTable.TABLE_NAME + " (" +
                FlashcardsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FlashcardsTable.COLUMN_TERM + " TEXT, " +
                FlashcardsTable.COLUMN_DEFINITION + " TEXT, " +
                FlashcardsTable.COLUMN_SUBJECT_ID + " INTEGER, " +
                "FOREIGN KEY(" + FlashcardsTable.COLUMN_SUBJECT_ID + ") REFERENCES " +
                SubjectsTable.TABLE_NAME + "(" + SubjectsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";


        db.execSQL(SQL_CREATE_SUBJECTS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_FLASHCARDS_TABLE);

        fillSubjectsTable();
        fillQuestionsTable();
        fillFlashcardsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SubjectsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FlashcardsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillSubjectsTable() {
        Subject s1 = new Subject("Programming");
        insertSubject(s1);
        Subject s2 = new Subject("Geography");
        insertSubject(s2);
        Subject s3 = new Subject("Maths");
        insertSubject(s3);
    }

    public void addSubject(Subject subject) {
        db = getWritableDatabase();
        insertSubject(subject);
    }

    public void addSubjects(List<Subject> subjects) {
        db = getWritableDatabase();
        for (Subject subject : subjects) {
            insertSubject(subject);
        }
    }

    private void insertSubject(Subject subject) {
        ContentValues cv = new ContentValues();
        cv.put(SubjectsTable.COLUMN_SUBJECT_NAME, subject.getName());
        db.insert(SubjectsTable.TABLE_NAME, null, cv);
    }

    public void deleteSubject(String string) {
        db = getWritableDatabase();
        db.delete(SubjectsTable.TABLE_NAME, SubjectsTable.COLUMN_SUBJECT_NAME + "=?", new String[]{string});
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("PROGRAMMING SUBJECT, EASY TEST Q: A is correct",
                "A", "B", "C", 1, Question.difficultyEasy, Subject.PROGRAMMING);
        insertQuestion(q1);
        Question q2 = new Question("GEOGRAPHY SUBJECT, MEDIUM TEST Q: B is correct",
                "A", "B", "C", 2, Question.difficultyMedium, Subject.GEOGRAPHY);
        insertQuestion(q2);
        Question q3 = new Question("MATHS SUBJECT, HARD TEST Q: C is correct",
                "A", "B", "C", 3, Question.difficultyHard, Subject.MATHS);
        insertQuestion(q3);
        Question q4 = new Question("PROGRAMMING SUBJECT, EASY TEST Q: C is correct",
                "A", "B", "C", 3, Question.difficultyEasy, Subject.PROGRAMMING);
        insertQuestion(q4);
        Question q5 = new Question("NULL SUBJECT, MEDIUM TEST Q: B is correct",
                "A", "B", "C", 2, Question.difficultyMedium, 4);
        insertQuestion(q5);
        Question q6 = new Question("NULL SUBJECT, HARD TEST Q: A is correct",
                "A", "B", "C", 1, Question.difficultyHard, 5);
        insertQuestion(q6);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NO, question.getAnswerNo());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_SUBJECT_ID, question.getSubjectID());


        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SubjectsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
                subject.setId(cursor.getInt(cursor.getColumnIndex(SubjectsTable._ID)));
                subject.setName(cursor.getString(cursor.getColumnIndex(SubjectsTable.COLUMN_SUBJECT_NAME)));
                subjectList.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjectList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNo(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setSubjectID(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_SUBJECT_ID)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int subjectId, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_SUBJECT_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId), difficulty};

        Cursor cursor = db.query(QuestionsTable.TABLE_NAME, null,
                selection, selectionArgs,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNo(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setSubjectID(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_SUBJECT_ID)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }


    private void insertFlashcard(Flashcard flashcard) {
        ContentValues cv = new ContentValues();
        cv.put(FlashcardsTable.COLUMN_DEFINITION, flashcard.getDefinition());
        cv.put(FlashcardsTable.COLUMN_TERM, flashcard.getTerm());
        cv.put(FlashcardsTable.COLUMN_SUBJECT_ID, flashcard.getSubjectId());
        db.insert(FlashcardsTable.TABLE_NAME, null, cv);
    }

    public void addFlashcard(Flashcard flashcard) {
        db = getWritableDatabase();
        insertFlashcard(flashcard);
    }

    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> flashcardList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FlashcardsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Flashcard flashcard = new Flashcard();
                flashcard.setId(cursor.getInt(cursor.getColumnIndex(FlashcardsTable._ID)));
                flashcard.setTerm(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_TERM)));
                flashcard.setDefinition(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_DEFINITION)));
                flashcard.setSubjectId(cursor.getInt(cursor.getColumnIndex(FlashcardsTable.COLUMN_SUBJECT_ID)));
                flashcardList.add(flashcard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return flashcardList;
    }

    public Cursor getAllFlashcardsCursor() {
        return db.query(
                FlashcardsTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getFlashcardsCursor(int subjectId) {
        List<Flashcard> flashcardList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = FlashcardsTable.COLUMN_SUBJECT_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId)};

        Cursor cursor = db.query(FlashcardsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return cursor;
    }

    public List<Flashcard> getFlashcards(int subjectId) {
        List<Flashcard> flashcardList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = FlashcardsTable.COLUMN_SUBJECT_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId)};

        Cursor cursor = db.query(FlashcardsTable.TABLE_NAME, null,
                selection, selectionArgs,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Flashcard flashcard = new Flashcard();
                flashcard.setId(cursor.getInt(cursor.getColumnIndex(FlashcardsTable._ID)));
                flashcard.setTerm(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_TERM)));
                flashcard.setDefinition(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_DEFINITION)));
                flashcard.setSubjectId(cursor.getInt(cursor.getColumnIndex(FlashcardsTable.COLUMN_SUBJECT_ID)));
                flashcardList.add(flashcard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return flashcardList;
    }


    private void fillFlashcardsTable() {
        Flashcard f1 = new Flashcard("TEST FLASHCARD 1", "PROGRAMMING", 1);
        insertFlashcard(f1);
        Flashcard f2 = new Flashcard("TEST FLASHCARD 2", "GEOGRAPHY", 2);
        insertFlashcard(f2);
        Flashcard f3 = new Flashcard("TEST FLASHCARD 3", "MATHS", 3);
        insertFlashcard(f3);
        Flashcard f4 = new Flashcard("TEST FLASHCARD 4", "PROGRAMMING", 1);
        insertFlashcard(f4);
        Flashcard f5 = new Flashcard("TEST FLASHCARD 5", "GEOGRAPHY", 2);
        insertFlashcard(f5);
        Flashcard f6 = new Flashcard("TEST FLASHCARD 6", "MATHS", 3);
        insertFlashcard(f6);
        Flashcard f7 = new Flashcard("TEST FLASHCARD 7", "PROGRAMMING", 1);
        insertFlashcard(f7);

    }
}
