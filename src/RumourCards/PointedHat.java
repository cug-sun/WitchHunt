package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class PointedHat extends RumourCard {

	public static RumourCardName cardName = RumourCardName.Pointed_Hat;

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getPlayerList().get(game.getCurrentPlayer());
		if(!player.getRevealedCards().isEmpty()) {
			System.out.println("You have these revealed Rumour cards:");
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			Scanner scanner = new Scanner(System.in);
			RumourCard choosedCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
			player.addHand(choosedCard);
			//current player takes next turn
			game.setCurrentPlayer(game.getCurrentPlayer());
		}
		else {
			System.out.println("You don't have any revealed card");
		}
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getPlayerList().get(game.getCurrentPlayer());
		if(!player.getRevealedCards().isEmpty()) {
			System.out.println("You have these revealed Rumour cards:");
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			Scanner scanner = new Scanner(System.in);
			RumourCard choosedCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
			player.addHand(choosedCard);
			//Choose another player to play next turn
			player.chooseNextPlayer(game);
		}
		else {
			System.out.println("You don't have any revealed card");
		}
	}

}
