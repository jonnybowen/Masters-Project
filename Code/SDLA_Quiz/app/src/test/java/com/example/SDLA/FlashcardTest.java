package com.example.SDLA;

import static org.junit.Assert.*;

public class FlashcardTest {

    @org.junit.Test
    public void testTermGetterSetter() {
        //Standard Use
        String input = "TEST";
        String expected = "TEST";
        Flashcard fc = new Flashcard();
        fc.setTerm(input);
        assertTrue(fc.getTerm() == expected);

        //Not equal
        String input2 = "TEST";
        String expected2 = "TEST";
        Flashcard fc2 = new Flashcard();
        fc2.setTerm(input);
        assertTrue(fc2.getTerm() == expected);
    }

    @org.junit.Test
    public void testDefinitionGetterSetter() {
        //Standard Use
        String input = "TEST";
        String expected = "TEST";
        Flashcard fc = new Flashcard();
        fc.setDefinition(input);

        assertTrue(fc.getDefinition() == expected);

        //Not equal
        String input2 = "TEST";
        String expected2 = "Different";
        Flashcard fc2 = new Flashcard();
        fc2.setDefinition(input2);
        assertFalse(fc2.getDefinition() == expected2);
    }

    @org.junit.Test
    public void testSubjectIdGetterSetter() {
        //Standard Use
        int input = 3;
        int expected = 3;
        Flashcard fc = new Flashcard();
        fc.setSubjectId(input);
        assert fc.getSubjectId() == expected;

        //Not equal
        int input2 = 3;
        int expected2 = 1;
        Flashcard fc2 = new Flashcard();
        fc2.setSubjectId(input);
        assertTrue(fc2.getSubjectId() != expected2);
    }
}