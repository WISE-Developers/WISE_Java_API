package ca.wise.api.input;

/**
 * The local time to calculate the start and stop time for burning
 * conditions based off.
 */
public enum BurningConditionRelative {
    /**
     * Use whatever the default is.
     */
    UNKNOWN(-1),
    /**
     * Compute start/stop times based off of local midnight.
     * This is the default and the original behavior.
     */
    LOCAL_MIDNIGHT(0),
    /**
     * Compute start/stop times based off of local noon.
     * Times may be negative for this type.
     */
    LOCAL_NOON(1),
    /**
     * Compute start/stop times based off of local sunrise/sunset.
     */
    SUN_RISE_SET(2);
    
    public final int value;
    
    BurningConditionRelative(int value) {
        this.value = value;
    }
    
    public static BurningConditionRelative fromValue(int value) {
        for (BurningConditionRelative cond : values()) {
            if (cond.value == value)
                return cond;
        }
        return UNKNOWN;
    }
}
