package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameter for the NZ-45, NZ-47, NZ-51, NZ-52, NZ-55, NZ-56, NZ-57, and NZ-58 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NzSpread implements SpreadAttribute {

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
        return "Nz|" + a + "|" + b + "|" + c + "|" + q + "|" + bui0 + "|" + maxBe + "|" + height + "|" + cbh + "|" + cfl;
	}
}
