package ca.wise.api.units;

public enum AreaUnit {
	DEFAULT(-1),
	MM2(0x00000100),
	CM2(0x00000101),
	M2(0x00000102),
	HECTARE(0x00000103),
	KM2(0x00000104),
	IN2(0x00000105),
	FT2(0x00000106),
	YD2(0x00000107),
	ACRE(0x00000108),
	MILE2(0x00000109);
	
	public final int value;
	
	AreaUnit(int value) {
		this.value = value;
	}
	
	public static AreaUnit fromValue(int value) {
		for (AreaUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
	
	public static AreaUnit fromVectorValue(int value) {
		switch (value) {
		case 4:
			return KM2;
		case 5:
			return M2;
		case 6:
			return MILE2;
		case 7:
			return FT2;
		case 8:
			return HECTARE;
		case 9:
			return YD2;
		case 10:
			return ACRE;
		}
		return DEFAULT;
	}

	public String toPerimString() {
		switch (this) {
		case KM2:
			return "KM2";
		case M2:
			return "M2";
		case MILE2:
			return "MI2";
		case FT2:
			return "FT2";
		case HECTARE:
			return "HA";
		case YD2:
			return "YD2";
		case ACRE:
			return "ACRE";
		default:
			return "unknown";
		}
	}
}
