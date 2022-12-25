package ca.wise.api.input;

public enum WeatherGridType {
	INVALID("-1"),
	DIRECTION("direction"),
	SPEED("speed");
	
	public final String value;
	
	WeatherGridType(String value) {
		this.value = value;
	}
	
	public static WeatherGridType fromValue(String value) {
		for (WeatherGridType stat : values()) {
			if (stat.value.equals(value))
				return stat;
		}
		return INVALID;
	}
}
