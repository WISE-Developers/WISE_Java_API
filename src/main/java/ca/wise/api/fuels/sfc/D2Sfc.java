package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the D-2 and D-1/D-2 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class D2Sfc implements SfcAttribute {

	@Getter @Setter
	private double p1;
	
	@Getter @Setter
	private double p2;
	
	@Getter @Setter
	private double power;
	
	@Getter @Setter
	private double multiplier;
	
	@Getter @Setter
	private double threshold;
	
	@Getter @Setter
	private double scale1;
	
	@Getter @Setter
	private double scale2;

	@Override
	public String stream() {
        return "D2|" + p1 + "|" + p2 + "|" + power + "|" + multiplier + "|" + threshold + "|" + scale1 + "|" + scale2;
	}
}
