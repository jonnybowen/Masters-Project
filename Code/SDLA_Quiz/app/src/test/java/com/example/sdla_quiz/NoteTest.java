package com.example.sdla_quiz;

import static org.junit.Assert.*;

public class NoteTest {

    @org.junit.Test
    public void testSubjectIdGetterSetter() {
        //Standard Use
        int input = 3;
        int expected = 3;
        Note note = new Note();
        note.setSubjectID(input);
        assert note.getSubjectID() == expected;

        //Not equal
        int input2 = 3;
        int expected2 = 1;
        Note note2 = new Note();
        note2.setSubjectID(input2);
        assertTrue(note2.getSubjectID() != expected2);
    }

    @org.junit.Test
    public void testTitleGetterSetter() {
        //Match
        String input = "TEST TITLE";
        String expected = "TEST TITLE";
        Note note = new Note();
        note.setTitle(input);
        assert note.getTitle() == expected;

        //No Match
        String input2 = "TEST TITLE";
        String expected2 = "TEST TITLE DIFFERENT";
        Note note2 = new Note();
        note2.setTitle(input2);
        assertTrue(note2.getTitle() != expected2);
    }

    @org.junit.Test
    public void testContentGetterSetter(){
        //Match
        String input = "TEST CONTENT";
        String expected = "TEST CONTENT";
        Note note = new Note();
        note.setContent(input);
        assert note.getContent() == expected;

        //No Match
        String input2 = "TEST CONTENT";
        String expected2 = "TEST CONTENT DIFFERENT";
        Note note2 = new Note();
        note2.setTitle(input2);
        assert note2.getContent() != expected2;
    }

    @org.junit.Test
    public void testTimestampGetterSetter() {
        //Match
        String input = "1 Aug 20";
        String expected = "1 Aug 20";
        Note note = new Note();
        note.setTimestamp(input);
        assert note.getTimestamp() == expected;

        //No Match
        String input2 = "1 Jan 99";
        String expected2 = "1 Aug 20";
        Note note2 = new Note();
        note2.setTitle(input2);
        assertFalse(note2.getContent() == expected2);
    }
}