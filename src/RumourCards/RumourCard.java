package RumourCards;
public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isSuccess;
	public RumourCard() {
		this.isSuccess = false;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public boolean getIsSuccess() {
		return this.isSuccess;
	}
	
}
