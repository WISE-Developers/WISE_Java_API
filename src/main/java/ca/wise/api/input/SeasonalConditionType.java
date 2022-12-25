package ca.wise.api.input;

public enum SeasonalConditionType {
	INVALID(-1),
	CURING(0),
	STANDING_GRASS(1),
	GREENUP(2);
	
	public final int value;
	
	SeasonalConditionType(int value) {
		this.value = value;
	}
	
	public static SeasonalConditionType fromValue(int value) {
		for (SeasonalConditionType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}
}
