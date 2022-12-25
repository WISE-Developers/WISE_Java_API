package ca.wise.api.fuels.isf;

import ca.wise.api.fuels.IsfAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ISF calculation parameters for the O-1a, O-1b, O-1AB, NZ-2, NZ-15, NZ-30, NZ-31, NZ-32, NZ-33, NZ-40, NZ-41,
 * NZ-43, NZ-44, NZ-46, NZ-50, NZ-53, NZ-62, NZ-63, and NZ-65 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class O1Isf implements IsfAttribute {

	@Getter @Setter
	private double threshold;

	@Getter @Setter
	private double f1;

	@Getter @Setter
	private double f2;

	@Getter @Setter
	private double f3;

	@Getter @Setter
	private double f4;

	@Override
	public String stream() {
        return "O1|" + threshold + "|" + f1 + "|" + f2 + "|" + f3 + "|" + f4;
	}
}
