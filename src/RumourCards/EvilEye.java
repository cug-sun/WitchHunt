package RumourCards;

import WitchHunt.Game;
import WitchHunt.Player;

public class EvilEye extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Evil_Eye;
	

	@Override
	public RumourCardName getCardName() {
		// TODO 
		return cardName;
	}


	@Override
	public void witchEffect(Game game) {
		//choose another player and they must accuse other player if possible
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn");
		player.chooseNextPlayer(game);
		String nextIdentity = game.getCurrentPlayer().getIdentity().toString();
		System.out.printf("The player you choose is a %s",nextIdentity);
		super.isUsed = true;
	}



	@Override
	public void huntEffect(Game game) {
		

	}

}
