package ca.wise.api.input;

public enum FuelOptionType {
	INVALID(-1),
	GRASS_FUEL_LOAD(0),
	GRASS_CURING(1),
	PERCENT_CONIFER(2),
	PERCENT_DEAD_FIR(3),
	CROWN_BASE_HEIGHT(4),
	CROWN_FUEL_LOAD(5);
	
	public final int value;
	
	FuelOptionType(int value) {
		this.value = value;
	}
	
	public static FuelOptionType fromValue(int value) {
		for (FuelOptionType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}
}
