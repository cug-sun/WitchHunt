package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Player;

public class PointedHat extends RumourCard {

	public PointedHat() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return null;
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
		}
		else {
			System.out.println("You don't have any revealed card");
		}
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根

	}

}
