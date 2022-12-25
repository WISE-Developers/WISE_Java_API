package ca.wise.api.units;

public enum PercentUnit {
	DEFAULT(-1),
	DECIMAL(0x000004c0),
	PERCENT(0x000004c1),
	DECIMAL_INVERT(0x000004c2),
	PERCENT_INVERT(0x000004c3);
	
	public final int value;
	
	PercentUnit(int value) {
		this.value = value;
	}
}
