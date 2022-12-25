package ca.wise.api.fuels.rsi;

import ca.wise.api.fuels.RsiAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RSI calculation parameters for the NZ-70 fuel type.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConstantRsi implements RsiAttribute {

	@Getter @Setter
	private double rsi;

	@Override
	public String stream() {
        return "Const|" + rsi;
	}
}
