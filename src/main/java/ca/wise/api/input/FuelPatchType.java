package ca.wise.api.input;

public enum FuelPatchType {
	INVALID(-1),
	FILE(0),
	POLYGON(2),
	LANDSCAPE(4);
	
	public final int value;
	
	FuelPatchType(int value) {
		this.value = value;
	}
	
	public static FuelPatchType fromValue(int value) {
		for (FuelPatchType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	public static FuelPatchType fromValue(String val) {
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
