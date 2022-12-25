package ca.wise.api.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntensityUnit {

	@Getter @Setter
	private EnergyUnit energy;
	
	@Getter @Setter
	private DistanceUnit distance;
}
