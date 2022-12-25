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
 * Options for associating an ignition point with a scenario.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IgnitionReference implements IWISESerializable {

	/**
	 * The ID of the ignition.
	 */
	@Getter @Setter
	private String ignition;

	/**
	 * Optional sub-scenario building options.
	 */
	@Getter @Setter
	private PolylineIgnitionOptions polylineIgnitionOptions;

	/**
	 * Optional sub-scenario building options.
	 */
	@Getter @Setter
	private MultiPointIgnitionOptions multiPointIgnitionOptions;

	/**
	 * Optional sub-scenario building options.
	 */
	@Getter @Setter
	private SinglePointIgnitionOptions singlePointIgnitionOptions;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.ignition))
			errs.add(new ValidationError("ignition", "No ignition reference was set.", this));
		int count = 0;
		if (this.polylineIgnitionOptions != null) {
			count++;
			List<ValidationError> optionsErrs = this.polylineIgnitionOptions.checkValid();
			if (optionsErrs.size() > 0) {
				ValidationError temp = new ValidationError("polylineIgnitionOptions", "Errors in sub-scenario options for polyline ignitions.", this);
				optionsErrs.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
		}
		if (this.multiPointIgnitionOptions != null) {
			count++;
			List<ValidationError> optionsErrs = this.multiPointIgnitionOptions.checkValid();
			if (optionsErrs.size() > 0) {
				ValidationError temp = new ValidationError("multiPointIgnitionOptions", "Errors in sub-scenario options for multi-point ignitions.", this);
				optionsErrs.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
		}
		if (this.singlePointIgnitionOptions != null) {
			count++;
			List<ValidationError> optionsErrs = this.singlePointIgnitionOptions.checkValid();
			if (optionsErrs.size() > 0) {
				ValidationError temp = new ValidationError("singlePointIgnitionOptions", "Errors in sub-scenario options for single point ignitions.", this);
				optionsErrs.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
		}
		if (count > 1)
			errs.add(new ValidationError(null, "More than one sub-scenario type has been specified.", this));
		
		return errs;
	}
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PolylineIgnitionOptions implements IWISESerializable {

		/**
		 * A name for the sub-scenario
		 */
		@Getter @Setter
		private String name;

		/**
		 * The spacing between points (expressed in meters)
		 */
		@Getter @Setter
		private Double pointSpacing;

		/**
		 * Index of the polyline to use, or -1 to use all polylines.
		 */
		@Getter @Setter
		private Integer polyIndex;

		/**
		 * Index of the point ignition to use in the specified polyline(s), or -1 to use all points.
		 */
		@Getter @Setter
		private Integer pointIndex;

		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (Strings.isNullOrEmpty(this.name))
				errs.add(new ValidationError("name", "No name has been set for the sub-scenario.", this));
			
			return errs;
		}
	}
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MultiPointIgnitionOptions implements IWISESerializable {

		/**
		 * A name for the sub-scenario
		 */
		@Getter @Setter
		private String name;

		/**
		 * Index of the point ignition to use in the specified polyline(s), or -1 to use all points.
		 */
		@Getter @Setter
		private Integer pointIndex;

		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (Strings.isNullOrEmpty(this.name))
				errs.add(new ValidationError("name", "No name has been set for the sub-scenario.", this));
			
			return errs;
		}
	}
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SinglePointIgnitionOptions implements IWISESerializable {

		/**
		 * A name for the sub-scenario
		 */
		@Getter @Setter
		private String name;

		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (Strings.isNullOrEmpty(this.name))
				errs.add(new ValidationError("name", "No name has been set for the sub-scenario.", this));
			
			return errs;
		}
	}
}
