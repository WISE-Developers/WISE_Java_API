package ca.wise.api.units;

public enum AngleUnit {
	DEFAULT(-1),
	CARTESIAN_RADIAN(0x000004b0),
	COMPASS_RADIAN(0x010004b0),
	CARTESIAN_DEGREE(0x020004b0),
	COMPASS_DEGREE(0x030004b0),
	CARTESIAN_ARCSECOND(0x040004b0),
	COMPASS_ARCSECOND(0x050004b0);
	
	public final int value;
	
	AngleUnit(int value) {
		this.value = value;
	}
	
	public static AngleUnit fromValue(int value) {
		for (AngleUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
