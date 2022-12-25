package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.FuelDefinition;
import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameter for the M-1, M-2, M-1/M-2, NZ-54, and NZ-69 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MixedSpread implements SpreadAttribute {
	
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
	
	@Getter @Setter
	private double pc;
	
	@Getter @Setter
	private FuelDefinition c2;
	
	@Getter @Setter
	private FuelDefinition d1;

	@Override
	public String stream() {
		String s = "Mix|" + q + "|" + bui0 + "|" + maxBe + "|" + height + "|" + cbh + "|" + cfl + "|" + pc;
		if (this.c2 == null)
			s += "|null";
		else
			s += "|" + this.c2.toString();
		if (this.d1 == null)
			s += "|null";
		else
			s += "|" + this.d1.toString();
		return s;
	}
}
