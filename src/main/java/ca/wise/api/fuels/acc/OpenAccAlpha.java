package ca.wise.api.fuels.acc;

import ca.wise.api.fuels.AccAlphaAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ISF calculation parameters for the C-1, O-1a, O-1b, O-1AB, S-1, S-2, S-3, Non, NZ-2, NZ-15, NZ-30, NZ-31,
 * NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44, NZ-46, NZ-50, NZ-53, NZ-62, NZ-63, NZ-64, NZ-65, and NZ-70 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccAlpha implements AccAlphaAttribute {

	@Getter @Setter
	private double init;

	@Override
	public String stream() {
        return "Open|" + init;
	}
}
