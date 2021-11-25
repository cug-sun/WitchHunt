package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class AngryMol extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.Angry_Mol;

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		//take next turn
		game.setCurrentPlayer(game.getCurrentPlayer());
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getPlayerList().get(game.getCurrentPlayer());
		//Only playable if you have been revealed as a villager
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			//reveal another player's identity
			Scanner scanner = new Scanner(System.in);
			System.out.println("You can reveal another player's identity\nInput the player's id");
			int choosedId = scanner.nextInt()-1;
			Player choosedPlayer = game.getPlayerList().get(choosedId);
			choosedPlayer.revealIdentity();
			//calculate point
			if (choosedPlayer.getIdentity() == Identity.Villager) {
				System.out.println("You lose 2 pts, the player whose identity card you reveal plays the next turn");
				player.updatePoints(-2);
				game.setCurrentPlayer(choosedId);
			}
			else if (choosedPlayer.getIdentity() == Identity.Witch) {
				System.out.println("You gain 2 pts, you take next turn");
				player.updatePoints(2);
				game.setCurrentPlayer(game.getCurrentPlayer());
			}
			
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
		}
	}
}
