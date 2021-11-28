package RumourCards;

import java.util.ArrayList;
import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class PetNewt extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Pet_Newt;
	private ArrayList<RumourCard> getExsistRevealed;
	

	@Override
	public RumourCardName getCardName() {
		return cardName;
	}
	
	public boolean exsistCards(Game game) {
		int i = 0;
		for (Player  player : game.getPlayerList()) {
			if (player.equals(game.getCurrentPlayer())) {
				continue;
			}
			if (!player.getRevealedCards().isEmpty()) {
				i++;
				getExsistRevealed.addAll(player.revealedCards);
			}
		}
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}
	

	@Override
	public void witchEffect(Game game) {
		//take next turn
		game.setCurrentPlayer(game.getCurrentPlayer());
	}

	@Override
	public void huntEffect(Game game) {
		Player player = game.getCurrentPlayer();
		
		//is there any card is revealed by others
		if (exsistCards(game) == true) {
			for (RumourCard card : getExsistRevealed) {
				System.out.printf("%d. %s\n",getExsistRevealed.indexOf(card)+1, card.getCardName().toString());
				System.out.println("Take one of revealed Rumour cards into your hand");
				Scanner scanner = new Scanner(System.in);
				RumourCard choosedCard = getExsistRevealed.get(scanner.nextInt() - 1);
				System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
				player.addHand(choosedCard);
				//current player takes next turn
				game.setCurrentPlayer(game.getCurrentPlayer());
			}
		} else {
			System.out.println("Other player doesn't revealed any cards");
			game.setCurrentPlayer(game.getCurrentPlayer());
		}

	}

}
