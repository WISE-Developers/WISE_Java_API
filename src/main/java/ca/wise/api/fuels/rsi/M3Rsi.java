package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the M-3 and M-3/M-4 (non-greenup) fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class M3Rsi implements RsiAttribute {

	@Getter @Setter
	private double a;

	@Getter @Setter
	private double b;

	@Getter @Setter
	private double c;

	@Getter @Setter
	private double p;

	@Override
	public String stream() {
        return "M3|" + a + "|" + b + "|" + c + "|" + p;
	}
}
