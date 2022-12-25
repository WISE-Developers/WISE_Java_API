package ca.wise.api.input;

public enum WeatherPatchType {
	INVALID(-1),
	FILE(0),
	POLYGON(2),
	LANDSCAPE(4);
	
	public final int value;
	
	WeatherPatchType(int value) {
		this.value = value;
	}
	
	public static WeatherPatchType fromValue(int value) {
		for (WeatherPatchType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	public static WeatherPatchType fromValue(String val) {
		if (val.equals("file")) {
			return FILE;
		}
		if (val.equals("polygon")) {
			return POLYGON;
		}
		if (val.equals("landscape")) {
			return LANDSCAPE;
		}
		return INVALID;
	}
}
