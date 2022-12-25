package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameters for the C-1, C-2, C-3, C-4, C-5, C-7, and NZ-70 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C1Spread implements SpreadAttribute {

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
        return "C1|" + a + "|" + b + "|" + c + "|" + q + "|" + bui0 + "|" + maxBe + "|" + height + "|" + cbh + "|" + cfl;
	}
}
