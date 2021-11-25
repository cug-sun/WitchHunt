package RumourCards;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class TheInquisition extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.The_Inquisition;
	public TheInquisition() {
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
		Player player = game.getPlayerList().get(game.getCurrentPlayer());
		player.discard(game);
		System.out.println("You will take next turn");
		game.setCurrentPlayer(game.getCurrentPlayer());
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		//Only playable if you have been revealed as a villager
		Player player = game.getPlayerList().get(game.getCurrentPlayer());
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			System.out.println("Choose a player to play next turn");
			player.chooseNextPlayer(game);
			String nextIdentity = game.getPlayerList().get(game.getCurrentPlayer()).getIdentity().toString();
			System.out.printf("The player you choose is a %s",nextIdentity);
			
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
		}
	}

}
