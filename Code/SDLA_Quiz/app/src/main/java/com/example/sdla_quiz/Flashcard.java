package com.example.sdla_quiz;

/**
 * Custom data class to represent a flashcard.
 */
public class Flashcard {

    //Declare Variables
    private String term;        // front side of the card
    private String definition;  // back side of the card
    private int subjectId;

    /**
     * No-arg Constructor
     */
    public Flashcard() {
    }

    /**
     * Constructor
     *
     * @param term
     * @param definition
     * @param subjectId
     */
    public Flashcard(String term, String definition, int subjectId) {
        this.term = term;
        this.definition = definition;
        this.subjectId = subjectId;
    }

    /**
     * Accessor method to get the term
     *
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Accessor method to set the term
     *
     * @param term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Accessor method to get the definition
     *
     * @return definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Accessor method to set the definition
     *
     * @param definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Accessor method to get the subject id
     *
     * @return the subject id
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectId
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
