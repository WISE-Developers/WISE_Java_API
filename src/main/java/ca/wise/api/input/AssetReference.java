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
 * A reference to an asset that has been added to a scenario. Contains options
 * for how to handle the asset.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetReference implements IWISESerializable {

	/**
	 * The name of the asset that was added.
	 */
	@Getter @Setter
	private String name;

	/**
	 * The affect the asset will have on the simulation.
	 */
	@Getter @Setter
	@Builder.Default
	private AssetOperation operation = AssetOperation.STOP_AFTER_ALL;

	/**
	 * The number of assets that need to be reached before the simulation will stop. Only valid if operation is AssetOperation::STOP_AFTER_X.
	 */
	@Getter @Setter
	@Builder.Default
	private int collisionCount = -1;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.name))
			errs.add(new ValidationError("name", "No asset reference has been set.", this));
		if (this.operation == AssetOperation.STOP_AFTER_X && this.collisionCount < 1)
			errs.add(new ValidationError("collisionCount", "The collision count has not been set when the asset operation is to stop after reaching X assets.", this));
		
		return errs;
	}
}
