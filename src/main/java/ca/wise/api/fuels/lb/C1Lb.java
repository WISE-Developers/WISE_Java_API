package ca.wise.api.fuels.lb;

import ca.wise.api.fuels.LbAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LB calculation parameters for the C-1, C-2, C-3, C-4, C-5, C-6, C-7, D-1, D-2, D-1/D-2, M-1, M-2, M-1/M-2, M-3, M-4, M-3/M-4, S-1, S-2, S-3,
 * Non, NZ-45, NZ-47, NZ-51, NZ-52, NZ-54, NZ-55, NZ-56, NZ-57, NZ-58, NZ-60, NZ-61, NZ-64, NZ-66, NZ-67, NZ-68, NZ-69, NZ-70, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C1Lb implements LbAttribute {

	@Getter @Setter
	private double init;

	@Getter @Setter
	private double multiplier;

	@Getter @Setter
	private double expMultiplier;

	@Getter @Setter
	private double power;

	@Override
	public String stream() {
        return "C1|" + init + "|" + multiplier + "|" + expMultiplier + "|" + power;
	}
}
