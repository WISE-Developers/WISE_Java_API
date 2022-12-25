package ca.wise.api.input;

public enum WeatherPatchOperation {
	INVALID(-1),
	EQUAL(0),
	PLUS(1),
	MINUS(2),
	MULTIPLY(3),
	DIVIDE(4);
	
	public final int value;
	
	WeatherPatchOperation(int value) {
		this.value = value;
	}
	
	public static WeatherPatchOperation fromValue(int value) {
		for (WeatherPatchOperation stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	@Override
	public String toString() {
		switch (this) {
		case EQUAL:
			return "equal";
		case PLUS:
			return "plus";
		case MINUS:
			return "minus";
		case MULTIPLY:
			return "multiply";
		case DIVIDE:
			return "divide";
		default:
			return "";
		}
	}
}
