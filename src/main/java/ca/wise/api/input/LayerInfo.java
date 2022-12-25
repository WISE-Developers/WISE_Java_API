package ca.wise.api.input;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayerInfo implements IWISESerializable {

	/**
	 * The name of the grid file to add.
	 */
	@Getter @Setter
	private String name;

	/**
	 * The layers index.
	 */
	@Getter @Setter
	@Builder.Default
	private int index = -1;

	/**
	 * Options for the layer when creating sub-scenarios.
	 */
	@Getter @Setter
	private Options options;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.name))
			errs.add(new ValidationError("name", "No layer reference has been set.", this));
		if (this.index < 0)
			errs.add(new ValidationError("index", "The layer index has not been set.", this));
		
		return errs;
	}
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Options {
		
		@Getter @Setter
		@Singular
		private List<String> subNames;
	}
}
