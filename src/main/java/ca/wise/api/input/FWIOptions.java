package ca.wise.api.input;

import java.util.Collections;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The fire weather index options.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FWIOptions implements IWISESerializable {

    /**
     * Apply spatial interpolation to FWI values (optional).
     */
	@Getter @Setter
	private Boolean fwiSpacInterp;

    /**
     * Calculate FWI values from temporally interpolated weather (optional).
     */
	@Getter @Setter
	private Boolean fwiFromSpacWeather;

    /**
     * Apply history to FWI values affected by patches, grids, etc. (optional).
     */
	@Getter @Setter
	private Boolean historyOnEffectedFWI;

    /**
     * Use burning conditions (optional).
     */
	@Getter @Setter
	private Boolean burningConditionsOn;

    /**
     * Apply spatial interpolation to FWI values (optional).
     */
	@Getter @Setter
	private Boolean fwiTemporalInterp;

	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
}
