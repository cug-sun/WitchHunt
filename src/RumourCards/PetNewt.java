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
		super.isUsed = true;
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
				choosedCard.isUsed = false;
				System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
				player.addHand(choosedCard);
				//choose the next player
				System.out.println("Choose a player to play next turn");
				player.chooseNextPlayer(game);
				String nextIdentity = game.getCurrentPlayer().getIdentity().toString();
				System.out.printf("The player you choose is a %s",nextIdentity);
			}
		super.isUsed = true;
		} else {
			System.out.println("Other player doesn't revealed any cards");
			game.setCurrentPlayer(game.getCurrentPlayer());
		super.isUsed = false;
		}

	}

}
