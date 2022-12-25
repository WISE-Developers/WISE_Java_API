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
 * A reference to a target that has been added to a scenario. Contains options
 * for how to handle the target.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TargetReference implements IWISESerializable {

	/**
	 * The name of the target that was added.
	 */
	@Getter @Setter
	private String name;

	/**
	 * An index of a geometry within the shape to use as the target.
	 */
	@Getter @Setter
	@Builder.Default
	private int geometryIndex = -1;

	/**
	 * An index of a point within the shape to use as the target.
	 */
	@Getter @Setter
	@Builder.Default
	private int pointIndex = -1;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.name))
			errs.add(new ValidationError("name", "No target reference has been set.", this));
		
		return errs;
	}
}
