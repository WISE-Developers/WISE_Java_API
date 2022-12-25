package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameter for the O-1a, O-1b, NZ-2, NZ-15, NZ-30, NZ-31, NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44, NZ-46,
 * NZ-50, NZ-53, NZ-62, NZ-63, and NZ-65 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class O1Spread implements SpreadAttribute {

	@Getter @Setter
	private double a;
	
	@Getter @Setter
	private double b;
	
	@Getter @Setter
	private double c;
	
	@Getter @Setter
	private double q;
	
	@Getter @Setter
	private double bui0;
	
	@Getter @Setter
	private double maxBe;
	
	@Getter @Setter
	private double curingDegree;

	@Override
	public String stream() {
        return "O1|" + a + "|" + b + "|" + c + "|" + q + "|" + bui0 + "|" + maxBe + "|" + curingDegree;
	}
}
