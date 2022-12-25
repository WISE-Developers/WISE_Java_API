package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the C-2, C-3, C-4, C-5, C-6, D-1, D-1/D-2, M-3, M-4, M-3/M-4, NZ-54, NZ-60,
 * NZ-61, NZ-67, NZ-68, and NZ-69 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C2Sfc implements SfcAttribute {

	@Getter @Setter
	private double p1;
	
	@Getter @Setter
	private double p2;
	
	@Getter @Setter
	private double power;
	
	@Getter @Setter
	private double multiplier;

	@Override
	public String stream() {
        return "C2|" + p1 + "|" + p2 + "|" + power + "|" + multiplier;
	}
}
