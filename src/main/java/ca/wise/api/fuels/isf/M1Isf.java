package ca.wise.api.fuels.isf;

import ca.wise.api.fuels.IsfAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * ISF calculation parameters for the M-1, M-2, M-1/M-2, NZ-54, and NZ-69 fuel types.
 */
@Builder
@AllArgsConstructor
public class M1Isf implements IsfAttribute {

	@Override
	public String stream() {
        return "M1";
	}
}
