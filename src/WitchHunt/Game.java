package WitchHunt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;



import RumourCards.AngryMob;
import RumourCards.BlackCat;
import RumourCards.Broomstick;
import RumourCards.Pauldron;
import RumourCards.DuckingStool;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.PetNewt;
import RumourCards.PointedHat;
import RumourCards.RumourCard;
import RumourCards.RumourCardName;
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
	ArrayList<Player> outPlayerList;
	//index of current player in playerList
	private Player currentPlayer;
	//an array to save the accuse relation, accuse[0] is the playerId of player who accuses, accuse[1] is the playerId of accused player
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
	
	public void shuffleCard() {
		System.out.println("Shuffle rumour cards...");
		Collections.shuffle(cardPile);
	}
	//initialize the rumours cards,add them to pile
	public void initPile() {
		//Initialize the pile
		cardPile = new ArrayList<RumourCard>();
		//Initialize the discard pile
		discardPile = new ArrayList<RumourCard>();
		RumourCard angryMob = new AngryMob();
		RumourCard blackCat = new BlackCat();
		RumourCard broomstick = new Broomstick();
		RumourCard pauldron = new Pauldron();
		RumourCard duckingStool = new DuckingStool();
		RumourCard evilEye = new EvilEye();
		RumourCard hookedNose = new HookedNose();
		RumourCard petNewt = new PetNewt();
		RumourCard pointedHat = new PointedHat();
		RumourCard theInquisition = new TheInquisition();
		RumourCard toad = new Toad();
		RumourCard wart = new Wart();
		cardPile.add(angryMob);
		cardPile.add(blackCat);
		cardPile.add(broomstick);
		cardPile.add(pauldron);
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
	
	public void initPlayer() {
		playerList = new ArrayList<Player>();
		outPlayerList = new ArrayList<Player>();
		for(int i = 1; i <= nPlayer; i++) {
			Player newPlayer = new Player(i);
			playerList.add(newPlayer);		
		}
		chooseIdentity();
		//Randomly select start player
		currentPlayer = playerList.get((int)(Math.random() * (nPlayer)));
		System.out.println("Start from player " + (currentPlayer.getPlayerId()));
	}
	
	
	public void distribute() {
		System.out.println("Distribute rumour cards...");
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
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public int[] getAccuse() {
		return this.accuse;
	}
	
	public ArrayList<RumourCard> getDiscardPile(){
		return this.discardPile;
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}
	
	public void playGame() {

		while(true) {
			playTurn();
			outOfGame();
			if (isRoundEnd()) {
				if(isGameEnd()) {
					break;
				}
				else {
					//reset
					playerList.addAll(outPlayerList);
					outPlayerList.clear();
					Collections.sort(playerList, new Comparator<Player>() {
						public int compare(Player p1, Player p2) {
						    return Integer.compare(p1.getPlayerId(), p2.getPlayerId());
						};
					});	
					//score board
					scoreBoard();
					System.out.println("Start new round...\n");
					chooseIdentity();
					for (Player player : playerList) {
						player.getHand().clear();
						player.getRevealedCards().clear();
						player.setEvilEye(false);
						player.setIsRevealed(false);
					}
					initPile();
					distribute();
				}
				
			}
		} 
		
		
	}
	
	public void playTurn() {
		
		System.out.printf("Player %d, it's your turn\n", currentPlayer.getPlayerId());
		System.out.println("you must either:\n" +
				"1.Accuse another player of being a Witch.\nor\n"
				+ "2.Reveal a Rumour card from your hand and play it face up in front of yourself, resolving its Hunt! effect.");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextInt()) {
		case 1: {
			//Accuse another player of being a Witch
			
			
			/*
			 * if this player is chosen by Evil Eye,he must accuse a player other than the player who accuses him
			 */
			System.out.println("You choose to accuse another player of being a Witch\nWhich player ?");
			displayUnaccusedPlayers();
			int choosedId = scanner.nextInt();
			Player accusedPlayer = findPlayer(choosedId);
			
			//this player can or cant be accused
			if(accusedPlayer.isRevealed() == true) {
				System.out.println("this player'identity is revealed, you cant accuse him/her");
				setCurrentPlayer(currentPlayer);
			}else {
			
			
			accuse[0] = currentPlayer.getPlayerId();
			accuse[1] = accusedPlayer.getPlayerId();
			//the accused player acts
			accusedPlayer.beingAccuesd(this);
			//setCurrentPlayer(accusedPlayer);
			}
			break;
			
		}
		case 2: {
			//Reveal a Rumour card from hand, resolving its Hunt! effect
			if(!currentPlayer.getHand().isEmpty()) {
				System.out.println("You have these Rumour cards:");
				currentPlayer.displayHand();
				System.out.println("Which card do you want to use ?");
				RumourCard choosedCard = currentPlayer.getHand().get(scanner.nextInt()-1);
				System.out.printf("You choose to use %s\n",choosedCard.getCardName().toString());
				choosedCard.huntEffect(this);
				if (choosedCard.getIsUsed() == true) {
					currentPlayer.getHand().remove(choosedCard);
					//after using Black Cat, discard it
					if (choosedCard.getCardName() == RumourCardName.Black_Cat) {
						discardPile.add(choosedCard);
					}
					else {
						currentPlayer.getRevealedCards().add(choosedCard);
					}
				}
				break;
			}
			else {
				System.out.println("You don't have any card in hand! You must accuse another player of being a witch");
				setCurrentPlayer(currentPlayer);
				break;
			}
			
			
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
				System.out.printf("Player %d is a witch, he/she is out of game\n", player.getPlayerId());
				it.remove();
				outPlayerList.add(player);
				
			}
		}
	}
	public boolean isRoundEnd() {
		int nUnrevealedPlayer = 0;
		Player roundWinner = null;
		for (Player player : playerList) {
			if(player.isRevealed() == false) {
				nUnrevealedPlayer ++;
				roundWinner = player;
			}
		}
		if (nUnrevealedPlayer == 1) {
			System.out.printf("This round ends, player %d remains with a unrevealed identity card, he/she wins the round\n",roundWinner.getPlayerId());
			if(roundWinner.getIdentity() == Identity.Villager) {
				System.out.println("He/She is a villager, gains 1 point");
				roundWinner.updatePoints(1);
			}
			else if (roundWinner.getIdentity() == Identity.Witch) {
				System.out.println("He/She is a witch, gains 2 point");
				roundWinner.updatePoints(2);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//if the game is end
	public boolean isGameEnd() {
		Player max = playerList.stream().max(Comparator.comparing(player -> player.getPoint())).get();
		if(max.getPoint() >= 5) {
			System.out.printf("Game ends, player %d wins, he has %d points\n",max.getPlayerId(),max.getPoint());
			setCurrentPlayer(max);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void scoreBoard() {
		System.out.println("Scoreboard:");
		for (Player player : playerList) {
			System.out.printf("Player %d: %d points\n", player.getPlayerId(),player.getPoint());
		}
	}
	
	//display all the players except current player
	public void displayPlayers() {
		for (Player player : playerList) {
			if (player == currentPlayer) {
				continue;
			}
			else {
				System.out.printf("Player %d\n", player.getPlayerId());
			}
		}
	}
	//display the players who haven't been accused to reveal identity card
	public void displayUnaccusedPlayers() {
		for (Player player : playerList) {
			if(player == currentPlayer) {
				continue;
			}
			else {
				if (player.isRevealed() == false) {
					System.out.printf("Player %d\n", player.getPlayerId());
				}
				else {
					System.out.printf("Player %d has been revealed as a %s\n", player.getPlayerId(),player.getIdentity().toString());
				}
			}
			
			
		}
	}
	
	//find a player by his playerId
	public Player findPlayer(int id) {
		Player target = null;
		boolean find = false;
		Iterator<Player> iterator = playerList.iterator();
		while (iterator.hasNext()) {
			target = iterator.next();
			if (target.getPlayerId()==id) {
				find = true;
				break;
			}
		}
		if (find) {
			return target;
			
		}
		else {
			return null;
		}
		
	}
	
	
	
}
