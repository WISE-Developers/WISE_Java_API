package ca.wise.api.input;

public enum GridFileType {
	INVALID(-1),
	FUEL_GRID(0),
	DEGREE_CURING(1),
	GREEN_UP(2),
	PERCENT_CONIFER(3),
	PERCENT_DEAD_FIR(4),
	CROWN_BASE_HEIGHT(5),
	TREE_HEIGHT(6),
	FUEL_LOAD(7),
	FBP_VECTOR(8);
	
	public final int value;
	
	GridFileType(int value) {
		this.value = value;
	}
	
	public static GridFileType fromValue(int value) {
		for (GridFileType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}

	@Override
	public String toString() {
		switch (this) {
		case FUEL_GRID:
	        return "fuelGrid";
		case DEGREE_CURING:
	        return "degreeCuring";
		case GREEN_UP:
	        return "greenUp";
		case PERCENT_CONIFER:
	        return "percentConifer";
		case PERCENT_DEAD_FIR:
	        return "percentDeadFir";
		case CROWN_BASE_HEIGHT:
	        return "crownBaseHeight";
		case TREE_HEIGHT:
	        return "treeHeight";
		case FUEL_LOAD:
		    return "fuelload";
		case FBP_VECTOR:
		    return "fbpvector";
		default:
			return "";
		}
	}
}
