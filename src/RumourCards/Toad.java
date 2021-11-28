package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class Toad extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Toad;
	

	@Override
	public RumourCardName getCardName() {
		// TODO
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		//take next turn
		game.setCurrentPlayer(game.getCurrentPlayer());
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
				//left player takes the turn Ä¬ÈÏË³Ê±Õë
				int leftPlayer = player.getPlayerId() + 1;
				Player leftPlayerChoose = game.findPlayer(leftPlayer);
				game.setCurrentPlayer(leftPlayerChoose);
			}
			
		} else {
			//cant use Hunt! effect return to the step choose action
			System.out.println("your identity has been revealed, you cant use the Hunt! effect");
			game.setCurrentPlayer(game.getCurrentPlayer());
		}
		
	}
}
