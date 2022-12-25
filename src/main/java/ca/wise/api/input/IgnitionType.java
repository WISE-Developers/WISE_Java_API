package ca.wise.api.input;

public enum IgnitionType {
	INVALID(-1),
	FILE(0),
	POLYLINE(1),
	POLYGON(2),
	POINT(4);
	
	public final int value;
	
	IgnitionType(int value) {
		this.value = value;
	}
	
	public static IgnitionType fromValue(int value) {
		for (IgnitionType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	public static IgnitionType fromValue(String val) {
		if (val.equals("file")) {
			return FILE;
		}
		if (val.equals("polyline")) {
			return POLYLINE;
		}
		if (val.equals("polygon")) {
			return POLYGON;
		}
		if (val.equals("point")) {
			return POINT;
		}
		return INVALID;
	}
}
