package ca.wise.api.units;

public enum MassUnit {
	DEFAULT(-1),
	MILLIGRAM(0x00000600),
	GRAM(0x00000601),
	KG(0x00000602),
	TONNE(0x00000603),
	OUNCE(0x00000604),
	LB(0x00000605),
	SHORT_TON(0x00000606),
	TON(0x00000607);
	
	public final int value;
	
	MassUnit(int value) {
		this.value = value;
	}
	
	public static MassUnit fromValue(int value) {
		for (MassUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
