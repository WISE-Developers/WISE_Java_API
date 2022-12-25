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

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VectorFile implements IWISESerializable {
	
	/**
	 * The name of the output file (required).
	 * The file will be located below the jobs output directory. All global paths and
     * relative paths that attempt to move the file outside of this directory will be
     * removed.
	 */
	@Getter @Setter
	@Builder.Default
	private String filename = "";
	
	/**
	 * The type of vector file to output (required).
	 */
	@Getter @Setter
	@Builder.Default
	private VectorFileType type = null;
	
	/**
	 * Whether multiple perimeters are needed (based on time steps) or only the final
	 * perimeter is needed (required).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean multPerim = null;
	
	/**
	 * Start output perimeter time (required).
	 */
	@Getter @Setter
	@Builder.Default
	private String perimStartTime = null;
	
	/**
	 * End output perimeter time (required).
	 */
	@Getter @Setter
	@Builder.Default
	private String perimEndTime = null;

	/**
	 * Remove unburned islands (holes) inside of the perimeter (required).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean removeIslands = null;
	
	/**
	 * Dissolve contacting fires into a single perimeter (required).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean mergeContact = null;
	
	/**
	 * Whether the exported file should contain only the active perimeter (required).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean perimActive = null;
	
	/**
	 * The name of the scenario that this output is for (required).
	 */
	@Getter @Setter
	@Builder.Default
	private String scenarioName = "";
	
	/**
	 * Describes which metadata should be written to the vector file (required).
	 */
	@Getter @Setter
	@Builder.Default
	private VectorMetadata metadata = new VectorMetadata();
	
	/**
	 * Should the file be streamed/uploaded to an external service after
	 * it has been created? The streaming services are defined by
	 * {@link OutputStreamInfo} and helper methods such as
	 * {@link WISE#streamOutputToMqtt} or {@link WISE#streamOutputToGeoServer}.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean shouldStream = false;

	/**
	 * The name of a sub-scenario to export instead of all sub-scenarios
	 * being combined into a single output. Ignored if not using sub-scenarios.
	 */
	@Getter @Setter
	@Builder.Default
	private String subScenarioName = null;
	
	@Getter @Setter
	private String coverageName;

	/**
	 * A list of times to override for specific sub-scenarios, if sub-scenarios
	 * are being created for the referenced scenario.
	 */
	@Getter @Setter
	@Builder.Default
	private List<PerimeterTimeOverride> subScenarioOverrides = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "No output filename has been specified.", this));
		if (this.perimStartTime == null)
			errs.add(new ValidationError("lPerimStartTime", "The simulation time to begin outputting the perimeter for has not been specified.", this));
		if (this.perimEndTime == null)
			errs.add(new ValidationError("lPerimEndTime", "The simulation time to stop outputting the perimeter for has not been specified.", this));
		if (Strings.isNullOrEmpty(scenarioName))
			errs.add(new ValidationError("scenarioName", "The scenario that the output is for has not been specified.", this));
		if (this.type == null)
			errs.add(new ValidationError("type", "Invalid output file type specified.", this));
		if (this.multPerim == null)
			errs.add(new ValidationError("multPerim", "The multiple perimeter setting is invalid.", this));
		if (this.removeIslands == null)
			errs.add(new ValidationError("removeIslands", "The remove islands setting is invalid.", this));
		if (this.mergeContact == null)
			errs.add(new ValidationError("mergeContact", "The merge contacting perimeters setting is invalid.", this));
		if (this.perimActive == null)
			errs.add(new ValidationError("perimActive", "The active perimeter setting is invalid.", this));

		List<ValidationError> metadataErrs = this.metadata.checkValid();
		if (metadataErrs.size() > 0) {
			ValidationError temp = new ValidationError("metadata", "Errors in vector file metadata settings.", this);
			metadataErrs.forEach(err -> {
				temp.addChild(err);
			});
			errs.add(temp);
		}

		List<ValidationError> subscenarioErrs = new ArrayList<>();
		for (int i = 0; i < this.subScenarioOverrides.size(); i++) {
			List<ValidationError> subscenario = this.subScenarioOverrides.get(i).checkValid();
			if (subscenario.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in sub-scenario overrides at " + i + ".", this.subScenarioName);
				subscenario.forEach(err -> {
					temp.addChild(err);
				});
				subscenarioErrs.add(temp);
			}
		}
		if (subscenarioErrs.size() > 0) {
			ValidationError temp = new ValidationError("subScenarioOverrides", "Errors in sub-scenario overrides.", this);
			subscenarioErrs.forEach(err -> {
				temp.addChild(err);
			});
			errs.add(temp);
		}

		return errs;
	}
}
