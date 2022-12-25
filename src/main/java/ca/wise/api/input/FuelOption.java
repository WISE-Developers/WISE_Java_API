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
 * Stores options for various fuel types including default grass fuel load,
 * grass curing, percent conifer, and percent dead fir.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuelOption implements IWISESerializable {

	/**
	 * The type of fuel to apply the option to.
	 */
	@Getter @Setter
	private String fuelType;

	/**
	 * The option that is to be applied.
	 */
	@Getter @Setter
	@Builder.Default
	private FuelOptionType optionType = FuelOptionType.INVALID;

	/**
	 * The value of the applied option.
	 */
	@Getter @Setter
	private double value;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.fuelType))
			errs.add(new ValidationError("fuelType", "No fuel type has been specified.", this));
		
		if (this.optionType == FuelOptionType.CROWN_BASE_HEIGHT) {
			if (this.value < 0.0 || this.value > 25.0)
				errs.add(new ValidationError("value", "An invalid crown base height has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.GRASS_CURING) {
			if (this.value < 0.0 || this.value > 1.0)
				errs.add(new ValidationError("value", "An invalid grass curing degree has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.GRASS_FUEL_LOAD) {
			if (this.value < 0.0 || this.value > 5.0)
				errs.add(new ValidationError("value", "An invalid grass fuel load has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.PERCENT_CONIFER) {
			if (this.value < 0.0 || this.value > 100.0)
				errs.add(new ValidationError("value", "An invalid percent conifer has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.PERCENT_DEAD_FIR) {
			if (this.value < 0.0 || this.value > 100.0)
				errs.add(new ValidationError("value", "An invalid percent dead fir has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.CROWN_FUEL_LOAD) {
			if (this.value < 0.0)
				errs.add(new ValidationError("value", "An invalid crown fuel load has been specified.", this));
		}
		else if (this.optionType == FuelOptionType.INVALID)
			errs.add(new ValidationError("optionType", "An invalid fuel option type has been specified.", this));
		
		return errs;
	}
}
