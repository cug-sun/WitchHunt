package WitchHunt;
import java.util.ArrayList;
import java.util.Scanner;

import RumourCards.RumourCard;

public class Game {
	//Pile
	public ArrayList<RumourCard> cardPile;
	//discard pile
	public ArrayList<RumourCard> discardPile;
	//The number of players
	private int nPlayer;
	//There are 12 RumourCards
	public static final int nHumourCards = 12;
	ArrayList<Player> playerList;
	//index of current player in playerList
	private int currentPlayer;
	public Game() {
		System.out.println("*****************Witch Hunt*****************");
		System.out.println("Choose the number of players (3-6)");
		Scanner scanner = new Scanner(System.in);
		//set the number of players
		this.nPlayer = scanner.nextInt();
		
		System.out.println(this.nPlayer + " players");
		System.out.println("Each player will have " + Game.nHumourCards/this.nPlayer + " Rumour Cards" );
		
		//Initialize the discard pile
		discardPile = new ArrayList<RumourCard>();
		
		//Initialize the pile
		cardPile = new ArrayList<RumourCard>();
		/*
		 * 依次实例化十二张牌，加入牌堆
		 */
//		RumourCardName[] cardNames = RumourCardName.values();
//		for (int i=0; i < nHumourCards; i++) {
//			RumourCard card = new RumourCard(cardNames[i]);
//			cardPile.add(card);
//			
//		}	
	}
	
	public void createPlayer() {
		playerList = new ArrayList<Player>();
		for(int i = 1; i <= nPlayer; i++) {
			
			System.out.println("Player " + i + " chooses to be a 1.villager 2.witch");
			Scanner scanner = new Scanner(System.in);
			int id = scanner.nextInt();
			switch (id) {
			case 1: {
				Player newPlayer = new Player(i, Identity.Villager);
				playerList.add(newPlayer);
				break;
			}
			case 2: {
				Player newPlayer = new Player(i, Identity.Witch);
				playerList.add(newPlayer);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + id);
			}
			
			
		}
		//Randomly select start player
		currentPlayer = (int)(Math.random() * (nPlayer));
		System.out.println("Start from player " + (currentPlayer+1));
	}
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void setCurrentPlayer(int id) {
		this.currentPlayer = id;
	}
	
	
	//take next turn
	public void nextTurn() {
		this.currentPlayer += 1;
		if(this.currentPlayer == nPlayer) {
			this.currentPlayer = 0;
		}
	}
	public void playGame() {
		
	}
	
}
