package ca.wise.api.output;

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
 * Which summary values to output.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryOutputs implements IWISESerializable {

    /**
     * Application information (optional).
     * Has a default value.
     */
	@Getter @Setter
	@Builder.Default
	private Boolean outputApplication = null;
    
    /**
     * Grid information (cell size, dimensions) (optional).
     * Has a default value.
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputGeoData = null;
    
    /**
     * Scenario Information (optional).
     * Has a default value.
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputScenario = null;
    
    /**
     * Scenario comments (optional).
     * Has a default value.
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputScenarioComments = null;
    
    /**
     * Inputs (optional).
     * Has a default value.
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputInputs = null;
    
    /**
     * Landscape information (optional).
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputLandscape = null;
    
    /**
     * Fuel patch information (optional).
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputFBPPatches = null;
    
	/**
	 * Wx patches and grids information (optional).
	 */
	@Getter @Setter
	@Builder.Default
    private Boolean outputWxPatches = null;
	
	/**
	 * Ignition information (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean outputIgnitions = null;
	
	/**
	 * Wx stream information (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean outputWxStreams = null;
	
	/**
	 * Fuel type information (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private Boolean outputFBP = null;
    
    /**
     * Wx stream data (temperature, RH, etc.) (optional).
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputWxData = null;
    
    /**
     * Asset related information (asset file reference) (optional).
     */
	@Getter @Setter
	@Builder.Default
    private Boolean outputAssetInfo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
}
