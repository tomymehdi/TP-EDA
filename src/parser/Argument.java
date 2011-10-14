package parser;

public class Argument {

	public boolean isOn;

	public Argument() {
		this.isOn = false;
	}

	public boolean getIsOn() {
		return isOn;
	}

	public void setIsOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	@Override
	public String toString() {
		return String.valueOf(isOn);
	}
}
