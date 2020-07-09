package com.example.flashcards;

/**
 * The Flashcard class.
 * <p>
 * This class represents digital flashcards that can be created, stored and viewed
 * by the user.
 */

/**
 * @author jonathanbowen
 */

public class Flashcard {
    // Declare Variables
    private String front; // The front (question) side of the flash-card
    private String back; // The back (answer) side of the flash-card
    private String hint; // The '3rd' (hint) side of the flash-card
    private String title; // The 'title' given to the flash-card
    private boolean currentSide; // The side currently facing up (readable). True if front, false if back.


    private StudySubject subject; // The subject category the card will be assigned to

    /**
     * No-arg constructor. Default settings are given to each field
     */
    public Flashcard() {
        setTitle("Title text: you can edit this."); // hint text to encourage the user to give a title
        setFront("This is the front of the flashcard. You can edit this text.");
        setBack("This is the back of the flashcard. You can edit this text.");
        setHint("This is a hint. you can edit it or make it blank.");
        setSubject(null);
        setCurrentSide(true); // set 'front' side to active.
    }

    /**
     * full-argument constructor. Takes all required and optional data and creates a
     * flashcard from it.
     *
     * @param title - the title to set
     * @param front - the front text of the flashcard to set
     * @param back  - the back text of the flashcard to set
     * @param hint  - hint text to set
     * @param subject - the subject to set.
     */
    public Flashcard(String title, String front, String back, String hint, StudySubject subject) {
        setTitle(title);
        setFront(front);
        setBack(back);
        setHint(hint);
        setSubject(subject);
        setCurrentSide(true);
    }


    /**
     * A method to simulate flipping the flash-card over. It changes the currently
     * active side.
     */
    public void flipFlashcard() {
        if (isCurrentSide() == true) {
            setCurrentSide(false);
        } else {
            setCurrentSide(true);
        }
    }

    /**
     * A function to display the contents of the 'front' side of the flash-card.
     */
    public void printFront() {
        System.out.println(getFront());
    }

    /**
     * A function to display the contents of the 'back' side of the flash-card.
     */
    public void printBack() {
        System.out.println(getBack());
    }

    /**
     * A function to display the contents of the 'hint' side of the flash-card.
     */
    public void printHint() {
        System.out.println(getHint());
    }

    /**
     * A function to display the contents of the 'title' side of the flash-card.
     */
    public void printTitle() {
        System.out.println(getTitle());
    }


    // Accessor Methods
    //----------------
    public String getFront() {
        return front;
    }


    public void setFront(String front) {
        this.front = front;
    }


    public String getBack() {
        return back;
    }


    public void setBack(String back) {
        this.back = back;
    }


    public String getHint() {
        return hint;
    }


    public void setHint(String hint) {
        this.hint = hint;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(boolean currentSide) {
        this.currentSide = currentSide;
    }

    public StudySubject getSubject() {
        return subject;
    }

    public void setSubject(StudySubject subject) {
        this.subject = subject;
    }

    //----------------
    //End of accessor methods.

}