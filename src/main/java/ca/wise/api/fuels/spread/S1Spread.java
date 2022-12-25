package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameters for the S-1, S-2, S-3, and NZ-64 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class S1Spread implements SpreadAttribute {

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

	@Override
	public String stream() {
        return "S1|" + a + "|" + b + "|" + c + "|" + a + "|" + bui0 + "|" + maxBe;
	}
}
