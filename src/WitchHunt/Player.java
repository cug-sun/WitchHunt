package WitchHunt;
import java.util.ArrayList;

import RumourCards.RumourCard;

public class Player {
	private ArrayList<RumourCard> hand;
	//Identity
	Identity identity;
	//whether the identity has been revealed, original value: false
	private boolean isIdRevealed;
	//points of player
	private int points;
	//pile of revealed Rumour cards
	private ArrayList<RumourCard> revealedCards;
	
	public Player() {
		this.points = 0;
		this.isIdRevealed = false;
		revealedCards = new ArrayList<RumourCard>();
	}
	
	
	//Draw a card from pile
	public void drawCard() {
		
	}
	//Accuse another player of being a Witch
	public void accused() {

	}
	/*
	 * Reveal a Rumour card from your hand and 
	 * play it face up in front of yourself, resolving
	 * its Hunt! effect
	 */
	public void revealCard() {
		
	}
	//update player's points in the game
	public void updatePoints(int points) {
		this.points += points;
	}
}
