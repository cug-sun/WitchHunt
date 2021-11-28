package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class HookedNose extends RumourCard {

	public static RumourCardName cardName = RumourCardName.Hooked_Nose;
	public HookedNose() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		System.out.println("You can take one card from the hand of the player who accused you");
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		System.out.printf("Player %d has these Rumour cards\n",accusePlayer.getPlayerId());
		accusePlayer.displayHand();
		System.out.println("Which card do you want to take ?");
		Scanner scanner = new Scanner(System.in);
	    RumourCard choosedCard = accusePlayer.getHand().get(scanner.nextInt()-1);
	    System.out.printf("You take %s from player %d\n",choosedCard.getCardName().toString(),accusePlayer.getPlayerId());
	    accusePlayer.getHand().remove(choosedCard);
	    player.addHand(choosedCard);
	    System.out.println("You will take next turn");
	    game.setCurrentPlayer(player);
		
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn, you can take a random card from their hand and add it into your hand");
		player.chooseNextPlayer(game);
		//currentPlayer has changed to the next chosen player
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("Player %d has these Rumour cards, which one do you want to take ?\n",chosenPlayer.getPlayerId());
		chosenPlayer.displayHand();
		Scanner scanner = new Scanner(System.in);
		RumourCard chosenCard = chosenPlayer.getHand().get(scanner.nextInt()-1);
		System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
		chosenPlayer.getHand().remove(chosenCard);
		player.addHand(chosenCard);
		
	}

}
