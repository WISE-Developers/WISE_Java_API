package ca.wise.api.input;

public enum WeatherGridSector {
	INVALID(-1),
	NORTH(0),
	NORTHEAST(1),
	EAST(2),
	SOUTHEAST(3),
	SOUTH(4),
	SOUTHWEST(5),
	WEST(6),
	NORTHWEST(7);
	
	public final int value;
	
	WeatherGridSector(int value) {
		this.value = value;
	}
	
	public static WeatherGridSector fromValue(int value) {
		for (WeatherGridSector stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}
}
