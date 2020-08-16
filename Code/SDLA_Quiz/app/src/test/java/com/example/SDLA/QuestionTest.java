package com.example.SDLA;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    //Inputs
    String questionIn = "Test Question";
    String option1In = "A";
    String option2In = "B";
    String option3In = "C";
    int answerNoIn = 2;
    String difficultyIn = Question.difficultyHard;
    int subjectIdIn = 1;


    //Question setup
    Question q = new Question(questionIn, option1In, option2In, option3In, answerNoIn, difficultyIn, subjectIdIn);


    @Test
    public void getQuestion() {
        assertSame(q.getQuestion(), questionIn);
    }

    @Test
    public void setQuestion() {
        String newQuestion = "Test 2";
        q.setQuestion(newQuestion);
        assertSame(q.getQuestion(), newQuestion);
    }

    @Test
    public void getOption1() {
        assertSame(q.getOption1(), option1In);
    }

    @Test
    public void setOption1() {
        String newOption1 = "New option 1";
        q.setOption1(newOption1);
        assertSame(q.getOption1(), newOption1);

    }

    @Test
    public void getOption2() {
        assertSame(q.getOption2(), option2In);
    }

    @Test
    public void setOption2() {
        String newOption2 = "New option 2";
        q.setOption2(newOption2);
        assertSame(q.getOption2(), newOption2);
    }

    @Test
    public void getOption3() {
        assertSame(q.getOption3(), option3In);
    }

    @Test
    public void setOption3() {
        String newOption3 = "New option 3";
        q.setOption3(newOption3);
        assertSame(q.getOption3(), newOption3);
    }

    @Test
    public void getAnswerNo() {
        assertEquals(q.getAnswerNo(), answerNoIn);
    }

    @Test
    public void setAnswerNo() {
        q.setAnswerNo(3);
        assertNotEquals(q.getAnswerNo(), answerNoIn);
    }

    @Test
    public void getDifficulty() {
        assertEquals(q.getDifficulty(), difficultyIn);
    }

    @Test
    public void setDifficulty() {
        q.setDifficulty(Question.difficultyEasy);
        assertNotEquals(q.getDifficulty(), difficultyIn);
        assertEquals(q.getDifficulty(), Question.difficultyEasy);
    }

    @Test
    public void getSubjectID() {
        assertEquals(q.getSubjectID(), subjectIdIn);
    }

    @Test
    public void setSubjectID() {
        q.setSubjectID(3);
        assertNotEquals(q.getSubjectID(), subjectIdIn);
    }
}