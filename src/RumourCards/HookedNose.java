package RumourCards;

import WitchHunt.Game;
import WitchHunt.Player;

public class HookedNose extends RumourCard {

	public static RumourCardName cardName = RumourCardName.Hooked_Nose;
	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根

	}

}
