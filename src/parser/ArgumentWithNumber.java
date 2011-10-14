package parser;

public class ArgumentWithNumber extends Argument {
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer playerNum) {
		this.number = playerNum;
	}

	@Override
	public String toString() {
		return super.toString() + " " + number;
	}

}
