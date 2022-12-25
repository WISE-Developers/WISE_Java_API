package ca.wise.api.fuels.fmc;

import ca.wise.api.fuels.FmcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FMC calculation parameters for the C-1, C-2, C-3, C-4, C-5, C-6, C-7, M-1, M-2, M-1/M-2, M-3, M-4, M-3/M-4, NZ-45,
 * NZ-47, NZ-51, NZ-52, NZ-54, NZ-55, NZ-56, NZ-57, NZ-58, NZ-60, NZ-61, NZ-66, NZ-67, NZ-69, NZ-70, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FmcCalc implements FmcAttribute {

	@Getter @Setter
	private int day0;

	@Override
	public String stream() {
		return "Calc|" + day0;
	}
}
