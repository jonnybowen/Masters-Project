package com.example.myapplication; /**
 * A driver class to verify initial functionality of the FlashCard class.
 */

/**
 * @author jonathanbowen
 */
public class FlashCardDriver {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		
		FlashCardCollection c = new FlashCardCollection();
		FlashCard f = new FlashCard();
		FlashCard f2 = new FlashCard();
		
		
		f.setTitle("FlashCard 1");
		f.setFront("TEST TEXT FOR THE FRONT");
		f.setBack("back side data");
		f.setHint("this is HINT TEXT");
		
		f2.setTitle("Mountain Ranges.");
		f2.setFront("Located in Asia. Spans China, Nepal and India.");
		f2.setBack("Himalayas Mountain Range.");
		f2.setHint("Home to Mt Everrest.");
		
		c.addToCollection(f);
		c.addToCollection(f2);
		
		c.lookAtFlashCard(f2);
		c.takeHint(f2);
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		c.flipFlashCard(f2);
		c.lookAtFlashCard(f2);
		
		

	}

}
