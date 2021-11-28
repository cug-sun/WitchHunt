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

	
	
	@Override
	public void witchEffect(Game game) {
		//choose another player and they must accuse other player if possible
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn");
		player.chooseNextPlayer(game);
		
		
			
		
		super.isUsed = true;
	}



	@Override
	public void huntEffect(Game game) {
		

	}

}
