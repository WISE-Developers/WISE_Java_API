package ca.wise.api.output;

import java.util.Collections;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.units.AreaUnit;
import ca.wise.api.units.DistanceUnit;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Possible metadata that could be written to vector files.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VectorMetadata implements IWISESerializable {

    /**
     * W.I.S.E. version and build date.
     */
	@Getter @Setter
	@Builder.Default
	private boolean version = false;
    
    /**
     * Scenario name.
     */
	@Getter @Setter
	@Builder.Default
	private boolean scenName = false;
    
    /**
     * Job name.
     */
	@Getter @Setter
	@Builder.Default
	private boolean jobName = false;
    
    /**
     * Ignition name(s).
     */
	@Getter @Setter
	@Builder.Default
	private boolean igName = false;
    
    /**
     * Simulated date/time.
     */
	@Getter @Setter
	@Builder.Default
	private boolean simDate = false;
    
    /**
     * Fire size (area).
     */
	@Getter @Setter
	@Builder.Default
	private boolean fireSize = false;
    
    /**
     * Total perimeter.
     */
	@Getter @Setter
	@Builder.Default
	private boolean perimTotal = false;
    
    /**
     * Active perimeter.
     */
	@Getter @Setter
	@Builder.Default
	private boolean perimActive = false;

    /**
     * Units of measure for area outputs (required).
     * @deprecated Use the global unit settings instead. Will be removed in the future.
     */
	@Getter @Setter
	@Builder.Default
	private AreaUnit areaUnit = AreaUnit.M2;

    /**
     * Units of measure for perimeter outputs (required).
     * @deprecated Use the global unit settings instead. Will be removed in the future.
     */
	@Getter @Setter
	@Builder.Default
	private DistanceUnit perimUnit = DistanceUnit.M;
    
    /**
     * Include the weather information in the output vector file.
     */
	@Getter @Setter
	@Builder.Default
	private boolean wxValues = false;
    
    /**
     * Include the FWI values in the output vector file.
     */
	@Getter @Setter
	@Builder.Default
	private boolean fwiValues = false;
    
    /**
     * Include the location of the ignition that created the perimeter in the vector file.
     */
	@Getter @Setter
	@Builder.Default
	private boolean ignitionLocation = false;
    
    /**
     * Add the max burn distance for each perimeter to the vector file.
     */
	@Getter @Setter
	@Builder.Default
	private boolean maxBurnDistance = false;
    
    /**
     * Pass ignition attributes from the input ignition file to the output perimeter.
     */
	@Getter @Setter
	@Builder.Default
	private boolean ignitionAttributes = false;
	
    /**
     * The time that the fire front reached an asset and the simulation stopped.
     */
	@Getter @Setter
	@Builder.Default
	private boolean assetArrivalTime = false;
	
    /**
     * The number of assets that were reached when the simulation stopped, if any.
     */
	@Getter @Setter
	@Builder.Default
	private boolean assetArrivalCount = false;
	
    /**
     * Add a column of 0s and 1s to indicate if a perimeter is the final perimeter of a simulation
     * or an intermediate step.
     */
	@Getter @Setter
	@Builder.Default
	private boolean identifyFinalPerimeter = false;
    
    /**
     * Simulation status when the end condition has been reached. Needed to know the reason the
     * simulation ended when using stop conditions or assets.
     */
    @Getter @Setter
    @Builder.Default
    private boolean simStatus = false;

	@Override
	public List<ValidationError> checkValid() {
        return Collections.emptyList();
	}
}
