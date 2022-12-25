package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the C-6, NZ-60, NZ-61, NZ-66, NZ-67, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C6Rsi implements RsiAttribute {

	@Getter @Setter
	private double fmeMultiplier;

	@Getter @Setter
	private double fmePowAdder;

	@Getter @Setter
	private double fmePowMultiplier;

	@Getter @Setter
	private double fmeDivAdder;

	@Getter @Setter
	private double fmeDivMultiplier;

	@Getter @Setter
	private double fmePower;

	@Getter @Setter
	private double rscMultiplier;

	@Getter @Setter
	private double rscExpMultiplier;

	@Getter @Setter
	private double fmeAvg;

	@Override
	public String stream() {
        return "C6|" + fmeMultiplier + "|" + fmePowAdder + "|" + fmePowMultiplier + "|" + fmeDivAdder + "|" + fmeDivMultiplier + "|" + fmePower + "|" + rscMultiplier + "|" + rscExpMultiplier + "|" + fmeAvg;
	}
}
