package ca.wise.api.output;

/**
 * The filetype of the exported stats file.
 */
public enum StatsFileType {
	/**
	 * Detect the output type based on the file extension. *.json will
	 * always be {@see JSON_ROW}.
	 */
	AUTO_DETECT(0),
	/**
	 * Export to a CSV file.
	 */
	COMMA_SEPARATED_VALUE(1),
	/**
	 * Export to a JSON file with the data separated by timestep.
	 */
	JSON_ROW(2),
	/**
	 * Export to a JSON file with the data separated by statistic.
	 */
	JSON_COLUMN(3);
	
	public final int value;
	
	StatsFileType(int value) {
		this.value = value;
	}
	
	public static StatsFileType fromValue(int value) {
		for (StatsFileType type : values()) {
			if (type.value == value)
				return type;
		}
		return AUTO_DETECT;
	}
}
