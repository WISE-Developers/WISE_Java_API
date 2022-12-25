package ca.wise.api.fuels.spread;

import ca.wise.api.fuels.SpreadAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Spread parameter for the O-1AB fuel type.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class O1abSpread implements SpreadAttribute {

	@Getter @Setter
	private double a;
	
	@Getter @Setter
	private double b;
	
	@Getter @Setter
	private double c;
	
	@Getter @Setter
	private double q;
	
	@Getter @Setter
	private double bui0;
	
	@Getter @Setter
	private double maxBe;
	
	@Getter @Setter
	private double curingDegree;
	
	@Getter @Setter
	private double o1AbStandingA;
	
	@Getter @Setter
	private double o1AbStandingB;
	
	@Getter @Setter
	private double o1AbStandingC;

	@Override
	public String stream() {
        return "O1ab|" + a + "|" + b + "|" + c + "|" + q + "|" + bui0 + "|" + maxBe + "|" + curingDegree + "|" + o1AbStandingA + "|" + o1AbStandingB + "|" + o1AbStandingC;
	}
}
