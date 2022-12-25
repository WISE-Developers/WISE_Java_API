package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the M-4 and M-3/M-4 (greenup) fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class M4Rsi implements RsiAttribute {

	@Getter @Setter
	private double a;

	@Getter @Setter
	private double b;

	@Getter @Setter
	private double c;

	@Getter @Setter
	private double d1A;

	@Getter @Setter
	private double d1B;

	@Getter @Setter
	private double d1C;

	@Getter @Setter
	private double p;

	@Override
	public String stream() {
        return "M4|" + a + "|" + b + "|" + c + "|" + d1A + "|" + d1B + "|" + d1C + "|" + p;
	}
}
