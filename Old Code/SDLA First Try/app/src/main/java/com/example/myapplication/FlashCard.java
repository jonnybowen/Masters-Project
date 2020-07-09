package com.example.myapplication;
/**
 * The FlashCard class.
 * 
 * This class represents digital flash-cards that can be created, stored and viewed
 * by the user.
 */

/**
 * @author jonathanbowen
 */

public class FlashCard {
	// Declare Variables
	private String front; // The front (question) side of the flash-card
	private String back; // The back (answer) side of the flash-card
	private String hint; // The '3rd' (hint) side of the flash-card
	private String title; // The 'title' given to the flash-card
	private boolean currentSide; // The side currently facing up (readable). True if front, false if back.

	/**
	 * No-arg constructor. Default settings are given to each field
	 */
	public FlashCard() {
		setTitle("Title text: you can edit this."); // hint text to encourage the user to give a title
		setFront("This is the front of the flashcard. You can edit this text.");
		setBack("This is the back of the flashcard. You can edit this text.");
		setHint("This is a hint. you can edit it or make it blank.");
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
	 */
	public FlashCard(String title, String front, String back, String hint) {
		setTitle(title);
		setFront(front);
		setBack(back);
		setHint(hint);
		setCurrentSide(true);
	}



	/**
	 * A method to simulate flipping the flash-card over. It changes the currently
	 * active side.
	 */
	public void flipFlashCard() {
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

	/**
	 * @return the front side
	 */
	public String getFront() {
		return front;
	}

	/**
	 * @param front - the front to set
	 */
	public void setFront(String front) {
		this.front = front;
	}

	/**
	 * @return the back
	 */
	public String getBack() {
		return back;
	}

	/**
	 * @param back - the back to set
	 */
	public void setBack(String back) {
		this.back = back;
	}

	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint - the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the current Side
	 */
	public boolean isCurrentSide() {
		return currentSide;
	}

	/**
	 * @param currentSide the current Side to set
	 */
	public void setCurrentSide(boolean currentSide) {
		this.currentSide = currentSide;
	}
}