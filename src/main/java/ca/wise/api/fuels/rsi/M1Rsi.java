package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the M-1, M-2, M-1/M-2, NZ-54, and NZ-69 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class M1Rsi implements RsiAttribute {

	@Getter @Setter
	private double p1;

	@Override
	public String stream() {
        return "M1|" + p1;
	}
}
