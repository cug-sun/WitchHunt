package WitchHunt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import RumourCards.AngryMol;
import RumourCards.BlackCat;
import RumourCards.Broomstick;
import RumourCards.Cauldron;
import RumourCards.DuckingStool;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.PetNewt;
import RumourCards.PointedHat;
import RumourCards.RumourCard;
import RumourCards.TheInquisition;
import RumourCards.Toad;
import RumourCards.Wart;



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
	//an array to save the accuse relation, accuse[0] is the player who accuses, accuse[1] is the accused player
	private int[] accuse = new int[2];
	public Game() {
		System.out.println("*****************Witch Hunt*****************");
		System.out.println("Choose the number of players (3-6)");
		Scanner scanner = new Scanner(System.in);
		//set the number of players
		this.nPlayer = scanner.nextInt();
		
		System.out.println(this.nPlayer + " players");
		System.out.println("Each player will have " + Game.nHumourCards/this.nPlayer + " Rumour Cards" );
		initPlayer();
	}
	//initialize the rumours cards,add them to pile
	public void initPile() {
		//Initialize the pile
		cardPile = new ArrayList<RumourCard>();
		//Initialize the discard pile
		discardPile = new ArrayList<RumourCard>();
		RumourCard angryMol = new AngryMol();
		RumourCard blackCat = new BlackCat();
		RumourCard broomstick = new Broomstick();
		RumourCard cauldron = new Cauldron();
		RumourCard duckingStool = new DuckingStool();
		RumourCard evilEye = new EvilEye();
		RumourCard hookedNose = new HookedNose();
		RumourCard petNewt = new PetNewt();
		RumourCard pointedHat = new PointedHat();
		RumourCard theInquisition = new TheInquisition();
		RumourCard toad = new Toad();
		RumourCard wart = new Wart();
		cardPile.add(angryMol);
		cardPile.add(blackCat);
		cardPile.add(broomstick);
		cardPile.add(cauldron);
		cardPile.add(duckingStool);
		cardPile.add(evilEye);
		cardPile.add(hookedNose);
		cardPile.add(petNewt);
		cardPile.add(pointedHat);
		cardPile.add(theInquisition);
		cardPile.add(toad);
		cardPile.add(wart);
		shuffleCard();
	}
	public void shuffleCard() {
		Collections.shuffle(cardPile);
	}
	public void distribute() {
		int nHand = nHumourCards/nPlayer;
		Iterator<RumourCard> it = cardPile.iterator();
		for (Player player : playerList) {
			for(int i = 0; i < nHand; i++) {
				player.addHand(it.next());
				it.remove();
			}
		}
		if(nPlayer == 5) {
			System.out.println("In a 5-player game, treat the 2 cards placed"
					+ " beside the play area at the start of the game as"
					+ " discarded cards");
			System.out.println("Discarded cards:");
			while (it.hasNext()) {
				RumourCard card = it.next();
				discardPile.add(card);
				System.out.println(card.getCardName().toString());
			}
		}
	}
	public void initPlayer() {
		playerList = new ArrayList<Player>();
		for(int i = 1; i <= nPlayer; i++) {
			Player newPlayer = new Player(i);
			playerList.add(newPlayer);		
		}
		chooseIdentity();
		//Randomly select start player
		currentPlayer = (int)(Math.random() * (nPlayer));
		System.out.println("Start from player " + (currentPlayer+1));
	}
	
	public void chooseIdentity() {
		for (Player player : playerList) {
			System.out.printf("Player %d chooses to be a 1.Villager 2.Witch\n",player.getPlayerId());
			Scanner scanner = new Scanner(System.in);
			int identity = scanner.nextInt();
			switch (identity) {
			case 1: {
				player.setIdentity(Identity.Villager);
				break;
			}
			case 2: {
				player.setIdentity(Identity.Witch);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + scanner);
			}
		}
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
		Player curPlayer = playerList.get(currentPlayer);
		System.out.printf("Player %d, it's your turn\n", currentPlayer+1);
		System.out.println("you must either:\n" +
				"1.Accuse another player of being a Witch.\nor\n"
				+ "2.Reveal a Rumour card from your hand and play it face up in front of yourself, resolving its Hunt! effect.");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextInt()) {
		case 1: {
			//Accuse another player of being a Witch
			System.out.println("You choose to accuse another player of being a Witch\n" +"Which player ?");
			displayUnaccusedPlayers();
			scanner.nextInt();
			
			//the accused player acts
			playerList.get(scanner.nextInt() - 1).beingAccuesd(curPlayer,this);
			
		}
		case 2: {
			//Reveal a Rumour card from hand, resolving its Hunt! effect
			System.out.println("You have these revealed Rumour cards:");
			curPlayer.displayHand();
			System.out.println("Which card do you want to use ?");
			RumourCard choosedCard = curPlayer.getHand().get(scanner.nextInt()-1);
			System.out.printf("You choose to use %s\n",choosedCard.getCardName().toString());
			choosedCard.huntEffect(this);
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + scanner);
		}
		//if there is a player out of game
	}
	//if there is a player out of game
	public void outOfGame() {
		for(Iterator<Player> it = playerList.iterator();it.hasNext();) {
			Player player = it.next();
			if(player.isRevealed() == true && player.getIdentity() == Identity.Witch) {
				System.out.printf("Player %d is a witch, he/she is out of game", player.getPlayerId());
				it.remove();
			}
		}
	}
	public boolean isRoundEnd() {
		if(playerList.size() == 1) {
			for(Player player: playerList) {
				System.out.printf("Player %d remains, he/she wins the round\n",player.getPlayerId());
				if(player.getIdentity() == Identity.Villager) {
					System.out.println("He/She is a villager, gains 1 point");
					player.updatePoints(1);
				}
				else if (player.getIdentity() == Identity.Witch) {
					System.out.println("He/She is a witch, gains 2 point");
					player.updatePoints(2);
				}
			}
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public void scoreBoard() {
		
	}
	//if the game is end
	public boolean isGameEnd() {
		Player min = playerList.stream().min(Comparator.comparing(player -> player.getPoint())).get();
		if(min.getPoint() >= 5) {
			return true;
		}
		else {
			return false;
		}
	}
	//display the players who haven't been accused yet
	public void displayUnaccusedPlayers() {
		for (Player player : playerList) {
			if(player.getPlayerId() == currentPlayer+1) {
				continue;
			}
			else {
				if (player.isRevealed() == false) {
					System.out.printf("Player %d\n", player.getPlayerId());
				}
				else {
					System.out.printf("Player %d is a %s", player.getPlayerId(),player.getIdentity().toString());
				}
			}
			
			
		}
	}
	
}
