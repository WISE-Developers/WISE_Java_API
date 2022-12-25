package ca.wise.api.output;

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

/**
 * Export a fuel grid.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelGridFile implements IWISESerializable {

	@Getter @Setter
    private String scenName;

	@Getter @Setter
	@Builder.Default
    private String filename = "";

	@Getter @Setter
	@Builder.Default
    private boolean shouldStream = false;
    
    /**
     * The type of compression that should be used if the output type if TIF.
     */
	@Getter @Setter
	@Builder.Default
    private GridFileCompression compression = GridFileCompression.NONE;

	@Getter @Setter
    private String coverageName;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(scenName))
			errs.add(new ValidationError("scenName", "The scenario name was not set.", this));
		if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "The export file name was not specified.", this));
		
		return errs;
	}
}
