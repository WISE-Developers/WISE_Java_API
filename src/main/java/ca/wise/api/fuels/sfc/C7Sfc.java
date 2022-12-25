package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the C-7 fuel type.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C7Sfc implements SfcAttribute {

	@Getter @Setter
	private double p1F;
	
	@Getter @Setter
	private double p2F;
	
	@Getter @Setter
	private double p3F;
	
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

	@Override
	public String stream() {
        return "C7|" + p1F + "|" + p2F + "|" + p3F + "|" + p1W + "|" + p2W + "|" + ffcMultiplier + "|" + wfcMultiplier + "|" + sfcMultiplier;
	}
}
