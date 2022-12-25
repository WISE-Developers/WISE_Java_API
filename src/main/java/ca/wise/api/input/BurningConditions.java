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
 * A condition for burning.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BurningConditions implements IWISESerializable {

	/**
	 * The date the burning condition is in effect on (required).
	 */
	@Getter @Setter
	private String date;

	/**
	 * The time of day that the burning condition starts to take effect (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String startTime = "PT0H";

	/**
	 * The time of day that the burning condition stops (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String endTime = "PT23H59M59S";

	/**
	 * The minimum FWI value that will allow burning (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private double fwiGreater = 0;

	/**
	 * The minimum wind speed that will allow burning (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private double wsGreater = 0;

	/**
	 * The maximum relative humidity that will allow burning (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private double rhLess = 100;

	/**
	 * The minimum ISI that will allow burning (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private double isiGreater = 0;

	/**
     * The local time to calculate the start time for burning
     * conditions based off.
     */
	@Getter @Setter
	@Builder.Default
	private BurningConditionRelative startTimeOffset = BurningConditionRelative.UNKNOWN;

    /**
     * The local time to calculate the stop time for burning
     * conditions based off.
     */
    @Getter @Setter
    @Builder.Default
    private BurningConditionRelative endTimeOffset = BurningConditionRelative.UNKNOWN;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (this.date == null)
			errs.add(new ValidationError("date", "No date was set to apply the burn conditions to.", this));
		if (this.startTime == null)
			errs.add(new ValidationError("startTime", "No start time of day has been set for the burn condition.", this));
		if (this.endTime == null)
			errs.add(new ValidationError("endTime", "No end time of day has been set for the burn condition.", this));
		if (this.fwiGreater < 0)
			errs.add(new ValidationError("fwiGreater", "The specified minimum FWI required for burning is invalid.", this));
		if (this.wsGreater < 0 || this.wsGreater > 200)
			errs.add(new ValidationError("wsGreater", "The specified minimum wind speed required for burning is invalid.", this));
		if (this.rhLess < 0 || this.rhLess > 100)
			errs.add(new ValidationError("rhLess", "The specified maximum relative humidity required for burning is invalid.", this));
		if (this.isiGreater < 0)
			errs.add(new ValidationError("isiGreater", "The specified minimum ISI required for burning is invalid.", this));
		
		return errs;
	}
}
