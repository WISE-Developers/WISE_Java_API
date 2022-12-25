package ca.wise.api.input;

import java.util.ArrayList;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The foliar moisture content options.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FMCOptions implements IWISESerializable {

    /**
     * The value for the FMC (%) override (optional). Must be between 0 and 300.
     */
	@Getter @Setter
	private Double perOverride;

    /**
     * The elevation where NODATA or no grid exists (required). Must be between 0 and 7000.
     */
	@Getter @Setter
	@Builder.Default
	private double nodataElev = -99;

    /**
     * Optional.
     */
	@Getter @Setter
	private Boolean terrain;

    /**
     * Optional.
     * @deprecated deprecated. Always true.
     */
	@Deprecated
	@Getter @Setter
	private Boolean accurateLocation;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if ((this.nodataElev < 0 && this.nodataElev != -9999 && this.nodataElev != -1 && this.nodataElev != -99) || this.nodataElev > 7000)
			errs.add(new ValidationError("nodataElev", "The elevation to use where NODATA exists is invalid.", this));
		if (this.perOverride != null && this.perOverride != -1) {
			if (this.perOverride < 0.0 || this.perOverride > 300.0)
				errs.add(new ValidationError("perOverride", "The FMC percent override was set but is invalid.", this));
		}
		
		return errs;
	}
}
