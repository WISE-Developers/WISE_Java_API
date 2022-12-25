package ca.wise.api.units;

public enum VolumeUnit {
	DEFAULT(-1),
	MM3(0x00000200),
	CM3(0x00000201),
	LITRE(0x00000202),
	M3(0x00000203),
	KM3(0x00000204),
	IN3(0x00000205),
	FT3(0x00000206),
	YD3(0x00000207),
	MILE3(0x00000208),
	UK_FL_OZ(0x00000209),
	UK_PINT(0x0000020a),
	UK_QUART(0x0000020b),
	UK_GALLON(0x0000020c),
	BUSHEL(0x0000020d),
	US_DRAM(0x0000020e),
	US_FL_OZ(0x0000020f),
	US_FL_PINT(0x00000210),
	US_FL_QUART(0x00000211),
	US_GALLON(0x00000212),
	US_FL_BARREL(0x00000213),
	US_DRY_PINT(0x00000214),
	US_DRY_QUART(0x00000215),
	US_DRY_BARREL(0x00000216);
	
	public final int value;
	
	VolumeUnit(int value) {
		this.value = value;
	}
	
	public static VolumeUnit fromValue(int value) {
		for (VolumeUnit e : values()) {
			if (e.value == value)
				return e;
		}
		return DEFAULT;
	}
}
