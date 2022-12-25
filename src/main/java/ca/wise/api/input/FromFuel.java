package ca.wise.api.input;

public enum FromFuel {
	INVALID("-1"),
	NODATA("noData"),
	ALL("allFuels"),
	ALL_COMBUSTABLE("allCombustibleFuels");
	
	public final String value;
	
	FromFuel(String value) {
		this.value = value;
	}
	
	public static FromFuel fromValue(String value) {
		for (FromFuel stat : values()) {
			if (stat.value.equals(value))
				return stat;
		}
		return INVALID;
	}
}
