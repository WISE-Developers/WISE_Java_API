package ca.wise.api.fuels.sfc;

import ca.wise.api.fuels.SfcAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SFC calculation parameters for the C-1 and Non fuel types.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class C1Sfc implements SfcAttribute {

	@Getter @Setter
	private double p1;
	
	@Getter @Setter
	private double p2;
	
	@Getter @Setter
	private double p3;
	
	@Getter @Setter
	private double p4;
	
	@Getter @Setter
	private double multiplier;

	@Override
	public String stream() {
        return "C1|" + p1 + "|" + p2 + "|" + p3 + "|" + p4 + "|" + multiplier;
	}
}
