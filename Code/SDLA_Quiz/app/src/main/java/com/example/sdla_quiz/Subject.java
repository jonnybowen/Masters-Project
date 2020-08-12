package com.example.sdla_quiz;

import androidx.annotation.NonNull;

/**
 * Custom data class to represent a subject. Subjects are the criteria by
 * which the app's functionality is organised.
 */
public class Subject {

    //Declare constants - some dummy data to initialise

    //Declare vars
    private int id;
    private String name;

    /**
     * No-arg constructor
     */
    public Subject(){
    }

    /**
     * Constructor for subject class
     * @param name
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * Accessor method to get the id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Accessor method to set the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Accessor method to get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override toString to it returns the subject name.
     * @return the subject's name
     */
    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
