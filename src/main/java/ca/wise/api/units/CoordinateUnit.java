package ca.wise.api.units;

public enum CoordinateUnit {
	DEFAULT(-1),
	DEGREE(0x00000800),
	DEGREE_MINUTE(0x00000801),
	DEGREE_MINUTE_SECOND(0x00000802),
	UTM(0x00000803),
	RELATIVE_DISTANCE(0x00000804);
	
	public final int value;
	
	CoordinateUnit(int value) {
		this.value = value;
	}
	
	public static CoordinateUnit fromValue(int value) {
		for (CoordinateUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
