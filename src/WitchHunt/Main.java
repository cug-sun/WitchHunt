package WitchHunt;
import RumourCards.AngryMol;
import RumourCards.RumourCard;
import RumourCards.RumourCardName;
import RumourCards.TheInquisition;

public class Main {

	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Game witchHunt = new Game();
		witchHunt.createPlayer();
//		Player p1 =new Player(1, Identity.Villager);
//		Player p2 = new Player(2, Identity.Witch);
//		aPlayer.revealIdentity();
//		aPlayer.revealIdentity();
//		witchHunt.playerList.get(0).revealIdentity();
		RumourCard angryMol = new TheInquisition();
		angryMol.huntEffect(witchHunt);
		
	}

}
