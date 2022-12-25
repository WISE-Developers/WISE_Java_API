package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the O-1a, O-1b, O-1AB, NZ-2, NZ-15, NZ-30, NZ-31, NZ-32, NZ-33, NZ-40, NZ-41, NZ-43, NZ-44,
 * NZ-45, NZ-46, NZ-47, NZ-50, NZ-51, NZ-52, NZ-53, NZ-55, NZ-56, NZ-57, NZ-58, NZ-62, and NZ-70 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class O1Sfc implements SfcAttribute {

	@Getter @Setter
	private double fuelLoad;

	@Override
	public String stream() {
        return "O1|" + fuelLoad;
	}
}
