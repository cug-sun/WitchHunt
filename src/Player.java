import java.util.ArrayList;
import java.util.List;

public class Player {
    /*
     * 加入name和idPlayer来区别玩家
     * ajouter les attributs name et idplayer pour distinguer les joueur
     */
	public String nameString;
	public int idPlayer;
	//Identity
	Identity identity;
	//whether the identity has been revealed, original value: false
	private boolean isIdRevealed;
	private int points;
	private ArrayList<RumourCard> revealedCards;

	private ArrayList<RumourCard> handCards; 
	
	//建立玩家实体  Initialisation des attributs du joueur
	public Player(int idPlayer,String nameString) {
		this.nameString = nameString;
		this.idPlayer = idPlayer;
		this.points = 0;
		this.isIdRevealed = false;
		revealedCards = new ArrayList<RumourCard>();
	}
	
	//Draw a card from pile   Distribution des cartes
	public void drawCard() {
			
	}
	
	public void setPoint(int points) {
		this.points = points;
	}
	
	public int getPoint() {
		return points;
	}
	
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	public Identity getIdentity() {
		return identity;
	}
	
	//set的是自己的手牌
	public void setHomourCards(RumourCard rumourCards) {
		this.handCards = rumourCards;
	}
	
	//手牌和rumourcards怎么关联，返回值应该怎么定义
	public ArrayList[] getHomourCards() {
		return handCards;
	}
	
	public void revealCards() {
		
	}
	
	public void disCards() {
		
	}
	
	//选择一个玩家accuse
	public void accuseWitch() {
		choosePlayer();	
	}
	
		public void choosePlayer() {
		// TODO Auto-generated method stub
		
	}
		
	public void isAccused() {
		switch (1) {
		case 1: {
			disCards();
		}
		case 2 : {
			reveal();
		}
		}
	}
	
	//update player's points in the game
	public void updatePoints(int points) {
		this.points += points;
	}

}
