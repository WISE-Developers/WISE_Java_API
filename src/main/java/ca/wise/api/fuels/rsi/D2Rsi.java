package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the D-2 and D-1/D-2 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class D2Rsi implements RsiAttribute {

	@Getter @Setter
	private double threshold;
	
	@Getter @Setter
	private double scale1;
	
	@Getter @Setter
	private double scale2;

	@Override
	public String stream() {
        return "D2|" + threshold + "|" + scale1 + "|" + scale2;
	}
}
