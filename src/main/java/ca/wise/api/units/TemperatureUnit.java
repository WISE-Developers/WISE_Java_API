package ca.wise.api.units;

public enum TemperatureUnit {
	DEFAULT(-1),
	KELVIN(0x00000400),
	CELSIUS(0x00000401),
	FAHRENHEIT(0x00000402),
	RANKINE(0x00000403);
	
	public final int value;
	
	TemperatureUnit(int value) {
		this.value = value;
	}
	
	public static TemperatureUnit fromValue(int value) {
		for (TemperatureUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
