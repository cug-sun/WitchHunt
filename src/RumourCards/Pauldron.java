package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class Pauldron extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Pauldron;
	

	@Override
	public RumourCardName getCardName() {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		System.out.println("You can take one card from the hand of the player who accused you");
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		
		//Randomly select the discard
		RumourCard choosedCard = player.getHand().get((int)(Math.random() * (player.getHand().size())));
	    accusePlayer.getHand().remove(choosedCard);
	    game.setCurrentPlayer(player);
		
	}

	@Override
	public void huntEffect(Game game) {
		//reveal your identity
		Player player = game.getCurrentPlayer();
		
		//determine the player's identity is revealed or not
		if (player.isRevealed() == false) {
			player.revealIdentity();
			
			//Determine the identity
			if(player.getIdentity() == Identity.Villager) {
				//choose a player to take the turn
				System.out.println("you can choose the player who will take the turn");
				player.chooseNextPlayer(game);
				
			}else {
				//left player takes the turn 默认顺时针
				int leftPlayer = player.getPlayerId() + 1;
				Player leftPlayerChoose = game.findPlayer(leftPlayer);
				game.setCurrentPlayer(leftPlayerChoose);
			}
		super.isUsed = true; 
		} else {
			//cant use Hunt! effect return to the step choose action
			System.out.println("your identity has been revealed, you cant use the Hunt! effect");
			game.setCurrentPlayer(game.getCurrentPlayer());
		super.isUsed = false;
		}
		
	}

}
