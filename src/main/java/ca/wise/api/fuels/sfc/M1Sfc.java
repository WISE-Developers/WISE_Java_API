package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * SFC calculation parameters for the M-1, M-2, and M-1/M-2 fuel types.
 */
@Builder
@AllArgsConstructor
public class M1Sfc implements SfcAttribute {
	
	@Override
	public String stream() {
        return "M1";
	}
}
