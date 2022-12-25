package ca.wise.api.input;

public enum HFFMCMethod {
	INVALID(-1),
	VAN_WAGNER(0),
	LAWSON(1);
	
	public final int value;
	
	HFFMCMethod(int value) {
		this.value = value;
	}
	
	public static HFFMCMethod fromValue(int value) {
		for (HFFMCMethod stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	@Override
	public String toString() {
		switch (this) {
		case LAWSON:
			return "lawson";
		case VAN_WAGNER:
			return "wagner";
		default:
			return "unknown";
		}
	}
}
