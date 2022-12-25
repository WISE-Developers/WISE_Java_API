package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Spread parameter for non fuel types.
 */
@Builder
@NoArgsConstructor
public class NonSpread implements SpreadAttribute {
	
	@Override
	public String stream() {
		return "Non";
	}
}
