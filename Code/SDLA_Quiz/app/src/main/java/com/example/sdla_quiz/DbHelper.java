package com.example.sdla_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sdla_quiz.DbContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class for the database.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper instance;

    //Initialise and declare variables
    public static final String DATABASE_NAME = "SDLA_DATABASE.db";
    public static final int DATABASE_VERSION = 3;

    //Declare Database
    private SQLiteDatabase db;

    /**
     * Constructor
     *
     * @param context
     */
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Gets an instance of the database-helper or creates one if null.
     *
     * @param context
     * @return instance
     */
    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * On-create method. Create SQL tables and populate them with some dummy data.
     *
     * @param db - a database
     */
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

        final String SQL_CREATE_VIDEOS_TABLE = "CREATE TABLE " +
                VideosTable.TABLE_NAME + " (" +
                VideosTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VideosTable.COLUMN_TITLE + " TEXT, " +
                VideosTable.COLUMN_URL + " TEXT, " +
                VideosTable.COLUMN_SUBJECT_ID + " INTEGER, " +
                "FOREIGN KEY(" + VideosTable.COLUMN_SUBJECT_ID + ") REFERENCES " +
                SubjectsTable.TABLE_NAME + "(" + SubjectsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_NOTES_TABLE = "CREATE TABLE " +
                NotesTable.TABLE_NAME + " (" +
                NotesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesTable.COLUMN_TITLE + " TEXT, " +
                NotesTable.COLUMN_CONTENT + " TEXT, " +
                NotesTable.COLUMN_TIMESTAMP + " TEXT, " +
                NotesTable.COLUMN_SUBJECT_ID + " INTEGER, " +
                "FOREIGN KEY(" + NotesTable.COLUMN_SUBJECT_ID + ") REFERENCES " +
                SubjectsTable.TABLE_NAME + "(" + SubjectsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";


        db.execSQL(SQL_CREATE_SUBJECTS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_FLASHCARDS_TABLE);
        db.execSQL(SQL_CREATE_VIDEOS_TABLE);
        db.execSQL(SQL_CREATE_NOTES_TABLE);

        fillSubjectsTable();
        fillQuestionsTable();
        fillFlashcardsTable();
        fillVideosTable();
        fillNotesTable();
    }

    /**
     * on-Upgrade method. Drops all tables and runs on-Create again if database version is changed.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SubjectsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FlashcardsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VideosTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * on-Configure called when the database connection is being configured,
     * to enable foreign key support
     *
     * @param db
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * Method to populate the subject table with some sample data.
     */
    private void fillSubjectsTable() {
        Subject s1 = new Subject("Maths");
        insertSubject(s1);
        Subject s2 = new Subject("Spanish");
        insertSubject(s2);
    }

    /**
     * Inserts a subject to the database.
     *
     * @param subject
     */
    public void insertSubject(Subject subject) {
        ContentValues cv = new ContentValues();
        cv.put(SubjectsTable.COLUMN_SUBJECT_NAME, subject.getName());
        db.insert(SubjectsTable.TABLE_NAME, null, cv);
    }

    /**
     * Deletes a subject from the database
     *
     * @param subjectName
     */
    public void deleteSubject(String subjectName) {
        db.delete(SubjectsTable.TABLE_NAME, SubjectsTable.COLUMN_SUBJECT_NAME + "=?", new String[]{subjectName});
    }

    /**
     * Deletes a note from the database
     *
     * @param note
     */
    public void deleteNote(Note note) {
        db = getWritableDatabase();
        db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_TITLE + "=?", new String[]{note.getTitle()});
    }


    /**
     * Returns a list of all available subjects from database.
     *
     * @return subjectList
     */
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

    /**
     * Populates question database table with sample data of different subjects.
     */
    private void fillQuestionsTable() {
        Question q1 = new Question("Maths - EASY - 2+2 = ...",
                "4", "3", "8", 1, Question.difficultyEasy, 1);
        insertQuestion(q1);
        Question q2 = new Question("Maths - Medium - 1+1 = ...",
                "4", "2", "3", 2, Question.difficultyMedium, 1);
        insertQuestion(q2);
        Question q3 = new Question("Spanish - Hard - Translate: Mirar",
                "To admire", "Mirror", "To watch", 3, Question.difficultyHard, 2);
        insertQuestion(q3);
        Question q4 = new Question("Spanish - Easy - Translate: Hola",
                "Goodbye", "See you soon", "Hello", 3, Question.difficultyEasy, 2);
        insertQuestion(q4);

    }


    /**
     * Inserts a question object to the database.
     *
     * @param question
     */
    public void insertQuestion(Question question) {
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

    /**
     * Returns an ArrayList of questions based on subject and difficulty
     *
     * @param subjectId
     * @param difficulty
     * @return questionList
     */
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


    /**
     * Inserts a flashcard object to the database.
     *
     * @param flashcard
     */
    public void insertFlashcard(Flashcard flashcard) {
        ContentValues cv = new ContentValues();

        cv.put(FlashcardsTable.COLUMN_DEFINITION, flashcard.getDefinition());
        cv.put(FlashcardsTable.COLUMN_TERM, flashcard.getTerm());
        cv.put(FlashcardsTable.COLUMN_SUBJECT_ID, flashcard.getSubjectId());

        db.insert(FlashcardsTable.TABLE_NAME, null, cv);
    }


    /**
     * Returns a cursor in the flashcard table with data matching given subject.
     *
     * @param subjectId
     * @return cursor
     */
    public Cursor getFlashcardsCursor(int subjectId) {
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

    /**
     * Returns a list of flashcards that match the given subject.
     *
     * @param subjectId
     * @return flashcardList
     */
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
                flashcard.setTerm(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_TERM)));
                flashcard.setDefinition(cursor.getString(cursor.getColumnIndex(FlashcardsTable.COLUMN_DEFINITION)));
                flashcard.setSubjectId(cursor.getInt(cursor.getColumnIndex(FlashcardsTable.COLUMN_SUBJECT_ID)));
                flashcardList.add(flashcard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return flashcardList;
    }


    /**
     * Method to populate the flashcards database table with dummy data.
     */
    private void fillFlashcardsTable() {
        Flashcard f1 = new Flashcard("Acute Angle", "An angle whose measure is between 0° and 90° or with less than 90° radians.", 1);
        insertFlashcard(f1);

        Flashcard f2 = new Flashcard("Addend", "A number involved in an addition problem; numbers being added are called addends.", 1);
        insertFlashcard(f2);

        Flashcard f3 = new Flashcard("Algebra", "The branch of mathematics that substitutes letters for numbers to solve for unknown values.", 1);
        insertFlashcard(f3);

        Flashcard f4 = new Flashcard("Hola", "Hello", 2);
        insertFlashcard(f4);

        Flashcard f5 = new Flashcard("Mirar", "To watch", 2);
        insertFlashcard(f5);

        Flashcard f6 = new Flashcard("Bocadillo", "Sandwich", 2);
        insertFlashcard(f6);

    }

    /**
     * Method to insert a video object to the database.
     *
     * @param video
     */
    public void insertVideo(Video video) {
        ContentValues cv = new ContentValues();
        cv.put(VideosTable.COLUMN_TITLE, video.getTitle());
        cv.put(VideosTable.COLUMN_URL, video.getUrl());
        cv.put(VideosTable.COLUMN_SUBJECT_ID, video.getSubjectId());
        db.insert(VideosTable.TABLE_NAME, null, cv);
    }

    /**
     * Populates the video database table with dummy data.
     */
    public void fillVideosTable() {
        Video v1 = new Video("TEST VIDEO", "dQw4w9WgXcQ", 1);
        insertVideo(v1);

        Video v2 = new Video("TEST VIDEO 2", "yho4YXrmle8", 1);
        insertVideo(v2);

        Video v3 = new Video("TEST VIDEO 3", "eA22Ej6kb2wo", 2);
        insertVideo(v3);

        Video v4 = new Video("TEST VIDEO 4", "WUEAbSkb_eQ", 2);
        insertVideo(v4);

        Video v5 = new Video("TEST VIDEO 5", "FE9PUexeUv0", 2);
        insertVideo(v5);

    }

    /**
     * Returns a cursor in the flashcard table with data matching given subject.
     *
     * @param subjectId
     * @return cursor
     */
    public Cursor getVideosCursor(int subjectId) {
        db = getReadableDatabase();

        String selection = VideosTable.COLUMN_SUBJECT_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId)};

        Cursor cursor = db.query(VideosTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return cursor;
    }


    /**
     * Inserts a note object to the database.
     *
     * @param note
     */
    public void insertNote(Note note) {
        ContentValues cv = new ContentValues();

        cv.put(NotesTable.COLUMN_TITLE, note.getTitle());
        cv.put(NotesTable.COLUMN_CONTENT, note.getContent());
        cv.put(NotesTable.COLUMN_TIMESTAMP, note.getTimestamp());
        cv.put(NotesTable.COLUMN_SUBJECT_ID, note.getSubjectID());

        db.insert(NotesTable.TABLE_NAME, null, cv);
    }

    /**
     * Method to populate Notes database table with dummy data.
     */
    public void fillNotesTable() {
        Note note1 = new Note("Maths Note 1", "Lecture cancelled next week!", "Aug 2020", 1);
        insertNote(note1);

        Note note2 = new Note("Spanish", "Revise Grammar before 03/09", "Aug 2021", 2);
        insertNote(note2);
    }

    /**
     * Returns a cursor in the Notes table with data matching the given subject.
     *
     * @param subjectId
     * @return cursor
     */
    public Cursor getNotesCursor(int subjectId) {
        db = getReadableDatabase();

        String selection = NotesTable.COLUMN_SUBJECT_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId)};

        Cursor cursor = db.query(NotesTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return cursor;
    }

    /**
     * Returns a list of flashcards that match the given subject.
     *
     * @param subjectId
     * @return flashcardList
     */
    public ArrayList<Note> getNotesList(int subjectId) {
        ArrayList<Note> noteList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = NotesTable.COLUMN_SUBJECT_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(subjectId)};

        Cursor cursor = db.query(NotesTable.TABLE_NAME, null,
                selection, selectionArgs,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setTitle(cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_CONTENT)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(NotesTable.COLUMN_TIMESTAMP)));
                note.setSubjectID(cursor.getInt(cursor.getColumnIndex(NotesTable.COLUMN_SUBJECT_ID)));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return noteList;
    }
}
