package ca.wise.api.fuels;

public enum FuelTypes {
	INVALID("-1"),
	C1("C-1"),
	C2("C-2"),
	C3("C-3"),
	C4("C-4"),
	C5("C-5"),
	C6("C-6"),
	C7("C-7"),
	D1("D-1"),
	D2("D-2"),
	D12("D-1/D-2"),
	M1("M-1"),
	M2("M-2"),
	M12("M-1/M-2"),
	M3("M-3"),
	M4("M-4"),
	M34("M-3/M-4"),
	O1A("O-1a"),
	O1B("O-1b"),
	O1AB("O-1AB"),
	S1("S-1"),
	S2("S-2"),
	S3("S-3"),
	Non("Non"),
	NonFuel("Non-Fuel"),
	NZ2("NZ-2"),
	NZ15("NZ-15"),
	NZ30("NZ-30"),
	NZ31("NZ-31"),
	NZ32("NZ-32"),
	NZ33("NZ-33"),
	NZ40("NZ-40"),
	NZ41("NZ-41"),
	NZ43("NZ-43"),
	NZ44("NZ-44"),
	NZ45("NZ-45"),
	NZ46("NZ-46"),
	NZ47("NZ-47"),
	NZ50("NZ-50"),
	NZ51("NZ-51"),
	NZ52("NZ-52"),
	NZ53("NZ-53"),
	NZ54("NZ-54"),
	NZ55("NZ-55"),
	NZ56("NZ-56"),
	NZ57("NZ-57"),
	NZ58("NZ-58"),
	NZ60("NZ-60"),
	NZ61("NZ-61"),
	NZ62("NZ-62"),
	NZ63("NZ-63"),
	NZ64("NZ-64"),
	NZ65("NZ-65"),
	NZ66("NZ-66"),
	NZ67("NZ-67"),
	NZ68("NZ-68"),
	NZ69("NZ-69"),
	NZ70("NZ-70"),
	NZ71("NZ-71");
	
	public final String value;
	
	FuelTypes(String value) {
		this.value = value;
	}
	
	public static FuelTypes fromString(String value) {
		for (FuelTypes type : values()) {
			if (type.value.equals(value))
				return type;
		}
		return INVALID;
	}
}
