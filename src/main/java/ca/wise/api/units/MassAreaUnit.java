package ca.wise.api.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MassAreaUnit {

	@Getter @Setter
	private MassUnit mass;
	
	@Getter @Setter
	private AreaUnit area;
}
