package ca.wise.api.input;

public enum FuelBreakType {
	INVALID(-1),
	FILE(0),
	POLYLINE(1),
	POLYGON(2);
	
	public final int value;
	
	FuelBreakType(int value) {
		this.value = value;
	}
	
	public static FuelBreakType fromValue(int value) {
		for (FuelBreakType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	public static FuelBreakType fromValue(String val) {
		if (val.equals("file")) {
			return FILE;
		}
		if (val.equals("polyline")) {
			return POLYLINE;
		}
		if (val.equals("polygon")) {
			return POLYGON;
		}
		return INVALID;
	}
}
