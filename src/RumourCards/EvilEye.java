package RumourCards;

import WitchHunt.Game;
import WitchHunt.Player;

public class EvilEye extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Evil_Eye;
	private boolean exsistOther;

	@Override
	public RumourCardName getCardName() {
		// TODO 
		return cardName;
	}

	public void otherPlayer(Game game) {
		int i = 0;
		for (Player  player : game.getPlayerList()) {
			if (player.equals(game.getCurrentPlayer() || player.)) {
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
		//choose another player and they must accuse other player if possible
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn");
		player.chooseNextPlayer(game);
		String nextIdentity = game.getCurrentPlayer().getIdentity().toString();
		System.out.printf("The player you choose is a %s",nextIdentity);
		
		for (Player  player2 : game.getPlayerList()) {
			if (player2.equals(game.getCurrentPlayer())) {
				continue;
			}
			
		
		super.isUsed = true;
	}



	@Override
	public void huntEffect(Game game) {
		

	}

}
