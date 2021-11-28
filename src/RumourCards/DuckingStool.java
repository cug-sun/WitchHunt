package RumourCards;

import java.util.Iterator;
import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class DuckingStool extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Ducking_Stool;
	

	public DuckingStool() {
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
		
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		boolean duckingTool = false;
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		for (RumourCard card : chosenPlayer.getRevealedCards()) {
			if(card.getCardName() == RumourCardName.Wart) {
				duckingTool = true;
				break;
			}
		}
		if (duckingTool) {
			System.out.printf("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!\n",chosenPlayer.getPlayerId());
			game.setCurrentPlayer(player);
		}
		else {
			game.setCurrentPlayer(chosenPlayer);
		}
		
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose a player, they must reveal their identity or discard a card from their hand
		System.out.println("You can choose a player, they must reveal their identity or discard a card from their hand");
		System.out.println("You choose which player ?");
		game.displayPlayers();
		Scanner scanner = new Scanner(System.in);
		Player chosenPlayer = game.getPlayerList().get(scanner.nextInt()-1);
		boolean duckingTool = false;
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		for (RumourCard card : chosenPlayer.getRevealedCards()) {
			if(card.getCardName() == RumourCardName.Wart) {
				duckingTool = true;
				break;
			}
		}
		if (duckingTool) {
			System.out.printf("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!\n",chosenPlayer.getPlayerId());
			game.setCurrentPlayer(player);
		}
		else {
			System.out.println();
		}
		
	}

}
