package RumourCards;
public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isUsed;
	public RumourCard() {
		this.isUsed = false;
	}
	public void setIsUsed(boolean isSuccess) {
		this.isUsed = isSuccess;
	}
	public boolean getIsUsed() {
		return this.isUsed;
	}
	
}
