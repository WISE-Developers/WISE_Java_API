package ca.wise.api.units;

public enum DistanceUnit {
	DEFAULT(-1),
	MM(0x00000001),
	CM(0x00000002),
	M(0x00000003),
	KM(0x00000004),
	INCH(0x00000005),
	FOOT(0x00000006),
	YARD(0x00000007),
	CHAIN(0x00000008),
	MILE(0x00000009),
	NAUTICAL_MILE(0x0000000a),
	NAUTICAL_MILE_UK(0x0000000b);
	
	public final int value;
	
	DistanceUnit(int value) {
		this.value = value;
	}
	
	public static DistanceUnit fromValue(int value) {
		for (DistanceUnit unit : values()) {
			if (unit.value == value)
				return unit;
		}
		return DEFAULT;
	}
	
	public static DistanceUnit fromVectorValue(int value) {
		switch (value) {
		case 0:
			return KM;
		case 1:
			return M;
		case 2:
			return MILE;
		case 3:
			return FOOT;
		case 11:
			return YARD;
		case 12:
			return CHAIN;
		}
		return DEFAULT;
	}

	public String toPerimString() {
		switch (this) {
		case KM:
			return "KM";
		case M:
			return "M";
		case MILE:
			return "MI";
		case FOOT:
			return "FT";
		case YARD:
			return "YARD";
		case CHAIN:
			return "CHAIN";
		default:
			return "unknown";
		}
	}
}
