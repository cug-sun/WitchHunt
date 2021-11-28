package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class BlackCat extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Black_Cat;

	@Override
	public RumourCardName getCardName() {
		// TODO 
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		//take next turn
		game.setCurrentPlayer(game.getCurrentPlayer());
		super.isUsed = true;
	}



	@Override
	public void huntEffect(Game game) {
		Player player = game.getCurrentPlayer();
		if(!game.discardPile.isEmpty()) {
			System.out.println("You can choose one of those Rumour cards:");
			for (RumourCard card : game.discardPile) {
				System.out.printf("%d. %s\n",game.discardPile.indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Which one do you want to choose");
			Scanner scanner = new Scanner(System.in);
			RumourCard choosedCard = game.discardPile.get(scanner.nextInt() - 1);
			choosedCard.isUsed = false;
			System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
			player.addHand(choosedCard);
			//current player takes next turn
			game.setCurrentPlayer(game.getCurrentPlayer());
			super.isUsed = true;
		}
		else {
			System.out.println("No cards in the discard pile,please choose another card");
			game.setCurrentPlayer(game.getCurrentPlayer());
			super.isUsed = false;
		}

	}

}
