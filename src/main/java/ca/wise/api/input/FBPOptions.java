package ca.wise.api.input;

import java.util.Collections;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The fire behaviour prediction options.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FBPOptions implements IWISESerializable {

    /**
     * Use terrain effect (optional).
     */
	@Getter @Setter
	private Boolean terrainEffect;

    /**
     * Use wind effect (optional).
     */
	@Getter @Setter
	private Boolean windEffect;

	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
}
