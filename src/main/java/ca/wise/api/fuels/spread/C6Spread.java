package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameter for the C-6, NZ-60, NZ-61, NZ-66, NZ-67, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C6Spread implements SpreadAttribute {

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
	private double height;
	
	@Getter @Setter
	private double cbh;
	
	@Getter @Setter
	private double cfl;

	@Override
	public String stream() {
        return "C6|" + a + "|" + b + "|" + c + "|" + q + "|" + bui0 + "|" + maxBe + "|" + height + "|" + cbh + "|" + cfl;
	}
}
