package ca.wise.api.output;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An override start and end time for a specific sub-scenario.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerimeterTimeOverride implements IWISESerializable {

	/**
	 * The name of the sub-scenario.
	 */
	@Getter @Setter
	public String subScenarioName;
	
	/**
	 * The time to use instead of {@link VectorFile#perimStartTime}.
	 */
	@Getter @Setter
	@Builder.Default
	private String startTime = null;
	
	/**
	 * The time to use instead of {@link VectorFile#perimEndTime}.
	 */
	@Getter @Setter
	@Builder.Default
	private String endTime = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		if (Strings.isNullOrEmpty(subScenarioName)) {
			return Collections.singletonList(new ValidationError("subScenarioName", "No sub-scenario has been set.", this));
		}
		return Collections.emptyList();
	}
}
