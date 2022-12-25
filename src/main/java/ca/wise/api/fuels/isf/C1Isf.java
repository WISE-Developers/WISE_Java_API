package ca.wise.api.fuels.isf;

import ca.wise.api.fuels.IsfAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * ISF calculation parameters for the C-1, C-2, C-3, C-4, C-5, C-6, C-7, D-1, D-2, D-1/D-2, S-1, S-2, S-3,
 * Non, NZ-45, NZ-47, NZ-51, NZ-52, NZ-55, NZ-56, NZ-57, NZ-58, NZ-60, NZ-61, NZ-64, NZ-66, NZ-67, NZ-68, NZ-70, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
public class C1Isf implements IsfAttribute {
	
	@Override
	public String stream() {
        return "C1";
	}
}
