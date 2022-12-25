package ca.wise.api.fuels.cfb;

import ca.wise.api.fuels.CfbAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CFB calculation parameters for the C-1, C-2, C-3, C-4, C-5, C-6, C-7, D-1, D-1/D-2 (non-greenup), M-1, M-2, M-1/M-2,
 * M-3, M-4, M-3/M-4, O-1a, O-1b, O-1AB, S-1, S-2, S-3, Non, NZ-2, NZ-15, NZ-30, NZ-31, NZ-32, NZ-33, NZ-40, NZ-41,
 * NZ-43, NZ-44, NZ-45, NZ-46, NZ-47, NZ-50, NZ-51, NZ-52, NZ-53, NZ-54, NZ-55, NZ-56, NZ-57, NZ-58, NZ-60, NZ-61,
 * NZ-62, NZ-63, NZ-64, NZ-65, NZ-66, NZ-67, NZ-68, NZ-69, NZ-70, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C1Cfb implements CfbAttribute {

	@Getter @Setter
	private double csiMultiplier;

	@Getter @Setter
	private double csiCbhExponent;

	@Getter @Setter
	private double csiExpAdder;

	@Getter @Setter
	private double csiExpMultiplier;

	@Getter @Setter
	private double csiPower;

	@Getter @Setter
	private double rsoDiv;

	@Getter @Setter
	private double cfbExp;

	@Getter @Setter
	private double cfbPossible;

	@Override
	public String stream() {
        return "C1|" + csiMultiplier + "|" + csiCbhExponent + "|" + csiExpAdder + "|" + csiExpMultiplier + "|" + csiPower + "|" + rsoDiv + "|" + cfbExp + "|" + cfbPossible;
	}
}
