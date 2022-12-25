package ca.wise.api.input;

public enum AssetOperation {
	STOP_AFTER_ALL(-1),
	NO_EFFECT(0),
	STOP_IMMEDIATELY(1),
	STOP_AFTER_X(2);
	
	public final int value;
	
	AssetOperation(int value) {
		this.value = value;
	}
	
	public static AssetOperation fromValue(int value) {
		for (AssetOperation stat : values()) {
			if (stat.value == value)
				return stat;
		}
		return STOP_AFTER_ALL;
	}
}
