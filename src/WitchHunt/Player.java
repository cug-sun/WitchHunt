package WitchHunt;
import java.util.ArrayList;
import java.util.Scanner;

import RumourCards.RumourCard;

public class Player {
	private int playerId;
	private ArrayList<RumourCard> hand;
	//Identity
	private Identity identity;
	//whether the identity has been revealed, original value: false
	private boolean isIdRevealed;
	//points of player
	private int points;
	//pile of revealed Rumour cards
	private ArrayList<RumourCard> revealedCards;
	
	public Player(int playerId, Identity id) {
		this.playerId = playerId;
		this.identity = id;
		this.points = 0;
		this.isIdRevealed = false;
		revealedCards = new ArrayList<RumourCard>();
	}
	//get field isIdReavealed
	public boolean isRevealed() {
		return this.isIdRevealed;
	}
	
	public Identity getIdentity() {
		return this.identity;
	}
	
	public ArrayList<RumourCard> getRevealedCards(){
		return this.revealedCards;
	}
	
	public void addHand(RumourCard card) {
		this.hand.add(card);
	}
	
	public boolean revealIdentity() {
		if(this.isIdRevealed == false) {
			this.isIdRevealed = true;
			System.out.println("Player "+ this.playerId + "'s identity card is revealed, he/she is a "+ this.identity);
			return true;
		}
		else {
			System.out.println("Player "+ this.playerId + " has been revealed as a " + this.identity);
			return false;
		}
	}
	
	public void chooseNextPlayer(Game game) {
		System.out.println("You choose which player to play next turn ?");
		Scanner scanner = new Scanner(System.in);
		int choosedId = scanner.nextInt();
		System.out.printf("You choose player %d to play next turn\n", choosedId);
		game.setCurrentPlayer(choosedId-1);
	}
	
	public void discard(Game game) {
		if(!this.hand.isEmpty()) {
			System.out.println("You have these Rumour cards:");
			for (RumourCard rumourCard : hand) {
				System.out.printf("%d. %s\n", hand.indexOf(rumourCard)+1, rumourCard.toString() );
			}
			System.out.println("Which card will you discard ?");
			Scanner scanner = new Scanner(System.in);
			RumourCard discarded = hand.remove(scanner.nextInt());
			System.out.println("You discard " + discarded.toString());
			game.discardPile.add(discarded);
		}
		else {
			System.out.println("This player doesn't have any card in hand");
		}
	}
	
	//update player's points in the game
		public void updatePoints(int points) {
			this.points += points;
		}
		public int getPoint() {
			return this.points;
		}
		
	//Accuse another player of being a Witch
	public void accuse() {

	}
	/*
	 * Reveal a Rumour card from your hand and 
	 * play it face up in front of yourself, resolving
	 * its Hunt! effect
	 */
	public void revealCard() {
		
	}
	
}
