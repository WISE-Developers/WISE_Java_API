package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * RSI calculation parameters for the C-1, C-2, C-3, C-4, C-5, C-7, D-1, D-1/D-2, S-1, S-2, S-3, Non,
 * NZ-45, NZ-47, NZ-51, NZ-52, NZ-55, NZ-56, NZ-57, NZ-58, NZ-64, and NZ-68 fuel types.
 */
@Builder
@AllArgsConstructor
public class C1Rsi implements RsiAttribute {
	
	@Override
	public String stream() {
		return "C1";
	}
}
