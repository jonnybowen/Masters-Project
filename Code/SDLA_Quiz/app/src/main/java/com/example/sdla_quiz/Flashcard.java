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
     * @param term the flashcard's term
     * @param definition the flashcard's definition
     * @param subjectId the flashcard's subject id
     */
    public Flashcard(String term, String definition, int subjectId) {
        this.term = term;
        this.definition = definition;
        this.subjectId = subjectId;
    }

    /**
     * Accessor method to get the term
     *
     * @return term the flashcard's term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Accessor method to set the term
     *
     * @param term the flashcard's term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Accessor method to get the definition
     *
     * @return definition the flashcard's definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Accessor method to set the definition
     *
     * @param definition the flashcard's definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Accessor method to get the subject id
     *
     * @return the flashcard's subject id
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectId the the flashcard's subject id
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
