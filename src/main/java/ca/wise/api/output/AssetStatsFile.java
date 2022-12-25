package ca.wise.api.output;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Statistics for asset files.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetStatsFile implements IWISESerializable {
	
	@Getter @Setter
	private String scenarioName;

    /**
     * The name of the output file.
     */
	@Getter @Setter
	private String filename;

    /**
     * The file format to export to.
     */
	@Getter @Setter
	@Builder.Default
	private int fileType = 0;

    /**
     * Should the file be streamed/uploaded to an external service after
     * it has been created?
     */
	@Getter @Setter
	@Builder.Default
	private boolean shouldStream = false;

	/**
	 * Embed critical path data inside the stats file.
	 */
	@Getter @Setter
	private Boolean criticalPathEmbedded;

	/**
	 * Export a separate file with critical paths in it.
	 */
	@Getter @Setter
    private String criticalPathPath;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(scenarioName))
			errs.add(new ValidationError("scenName", "The scenario name was not set.", this));
		if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "The export file name was not specified.", this));
		
		return errs;
	}
}
