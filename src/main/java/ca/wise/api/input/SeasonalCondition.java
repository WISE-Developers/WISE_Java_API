package ca.wise.api.input;

import java.util.ArrayList;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Seasonal burning conditions.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonalCondition implements IWISESerializable {

	@Getter @Setter
	@Builder.Default
	private SeasonalConditionType type = SeasonalConditionType.INVALID;
	
	@Getter @Setter
	private String startTime;
	
	@Getter @Setter
	private boolean active;
	
	@Getter @Setter
	private Double value;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (type == SeasonalConditionType.CURING || type == SeasonalConditionType.STANDING_GRASS) {
			if (value == null)
				errs.add(new ValidationError("value", "No value set for seasonal condition.", this));
		}
		else if (type != SeasonalConditionType.GREENUP)
			errs.add(new ValidationError("type", "Invalid seasonal condition type.", this));
		
		return errs;
	}
}
