import java.util.ArrayList;
import java.util.List;

public class Player {
    /*
     * ����name��idPlayer���������
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
	
	//�������ʵ��  Initialisation des attributs du joueur
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
	
	//set�����Լ�������
	public void setHomourCards(RumourCard rumourCards) {
		this.handCards = rumourCards;
	}
	
	//���ƺ�rumourcards��ô����������ֵӦ����ô����
	public ArrayList[] getHomourCards() {
		return handCards;
	}
	
	public void revealCards() {
		
	}
	
	public void disCards() {
		
	}
	
	//ѡ��һ�����accuse
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
