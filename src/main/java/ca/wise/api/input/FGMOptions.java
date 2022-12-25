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
 * The fire growth model options.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FGMOptions implements IWISESerializable {

    /**
     * The maximum time step during acceleration (optional). This value must be <= 5min.
     */
	@Getter @Setter
	private String maxAccTs;

    /**
     * The distance resolution (required). Must be between 0.2 and 10.0.
     */
	@Getter @Setter
	private double distRes;

    /**
     * The perimeter resolution (required). Must be between 0.2 and 10.0.
     */
	@Getter @Setter
	private double perimRes;

    /**
     * Minimum Spreading ROS (optional). Must be between 0.0000001 and 1.0.
     */
	@Getter @Setter
	private Double minimumSpreadingRos;

    /**
     * Whether to stop the fire spread when the simulated fire reaches the boundary of the grid data (required).
     */
	@Getter @Setter
	private boolean stopAtGridEnd;

    /**
     * Whether breaching is turned on or off (required).
     */
	@Getter @Setter
	private boolean breaching;

    /**
     * Whether using the dynamic spatial threshold algorithm is turned on or off (optional).
     */
	@Getter @Setter
	private boolean dynamicSpatialThreshold;

    /**
     * Whether the spotting model should be activated (optional).
     */
	@Getter @Setter
	private boolean spotting;

    /**
     * Whether internal/hidden time steps are retained.
     */
	@Getter @Setter
	private boolean purgeNonDisplayable;

	/**
	 * How much to nudge ignitions to perform probabilistic analyses on ignition location.
     * Primarily used when ignition information is not 100% reliable.
     * Must be between -250 and 250.
	 */
	@Getter @Setter
	private Double dx;

	/**
	 * How much to nudge ignitions to perform probabilistic analyses on ignition location.
     * Primarily used when ignition information is not 100% reliable.
     * Must be between -250 and 250.
	 */
	@Getter @Setter
	private Double dy;

	/**
	 * How much to nudge ignitions to perform probabilistic analyses on ignition location and start time.
     * Primarily used when ignition information is not 100% reliable.
	 */
	@Getter @Setter
	private String dt;

    /**
     * How much to nudge wind direction to perform probabilistic analyses on weather.
     * Applied after all patches and grids, and does not recalculate any FWI calculations.
     * Applied before any FBP calculations.
     * Provided in compass degrees, -360 to 360 is acceptable.
     * Applied to both simulations, and to instantaneous calculations as shown on the map trace view query, for consistency.
     * Primarily used when weather information does not have the expected fidelity.
     */
	@Getter @Setter
	private Double dwd;

    /**
     * What to change the wind direction to, to perform probabilistic analyses on weather.
     * Applied after all patches and grids, and does not recalculate any FWI calculations.
     * Applied before any FBP calculations.
     * Provided in compass degrees, 0 to 360 is acceptable.
     * Applied to both simulations, and to instantaneous calculations as shown on the map trace view query, for consistency.
     */
	@Getter @Setter
	private Double owd;

    /**
     * Used to calculate grid-based statistics without modelling a fire. Where-as various inputs will determine the dimensions and
     * orientation of the ellipse representing fire growth at a location, this value determines the direction of vector growth out
     * of the defined ellipse. In this mode, provided FBP equationsa are used. oVD stands for overrideVectorDirection.
     * What to define (or change) the vector direction to.
     * Applied after all patches and grids, and does not recalculate any FWI calculations.
     * Provided in compass degrees, 0 to 360 is acceptable.
     */
	@Getter @Setter
	private Double ovd;

    /**
     * Used to calculate grid-based statistics without modelling a fire.  Where-as various inputs will determine the dimensions and
     * orientation of the ellipse representing fire growth at a location, this value determines the direction of vector growth out
     * of the defined ellipse.  In this mode, provided FBP equations are used.  dVD stands for deltaVectorDirection.
     * How much to nudge wind direction to perform probabilistic analyses on weather.
     * Applied after all patches and grids, and does not recalculate any FWI calculations.
     * Provided in compass degrees, -360 to 360 is acceptable.
     */
	@Getter @Setter
	private Double dvd;

    /**
     * Whether the growth percentile value is applied (optional).
     */
	@Getter @Setter
	private Boolean growthPercentileApplied;

    /**
     * Growth percentile, to apply to specific fuel types (optional).
     */
	@Getter @Setter
	private Double growthPercentile;

    /**
     * Suppress adding new points to polygons in tight concave locations.
     */
	@Getter @Setter
	private Boolean suppressTightConcave;

    /**
     * Should non-fuel locations be used as vector breaks.
     * @deprecated
     */
	@Deprecated
	@Getter @Setter
	private Boolean nonFuelsAsVectorBreaks;

    /**
     * Should non-fuel locations be converted to vector breaks.
     * @deprecated
     */
    @Deprecated
	@Getter @Setter
	private Boolean nonFuelsToVectorBreaks;

    /**
     * Should independent timesteps be used when running scenarios.
     */
	@Getter @Setter
	private Boolean useIndependentTimesteps;

    /**
     * Value at which to enforce a minimum spacing of vertices on a fire perimeters, in metres.
     */
	@Getter @Setter
	private Double perimeterSpacing;

    /**
     * The initial number of vertices used to create a polygon aroung point ignitions.
     */
	@Getter @Setter
	@Builder.Default
	private int initialVertexCount = 16;

    /**
     * The initial size of the polygon around point ignitions, in metres.
     */
	@Getter @Setter
	@Builder.Default
	private double ignitionSize = 0.5;

	/**
	 * A global asset operation that can be used to force an asset behaviour for all attached assets.
	 */
	@Getter @Setter
	@Builder.Default
	private AssetOperation globalAssetOperation = AssetOperation.STOP_AFTER_ALL;

	/**
	 * An asset collision count. Will allow the simulation to be stopped after a certain number of assets have been reached.
	 * Only valid if globalAssetOperation in {@link AssetOperation#STOP_AFTER_X}.
	 */
	@Getter @Setter
	private Integer assetCollisionCount;

    /**
     * ![v7](https://img.shields.io/badge/-v7-blue)![2021.04.01](https://img.shields.io/badge/-2021.04.01-red)
     * 
     * Use a false origin to work with location information in the PSaaS backend. Currently the origin will always be the
     * lower-left location of the fuel map.
     * 
     * This is a v7 only setting. On v6 false origin is always on.
     */
	@Getter @Setter
	@Builder.Default
	private boolean enableFalseOrigin = false;

    /**
     * ![v7](https://img.shields.io/badge/-v7-blue)![2021.04.01](https://img.shields.io/badge/-2021.04.01-red)
     * 
     * Use scaling to work with location information in the PSaaS backend. Currently the scale will be the scale defined
     * in the fuel map's projection.
     * 
     * This is a v7 only setting. On v6 false scaling is always on.
     */
	@Getter @Setter
	@Builder.Default
	private boolean enableFalseScaling = false;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (this.maxAccTs == null)
			errs.add(new ValidationError("maxAccTs", "The maximum timestep to use during acceleration is not set.", this));
		if (this.distRes < 0.2 || this.distRes > 10.0)
			errs.add(new ValidationError("distRes", "The specified distance resolution is invalid.", this));
		if (this.perimRes < 0.2 || this.perimRes > 10.0)
			errs.add(new ValidationError("perimRes", "The specified perimeter resolution is invalid.", this));
		if (this.minimumSpreadingRos != null) {
			if (this.minimumSpreadingRos < 0.0000001 || this.minimumSpreadingRos > 1.0)
				errs.add(new ValidationError("minimumSpreadingRos", "The minimum spreading ROS is set but is not valid.", this));
		}
		if (this.dx != null) {
			if (this.dx < -250.0 || this.dx > 250.0)
				errs.add(new ValidationError("dx", "A delta value for the x direction of the ignition points is set but is not valid.", this));
		}
		if (this.dy != null) {
			if (this.dy < -250.0 || this.dy > 250.0)
				errs.add(new ValidationError("dy", "A delta value for the y direction of the ignition points is set but is not valid.", this));
		}
		if (this.dwd != null) {
			if (this.dwd < -360.0 || this.dwd > 360.0)
				errs.add(new ValidationError("dwd", "A delta value for the wind direction is set but is not valid.", this));
		}
		if (this.owd != null) {
			if (this.owd < 0.0 || this.owd >= 360.0)
				errs.add(new ValidationError("owd", "A wind direction override value is set but is not valid.", this));
		}
		if (this.dvd != null) {
			if (this.dvd < -360.0 || this.dvd > 360.0)
				errs.add(new ValidationError("dvd", "A delta value for the wind direction on probabilistic analysis is set but is not valid.", this));
		}
		if (this.ovd != null) {
			if (this.ovd < 0.0 || this.ovd >= 360.0)
				errs.add(new ValidationError("ovd", "A vector growth direction is set but is not valid.", this));
		}
		if (this.growthPercentileApplied != null && this.growthPercentileApplied) {
			if (this.growthPercentile == null || this.growthPercentile <= 0.0 || this.growthPercentile >= 100.0)
				errs.add(new ValidationError("growthPercentile", "Growth percentile is enabled but the specified growth percentile is not valid.", this));
		}
		if (this.perimeterSpacing != null && (this.perimeterSpacing < 0.0 || this.perimeterSpacing > 10.0))
			errs.add(new ValidationError("perimeterSpacing", "The specified perimeter spacing is not valid.", this));
		if (this.initialVertexCount < 6 || this.initialVertexCount > 64)
			errs.add(new ValidationError("initialVertexCount", "The specified initial vertex count is not valid.", this));
		if (this.ignitionSize <= 0.0 || this.ignitionSize > 25.0)
			errs.add(new ValidationError("ignitionSize", "The specified ignition size is not valid.", this));
		if (this.globalAssetOperation == AssetOperation.STOP_AFTER_X && this.assetCollisionCount < 0)
			errs.add(new ValidationError("assetCollisionCount", "The number of assets to stop the simulation after reaching has not been set.", this));
		
		return errs;
	}
}
