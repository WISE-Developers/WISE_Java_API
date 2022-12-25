package ca.wise.api.units;

public enum TimeUnit {
	DEFAULT(-1),
	MICROSECOND(0x00180000),
	MILLISECOND(0x00190000),
	SECOND(0x00110000),
	MINUTE(0x00120000),
	HOUR(0x00130000),
	DAY(0x00140000),
	WEEK(0x00150000),
	MONTH(0x00160000),
	YEAR(0x00170000),
	DECADE(0x001a0000),
	CENTURY(0x001b0000);
	
	public final int value;
	
	TimeUnit(int value) {
		this.value = value;
	}
	
	public static TimeUnit fromValue(int value) {
		for (TimeUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
