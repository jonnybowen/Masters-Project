package com.example.myapplication;

import java.util.ArrayList;

/**
 * This class represents a collection of FlashCard objects.
 */

/**
 * @author jonathanbowen
 *
 */
public class FlashCardCollection {
	
// Declare variables
private String name = new String(); // The name of the collection
private ArrayList<FlashCard> collection = new ArrayList<FlashCard>(); // The array to store the flash-card objects.



/**
 * No-arg constructor.
 */
public FlashCardCollection() {
	setName("My Default Collection");
}

/**
 * 1-argument constructor. Takes a name string and applies it.
 * @param name - the name to set
 */
public FlashCardCollection(String name) {
	setName(name);
}

/**
 * A method to add a FlashCard to the collection
 */
public void addToCollection(FlashCard flashCard) {
	getCollection().add(flashCard);
	System.out.println("FlashCard titled: " + flashCard.getTitle() + " added to collection");
}

/**
 * A method to remove a FlashCard from the collection
 */
public void removeFromCollection(FlashCard flashCard) {
	getCollection().remove(flashCard);
}

/**
 * A method to print all details of all flash-cards in the collection
 */
public void printCollectionDetails() {
	for (FlashCard i : getCollection()) {
		System.out.println("Title: " + i.getTitle());
		System.out.println("Front: " + i.getFront());
		System.out.println("Back: " + i.getBack());
		System.out.println("Hint: " + i.getHint());
	}
}

/**
 * A method to simulate showing the currently 'up' face of the flash-card.
 */
public void lookAtFlashCard(FlashCard flashCard) {
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
public void flipFlashCard(FlashCard flashCard) {
	if (flashCard.isCurrentSide() == true) {
		flashCard.setCurrentSide(false);
	} else {
		flashCard.setCurrentSide(true);
	}
}

/**
 *  A method to simulate showing a hint to the user.
 */
public void takeHint(FlashCard flashCard) {
	System.out.println("Hint: " + flashCard.getHint());
}

/**
 * @return the collection
 */
public ArrayList<FlashCard> getCollection() {
	return collection;
}

/**
 * @param collection the collection to set
 */
public void setCollection(ArrayList<FlashCard> collection) {
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
