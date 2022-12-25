package ca.wise.api.fuels.lb;

import ca.wise.api.fuels.LbAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LB calculation parameters for the O-1a, O-1b, O-1AB, NZ-2, NZ-15, NZ-30, NZ-31,
 * NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44, NZ-46, NZ-50, NZ-53, NZ-62, NZ-63, and NZ-65 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class O1Lb implements LbAttribute {

	@Getter @Setter
	private double init;

	@Getter @Setter
	private double power;

	@Override
	public String stream() {
        return "O1|" + init + "|" + power;
	}
}
