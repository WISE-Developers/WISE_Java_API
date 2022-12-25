package ca.wise.api.fuels.fl;

import ca.wise.api.fuels.FlameLengthAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Flame length calculation parameters for the O-1a, O-1b, O-1AB, S-1, S-2, S-3, Non, NZ-2, NZ-15, NZ-30,
 * NZ-31, NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44, NZ-46, NZ-50, NZ-53, NZ-62, NZ-63, NZ-64, and NZ-65 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alexander82FlameLength implements FlameLengthAttribute {

	@Getter @Setter
	private double p1;

	@Getter @Setter
	private double p2;

	@Override
	public String stream() {
        return "Alex|" + p1 + "|" + p2;
	}
}
