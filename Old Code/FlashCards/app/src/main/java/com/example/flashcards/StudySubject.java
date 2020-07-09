package com.example.flashcards;

import java.util.ArrayList;

/**
 * This class represents a subject of study.
 * Various notes, flashcards, etc. can be assigned to these subjects.
 *
 * @author jonathanbowen
 */
public class StudySubject {

    // Declare and initialise variables
    private String name = new String(); // The name of the collection
    private ArrayList<Flashcard> collection = new ArrayList<>(); // The array to store the flash-card objects.

    /**
     * No-arg constructor.
     */
    public StudySubject() {
        setName("My Default Collection");
    }

    /**
     * 1-argument constructor. Takes a name string and applies it.
     *
     * @param name - the name to set
     */
    public StudySubject(String name) {
        setName(name);
    }

    /**
     * A method to add a Flashcard to the collection
     */
    public void addToCollection(Flashcard flashCard) {
        getCollection().add(flashCard);
        System.out.println("Flashcard titled: " + flashCard.getTitle() + " added to collection");
    }

    /**
     * A method to remove a Flashcard from the collection
     */
    public void removeFromCollection(Flashcard flashCard) {
        getCollection().remove(flashCard);
    }

    /**
     * A method to print all details of all flash-cards in the collection
     */
    public void printCollectionDetails() {
        for (Flashcard i : getCollection()) {
            System.out.println("Title: " + i.getTitle());
            System.out.println("Front: " + i.getFront());
            System.out.println("Back: " + i.getBack());
            System.out.println("Hint: " + i.getHint());
        }
    }

    /**
     * A method to simulate showing the currently 'up' face of the flash-card.
     */
    public void lookAtFlashcard(Flashcard flashCard) {
        System.out.println("Title: " + flashCard.getTitle());

        if (flashCard.isCurrentSide() == true) {
            System.out.println("Front: " + flashCard.getFront());
        } else {
            System.out.println("Back: " + flashCard.getBack());
        }
    }

    /**
     * A method to simulate flipping the flash-card over. It changes the currently
     * active side.
     */
    public void flipFlashcard(Flashcard flashCard) {
        if (flashCard.isCurrentSide() == true) {
            flashCard.setCurrentSide(false);
        } else {
            flashCard.setCurrentSide(true);
        }
    }

    /**
     * A method to simulate showing a hint to the user.
     */
    public void takeHint(Flashcard flashCard) {
        System.out.println("Hint: " + flashCard.getHint());
    }

    /**
     * @return the collection
     */
    public ArrayList<Flashcard> getCollection() {
        return collection;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(ArrayList<Flashcard> collection) {
        this.collection = collection;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


}
