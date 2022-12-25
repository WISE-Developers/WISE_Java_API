package ca.wise.api.output;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.LatLon;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridFile implements IWISESerializable {
	
	/**
	 * The name of the output file (required).
	 * The file will be located below the jobs output directory.
     * All global paths and relative paths that attempt to move
     * the file outside of this directory will be removed.
	 */
	@Getter @Setter
	@Builder.Default
	private String filename = "";
	
	/**
	 * The end of the output time range (required). Will also be
	 * used as the start of the output time range if the start
	 * output time has not been specified.
	 */
	@Getter @Setter
	@Builder.Default
	private String outputTime = null;

	/**
	 * The start of the output time range (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String startOutputTime = null;

	/**
	 * The statistic that should be output (required). If the statistic is TOTAL_FUEL_CONSUMED, SURFACE_FUEL_CONSUMED,
	 * CROWN_FUEL_CONSUMED, or RADIATIVE_POWER the {@link GridFileInterpolation interpolation method} must be DISCRETIZED.
	 * Setting the output statistic to any of those values will automatically set the interpolation method.
	 * 
	 * Valid values:
	 * <ul>
	 * <li>TEMPERATURE</li>
	 * <li>DEW_POINT</li>
	 * <li>RELATIVE_HUMIDITY</li>
	 * <li>WIND_DIRECTION</li>
	 * <li>WIND_SPEED</li>
	 * <li>PRECIPITATION</li>
	 * <li>FFMC</li>
	 * <li>ISI</li>
	 * <li>FWI</li>
	 * <li>BUI</li>
	 * <li>MAX_FI</li>
	 * <li>MAX_FL</li>
	 * <li>MAX_ROS</li>
	 * <li>MAX_SFC</li>
	 * <li>MAX_CFC</li>
	 * <li>MAX_TFC</li>
	 * <li>MAX_CFB</li>
	 * <li>RAZ</li>
	 * <li>BURN_GRID</li>
	 * <li>FIRE_ARRIVAL_TIME</li>
	 * <li>HROS</li>
	 * <li>FROS</li>
	 * <li>BROS</li>
	 * <li>RSS</li>
	 * <li>ACTIVE_PERIMETER</li>
	 * <li>BURN</li>
	 * <li>BURN_PERCENTAGE</li>
	 * <li>FIRE_ARRIVAL_TIME_MIN</li>
	 * <li>FIRE_ARRIVAL_TIME_MAX</li>
	 * <li>TOTAL_FUEL_CONSUMED</li>
	 * <li>SURFACE_FUEL_CONSUMED</li>
	 * <li>CROWN_FUEL_CONSUMED</li>
	 * <li>RADIATIVE_POWER</li>
	 * <li>HFI</li>
	 * <li>HCFB</li>
	 * <li>HROS_MAP</li>
	 * <li>FROS_MAP</li>
	 * <li>BROS_MAP</li>
	 * <li>RSS_MAP</li>
	 * <li>RAZ_MAP</li>
	 * <li>FMC_MAP</li>
	 * <li>CFB_MAP</li>
	 * <li>CFC_MAP</li>
	 * <li>SFC_MAP</li>
	 * <li>TFC_MAP</li>
	 * <li>FI_MAP</li>
	 * <li>FL_MAP</li>
	 * <li>CURINGDEGREE_MAP</li>
	 * <li>GREENUP_MAP</li>
	 * <li>PC_MAP</li>
	 * <li>PDF_MAP</li>
	 * <li>CBH_MAP</li>
	 * <li>TREE_HEIGHT_MAP</li>
	 * <li>FUEL_LOAD_MAP</li>
	 * <li>CFL_MAP</li>
	 * <li>GRASSPHENOLOGY_MAP</li>
	 * <li>ROSVECTOR_MAP</li>
	 * <li>DIRVECTOR_MAP</li>
     * <li>CRITICAL_PATH</li>
     * <li>CRITICAL_PATH_PERCENTAGE</li>
	 * </ul>
	 */
	@Getter @Setter
	@Builder.Default
	private GlobalStatistics statistic = null;

	/**
	 * The interpolation method (required).
	 */
	@Getter @Setter
	private GridFileInterpolation interpMethod;
	
	/**
	 * The amount to discritize the existing grid to (optional).
	 * Only applicable if the interpolation mode is set to {@link Output_GridFileInterpolation.DISCRETIZED}.
	 * Must be in [1, 1000].
	 */
	@Getter @Setter
	@Builder.Default
	private Integer discretize = null;
	
	/**
	 * The name of the scenario that this output is for (required).
	 */
	@Getter @Setter
	@Builder.Default
	private String scenarioName = "";
	
	/**
	 * Should the file be streamed/uploaded to an external service after
	 * it has been created? The streaming services are defined by
	 * {@link OutputStreamInfo} and helper methods such as
	 * {@link WISE#streamOutputToMqtt} or {@link WISE#streamOutputToGeoServer}.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean shouldStream = false;
	
	/**
	 * If the output file is a TIF file the contents will be compressed
	 * using this method.
	 */
	@Getter @Setter
	@Builder.Default
	private GridFileCompression compression = GridFileCompression.NONE;
	
	/**
	 * Should the output file be minimized to just its bounding box (true) or should it cover the entire
	 * grid area (false).
	 */
	@Getter @Setter
	@Builder.Default
	private boolean shouldMinimize = false;
	
	/**
	 * The name of a specific sub-scenario that the output is for (if it should be for a subscenario).
	 */
	@Getter @Setter
	@Builder.Default
	private String subScenarioName = null;

	/**
	 * A list of export time overrides for different sub-scenarios that may be created
	 * for the specified scenario.
	 */
	@Getter @Setter
	@Builder.Default
	private List<ExportTimeOverride> subScenarioOverrideTimes = new ArrayList<>();
	
	@Getter @Setter
	private ExportOptions exportOptions;

	@Getter @Setter
	private String coverageName;
	
    /**
     * The name of an asset to use when creating the grid. Only valid for critical path grids.
     */
	@Getter @Setter
	private String assetName;
    
    /**
     * The index of a shape within the asset shapefile to use for critical paths instead of the entire shapefile.
     */
	@Getter @Setter
	private Integer assetIndex;
	
	/**
	 * Should 0s be used instead of NODATA where no stats exist.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean zeroForNodata = false;
    
    /**
     * Should the interior of the starting ignition polygon be excluded from the grid export.
     */
    @Getter @Setter
    @Builder.Default
    private boolean excludeInteriors = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
        if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "No output filename has been specified.", this));
        if (this.outputTime == null)
			errs.add(new ValidationError("lOutputTime", "The simulation time to export at has not been specified.", this));
        if (Strings.isNullOrEmpty(scenarioName))
			errs.add(new ValidationError("scenarioName", "The scenario that the output is for has not been specified.", this));
        if (this.interpMethod == null)
			errs.add(new ValidationError("interpMethod", "The interpolation method has not been specified.", this));
		if (this.discretize != null && (this.discretize < 1 || this.discretize > 1000))
			errs.add(new ValidationError("discritize", "The discritization must be an integer greater than 0 and less than 1001.", this));
        if (this.statistic == null)
			errs.add(new ValidationError("statistic", "The statistic to export has not been specified.", this));
		else if (this.statistic != GlobalStatistics.TEMPERATURE && this.statistic != GlobalStatistics.DEW_POINT && this.statistic != GlobalStatistics.RELATIVE_HUMIDITY &&
			this.statistic != GlobalStatistics.WIND_DIRECTION && this.statistic != GlobalStatistics.WIND_SPEED && this.statistic != GlobalStatistics.PRECIPITATION &&
			this.statistic != GlobalStatistics.FFMC && this.statistic != GlobalStatistics.ISI && this.statistic != GlobalStatistics.FWI &&
			this.statistic != GlobalStatistics.BUI && this.statistic != GlobalStatistics.MAX_FI && this.statistic != GlobalStatistics.MAX_FL &&
			this.statistic != GlobalStatistics.MAX_ROS && this.statistic != GlobalStatistics.MAX_SFC && this.statistic != GlobalStatistics.MAX_CFC &&
			this.statistic != GlobalStatistics.MAX_TFC && this.statistic != GlobalStatistics.MAX_CFB && this.statistic != GlobalStatistics.RAZ &&
			this.statistic != GlobalStatistics.BURN_GRID && this.statistic != GlobalStatistics.FIRE_ARRIVAL_TIME && this.statistic != GlobalStatistics.HROS &&
			this.statistic != GlobalStatistics.FROS && this.statistic != GlobalStatistics.BROS && this.statistic != GlobalStatistics.RSS &&
			this.statistic != GlobalStatistics.ACTIVE_PERIMETER && this.statistic != GlobalStatistics.BURN && this.statistic != GlobalStatistics.BURN_PERCENTAGE &&
			this.statistic != GlobalStatistics.FIRE_ARRIVAL_TIME_MIN && this.statistic != GlobalStatistics.FIRE_ARRIVAL_TIME_MAX && this.statistic != GlobalStatistics.TOTAL_FUEL_CONSUMED &&
			this.statistic != GlobalStatistics.SURFACE_FUEL_CONSUMED && this.statistic != GlobalStatistics.CROWN_FUEL_CONSUMED && this.statistic != GlobalStatistics.RADIATIVE_POWER &&
			this.statistic != GlobalStatistics.HFI && this.statistic != GlobalStatistics.HCFB && this.statistic != GlobalStatistics.HROS_MAP && this.statistic != GlobalStatistics.FROS_MAP &&
			this.statistic != GlobalStatistics.BROS_MAP && this.statistic != GlobalStatistics.RSS_MAP && this.statistic != GlobalStatistics.RAZ_MAP && this.statistic != GlobalStatistics.FMC_MAP &&
			this.statistic != GlobalStatistics.CFB_MAP && this.statistic != GlobalStatistics.CFC_MAP && this.statistic != GlobalStatistics.SFC_MAP && this.statistic != GlobalStatistics.TFC_MAP &&
			this.statistic != GlobalStatistics.FI_MAP && this.statistic != GlobalStatistics.FL_MAP && this.statistic != GlobalStatistics.CURINGDEGREE_MAP && this.statistic != GlobalStatistics.GREENUP_MAP &&
			this.statistic != GlobalStatistics.PC_MAP && this.statistic != GlobalStatistics.PDF_MAP && this.statistic != GlobalStatistics.CBH_MAP && this.statistic != GlobalStatistics.TREE_HEIGHT_MAP &&
			this.statistic != GlobalStatistics.FUEL_LOAD_MAP && this.statistic != GlobalStatistics.CFL_MAP && this.statistic != GlobalStatistics.GRASSPHENOLOGY_MAP &&
			this.statistic != GlobalStatistics.ROSVECTOR_MAP && this.statistic != GlobalStatistics.DIRVECTOR_MAP &&
			this.statistic != GlobalStatistics.CRITICAL_PATH && this.statistic != GlobalStatistics.CRITICAL_PATH_PERCENTAGE) {

			errs.add(new ValidationError("statistic", "Invalid statistic specified for grid export.", this));
		}
		//catch an error where the interpolation method is restricted for the output statistic
		else if ((this.statistic == GlobalStatistics.TOTAL_FUEL_CONSUMED || this.statistic == GlobalStatistics.SURFACE_FUEL_CONSUMED ||
			this.statistic == GlobalStatistics.CROWN_FUEL_CONSUMED || this.statistic == GlobalStatistics.RADIATIVE_POWER) &&
			this.interpMethod != GridFileInterpolation.DISCRETIZED) {
			errs.add(new ValidationError("interpMethod", "Interpolation method must be discretized.", this));
		}

		List<ValidationError> subscenarioErrs = new ArrayList<>();
		for (int i = 0; i < this.subScenarioOverrideTimes.size(); i++) {
			List<ValidationError> subscenario = this.subScenarioOverrideTimes.get(i).checkValid();
			if (subscenario.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in sub-scenario override times at " + i + ".", this.subScenarioOverrideTimes);
				subscenario.forEach(err -> {
					temp.addChild(err);
				});
				subscenarioErrs.add(temp);
			}
		}
		if (subscenarioErrs.size() > 0) {
			ValidationError temp = new ValidationError("subScenarioOverrideTimes", "Errors found in sub-scenario override times.", this);
			subscenarioErrs.forEach(err -> {
				temp.addChild(err);
			});
			errs.add(temp);
		}

		return errs;
	}
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ExportOptions {
		
		@Getter @Setter
		private Double resolution;
		
		@Getter @Setter
		private Double scale;
		
		@Getter @Setter
		private Boolean useFuelMap;
		
		@Getter @Setter
		private LatLon location;
		
		@Getter @Setter
		private ExportOptionsOrigin origin;
	}
	
	public enum ExportOptionsOrigin {
		FUELMAP_ORIGIN(0),
		PRECISE_PERIMETER(1),
		MINIMIZE_PERIMETER(2),
		MINIMIZE_PERIMETER2(3),
		CENTER_PERIMETER(4);
		
		public final int value;
		
		ExportOptionsOrigin(int value) {
			this.value = value;
		}
		
		public static ExportOptionsOrigin fromValue(int value) {
			for (ExportOptionsOrigin option: values()) {
				if (option.value == value)
					return option;
			}
			return FUELMAP_ORIGIN;
		}
	}
}
