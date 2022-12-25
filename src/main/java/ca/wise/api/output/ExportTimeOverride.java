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
 * Override the output time for a specific sub-scenario.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportTimeOverride implements IWISESerializable {

	/**
	 * The name of the sub-scenario that the override time is for.
	 */
	@Getter @Setter
	@Builder.Default
	private String subScenarioName = null;
	
	/**
	 * The export time to use instead of the one defined in the {@link Output_GridFile} class.
	 */
	@Getter @Setter
	@Builder.Default
	private String exportTime = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		if (Strings.isNullOrEmpty(subScenarioName))
			return Collections.singletonList(new ValidationError("subScenarioName", "The sub-scenario that the override is for has not been set.", this));
		return Collections.emptyList();
	}
}
