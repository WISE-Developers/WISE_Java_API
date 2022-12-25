package ca.wise.api.units;

public enum EnergyUnit {
	DEFAULT(-1),
	JOULE(0x00000700),
	KILOJOULE(0x0000070a),
	ELECTRONVOLT(0x00000701),
	ERG(0x00000702),
	FT_LB(0x00000703),
	CALORIE(0x00000704),
	KG_METRE(0x00000705),
	BTU(0x00000706),
	WATT_SECOND(0x00110707),
	WATT_HOUR(0x00130707),
	KILOWATT_SECOND(0x00110708),
	KILOWATT_HOUR(0x00130708),
	THERM(0x00000709);
	
	public final int value;
	
	EnergyUnit(int value) {
		this.value = value;
	}
	
	public static EnergyUnit fromValue(int value) {
		for (EnergyUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
