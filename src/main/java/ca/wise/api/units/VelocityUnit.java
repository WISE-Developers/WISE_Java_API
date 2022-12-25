package ca.wise.api.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VelocityUnit {

	@Getter @Setter
	@Builder.Default
	private DistanceUnit distance = DistanceUnit.DEFAULT;
	
	@Getter @Setter
	@Builder.Default
	private TimeUnit time = TimeUnit.DEFAULT;
}
