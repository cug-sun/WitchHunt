package RumourCards;

import WitchHunt.Game;
import WitchHunt.Player;

public class EvilEye extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Evil_Eye;
	

	public EvilEye() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("You have choose player %d, on his/her turn, he/she must accuse a player other than you\n",chosenPlayer.getPlayerId());
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根

	}

}
