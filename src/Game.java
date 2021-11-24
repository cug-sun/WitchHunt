import java.util.ArrayList;
import java.util.Scanner;

import RumourCards.RumourCard;

public class Game {
	//Pile
	private ArrayList<RumourCard> cardPile;
	//The number of players
	private int nPlayer;
	//There are 12 RumourCards
	public static final int nHumourCards = 12;
	public Game() {
		System.out.println("*****************Witch Hunt*****************");
		System.out.println("Choose the number of players (3-6)");
		Scanner scanner = new Scanner(System.in);
		//set the number of players
		this.nPlayer = scanner.nextInt();
		System.out.println(this.nPlayer + " players");
		System.out.println("Each player will have " + Game.nHumourCards/this.nPlayer + " Rumour Cards" );
		
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
	
	
	
}