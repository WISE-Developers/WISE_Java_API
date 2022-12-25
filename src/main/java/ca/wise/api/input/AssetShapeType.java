package ca.wise.api.input;

/**
 * The type of shape that is being used to describe an
 * asset.
 */
public enum AssetShapeType {
	INVALID(-1),
	FILE(0),
	POLYLINE(1),
	POLYGON(2),
	POINT(4);
	
	public final int value;
	
	AssetShapeType(int value) {
		this.value = value;
	}
	
	public static AssetShapeType fromValue(int value) {
		for (AssetShapeType stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return INVALID;
	}
}
