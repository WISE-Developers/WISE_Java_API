package ca.wise.api.input;

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
 * A reference to a weather stream/station used by a scenario.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationStream implements IWISESerializable {

	/**
	 * The name of the weather station.
	 */
	@Getter @Setter
	private String station;

	/**
	 * The name of the weather stream.
	 */
	@Getter @Setter
	private String stream;

	/**
	 * Is this the primary stream attached to the scenario.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean primaryStream = false;

	/**
	 * Optional settings that determine how sub-scenarios will
	 * be created if multiple weather streams are referenced.
	 */
	@Getter @Setter
	private StreamOptions streamOptions;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.station))
			errs.add(new ValidationError("station", "No weather station has been set on the weather stream reference.", this));
		if (Strings.isNullOrEmpty(this.stream))
			errs.add(new ValidationError("stream", "No weather stream has been set on the weather stream reference.", this));
		if (this.streamOptions != null) {
			List<ValidationError> streamErrs = this.streamOptions.checkValid();
			if (streamErrs.size() > 0) {
				ValidationError temp = new ValidationError("streamOptions", "Errors found in stream sub-scenario options.", this);
				streamErrs.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
		}
		
		return errs;
	}

	/**
	 * Options for creating sub-scenarios when adding weather streams to
	 * a scenario.
	 */
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StreamOptions implements IWISESerializable {

		/**
		 * The name of the sub-scenario that will be built using these options.
		 */
		@Getter @Setter
		private String name;

		/**
		 * An override for the scenario start time.
		 */
		@Getter @Setter
		private String startTime;

		/**
		 * An override for the scenario end time.
		 */
		@Getter @Setter
		private String endTime;

		/**
		 * An override for the ignition start time for any ignitions attached
		 * to this sub-scnario. Must be formatted as ISO-8601.
		 */
		@Getter @Setter
		private String ignitionTime;
		
		@Getter @Setter
		private Double windDirection;
		
		@Getter @Setter
		private Double deltaWindDirection;

		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (Strings.isNullOrEmpty(this.name))
				errs.add(new ValidationError("name", "The sub-scenario name cannot be null.", this));
			
			return errs;
		}
	}
}
