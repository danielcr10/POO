package model;

public enum Color {
	BLACK(1), WHITE(-1);

	private final int value;

	Color(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	
	public String toString() {
		final String colorString = super.toString();
		return colorString.substring(0, 1) + colorString.substring(1).toLowerCase();
	}
}

