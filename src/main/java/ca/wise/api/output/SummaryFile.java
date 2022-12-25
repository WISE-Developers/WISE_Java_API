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
 * Output a summary for the specified scenario.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryFile implements IWISESerializable {

	/**
	 * Which summary values should be output upon completion of the scenario.
	 */
	@Getter @Setter
	@Builder.Default
	private SummaryOutputs outputs = new SummaryOutputs();
	
	/**
	 * Should the file be streamed/uploaded to an external service after
	 * it has been created? The streaming services are defined by
	 * {@link OutputStreamInfo} and helper methods such as
	 * {@link WISE#streamOutputToMqtt} or {@link WISE#streamOutputToGeoServer}.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean shouldStream = false;

	@Getter @Setter
	private String scenName;
	
	/**
	 * The name of the output file.
	 */
	@Getter @Setter
	private String filename;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "The output filename was not specified.", this));
		if (Strings.isNullOrEmpty(scenName))
			errs.add(new ValidationError("scenName", "The scenario that the output is for has not been specified.", this));
		List<ValidationError> outputsErrs = this.outputs.checkValid();
		if (outputsErrs.size() > 0) {
			ValidationError temp = new ValidationError("outputs", "Errors found in the summary output settings.", this);
			outputsErrs.forEach(err -> {
				temp.addChild(err);
			});
			errs.add(temp);
		}
		return errs;
	}
}
