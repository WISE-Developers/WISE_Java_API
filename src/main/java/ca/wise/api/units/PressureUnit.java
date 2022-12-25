package ca.wise.api.units;

public enum PressureUnit {
	DEFAULT(-1),
	KPA(0x00000500),
	PSI(0x00000501),
	BAR(0x00000502),
	ATM(0x00000503),
	TORR(0x00000504);
	
	public final int value;
	
	PressureUnit(int value) {
		this.value = value;
	}
}
