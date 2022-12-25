package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the S-1, S-2, S-3, NZ-63, NZ-64, NZ-65, NZ-66, and NZ-71 fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class S1Sfc implements SfcAttribute {

	@Getter @Setter
	private double p1F;
	
	@Getter @Setter
	private double p2F;
	
	@Getter @Setter
	private double p1W;
	
	@Getter @Setter
	private double p2W;
	
	@Getter @Setter
	private double ffcMultiplier;
	
	@Getter @Setter
	private double wfcMultiplier;
	
	@Getter @Setter
	private double sfcMultiplier;
	
	@Getter @Setter
	private double ffcBuiMultiplier;
	
	@Getter @Setter
	private double wfcBuiMultiplier;

	@Override
	public String stream() {
        return "S1|" + p1F + "|" + p2F + "|" + p1W + "|" + p2W + "|" + ffcMultiplier + "|" + wfcMultiplier + "|" + sfcMultiplier + "|" + ffcBuiMultiplier + "|" + wfcBuiMultiplier;
	}
}
