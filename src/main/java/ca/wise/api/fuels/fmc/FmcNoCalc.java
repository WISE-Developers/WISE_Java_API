package ca.wise.api.fuels.fmc;

import ca.wise.api.fuels.FmcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * FMC calculation parameters for the D-1, D-2, D-1/D-2, O-1a, O-1b, O-1AB, S-1, S-2, S-3, Non, NZ-2, NZ-15, NZ-30, NZ-31,
 * NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44, NZ-46, NZ-50, NZ-53, NZ-62, NZ-63, NZ-64, NZ-65, and NZ-68 fuel types.
 */
@Builder
@AllArgsConstructor
public class FmcNoCalc implements FmcAttribute {
	
	@Override
	public String stream() {
		return "No";
	}
}
