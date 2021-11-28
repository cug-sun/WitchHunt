package RumourCards;

import WitchHunt.Game;

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
	}



	@Override
	public void huntEffect(Game game) {
		// TODO 

	}

}
