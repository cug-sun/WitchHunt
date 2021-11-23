import RumourCards.AngryMol;
import RumourCards.RumourCard;
import RumourCards.RumourCardName;

public class Main {

	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Game witchHunt = new Game();
		RumourCard aCard = new AngryMol(RumourCardName.Angry_Mol);
		System.out.println(aCard.getCardName());
	}

}
