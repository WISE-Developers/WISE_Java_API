package ca.wise.api.fuels.isf;

import ca.wise.api.fuels.IsfAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ISF calculation parameters for the M-3, M-4, and M-3/M-4 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class M3M4Isf implements IsfAttribute {

	@Getter @Setter
	private double a;

	@Getter @Setter
	private double b;

	@Getter @Setter
	private double c;

	@Override
	public String stream() {
        return "M3M4|" + a + "|" + b + "|" + c;
	}
}
