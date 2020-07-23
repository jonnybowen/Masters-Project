package com.example.sdla_quiz;

public class Flashcard {

    //Declare Variables
    private String term;
    private String definition;
    private String hint;
    private int subjectId;

    public Flashcard(){

    }

    public Flashcard(String term, String definition, int subjectId){
        this.term = term;
        this.definition = definition;
        this.subjectId = subjectId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }


    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
