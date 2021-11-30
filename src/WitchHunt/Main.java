package WitchHunt;



public class Main {

	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Game witchHunt = new Game();
//		witchHunt.initPlayer();
//		Player p1 =new Player(1, Identity.Villager);
//		Player p2 = new Player(2, Identity.Witch);
//		aPlayer.revealIdentity();
//		aPlayer.revealIdentity();
//		witchHunt.playerList.get(0).revealIdentity();
//		RumourCard angryMol = new TheInquisition();
//		angryMol.
		//witchHunt.displayPlayers();
//		witchHunt.playGame();
		witchHunt.initPile();
		witchHunt.distribute();
//		witchHunt.playTurn();
//		witchHunt.outOfGame();
//		witchHunt.playGame();
		witchHunt.getCurrentPlayer().chooseNextPlayer(witchHunt);

		
	}

}
