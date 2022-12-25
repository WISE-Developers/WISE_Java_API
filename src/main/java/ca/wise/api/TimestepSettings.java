package ca.wise.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import ca.wise.api.output.GlobalStatistics;
import ca.wise.api.validation.ValidationError;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Settings to modify W.I.S.E. behaviour at the end of every timestep.
 */
@NoArgsConstructor
public class TimestepSettings implements IWISESerializable {
	
	private Set<GlobalStatistics> statistics = new HashSet<>();

	/**
	 * The amount to discritize the existing grid to (optional).
	 * Will only be applied to statistics that require a discretization parameter.
	 * Must be in [1,1000].
	 */
	@Getter @Setter
	private Integer discretize = null;

	@Builder
	public TimestepSettings(Integer discretize) {
		this.discretize = discretize;
	}
	
	/**
	 * Get an unmodifiable list of already added statistics.
	 */
	public Collection<GlobalStatistics> getStatistics() {
		return Collections.unmodifiableCollection(statistics);
	}
	
	/**
	 * Add a new statistic to export after every timestep.
	 * Valid statistics are:
	 * <ul>
	 * <li>{@link GlobalStatistics#TOTAL_BURN_AREA}</li>
	 * <li>{@link GlobalStatistics#TOTAL_PERIMETER}</li>
	 * <li>{@link GlobalStatistics#EXTERIOR_PERIMETER}</li>
	 * <li>{@link GlobalStatistics#ACTIVE_PERIMETER}</li>
	 * <li>{@link GlobalStatistics#TOTAL_PERIMETER_CHANGE}</li>
	 * <li>{@link GlobalStatistics#TOTAL_PERIMETER_GROWTH_RATE}</li>
	 * <li>{@link GlobalStatistics#EXTERIOR_PERIMETER_CHANGE}</li>
	 * <li>{@link GlobalStatistics#EXTERIOR_PERIMETER_GROWTH_RATE}</li>
	 * <li>{@link GlobalStatistics#ACTIVE_PERIMETER_CHANGE}</li>
	 * <li>{@link GlobalStatistics#ACTIVE_PERIMETER_GROWTH_RATE}</li>
	 * <li>{@link GlobalStatistics#AREA_CHANGE}</li>
	 * <li>{@link GlobalStatistics#AREA_GROWTH_RATE}</li>
	 * <li>{@link GlobalStatistics#NUM_VERTICES}</li>
	 * <li>{@link GlobalStatistics#NUM_ACTIVE_VERTICES}</li>
	 * <li>{@link GlobalStatistics#CUMULATIVE_ACTIVE_VERTICES}</li>
	 * <li>{@link GlobalStatistics#CUMULATIVE_VERTICES}</li>
	 * <li>{@link GlobalStatistics#NUM_FRONTS}</li>
	 * <li>{@link GlobalStatistics#NUM_ACTIVE_FRONTS}</li>
	 * <li>{@link GlobalStatistics#MAX_ROS}</li>
	 * <li>{@link GlobalStatistics#MAX_CFB}</li>
	 * <li>{@link GlobalStatistics#MAX_CFC}</li>
	 * <li>{@link GlobalStatistics#MAX_SFC}</li>
	 * <li>{@link GlobalStatistics#MAX_TFC}</li>
	 * <li>{@link GlobalStatistics#MAX_FI}</li>
	 * <li>{@link GlobalStatistics#MAX_FL}</li>
	 * <li>{@link GlobalStatistics#TICKS}</li>
	 * <li>{@link GlobalStatistics#PROCESSING_TIME}</li>
	 * <li>{@link GlobalStatistics#GROWTH_TIME}</li>
	 * <li>{@link GlobalStatistics#DATE_TIME}</li>
	 * <li>{@link GlobalStatistics#SCENARIO_NAME}</li>
	 * <li>{@link GlobalStatistics#HFI}</li>
	 * <li>{@link GlobalStatistics#HCFB}</li>
	 * </ul>
	 */
	public TimestepSettings addStatistic(GlobalStatistics stat) {
		if (!validateStatistic(stat))
			return null;
		statistics.add(stat);
		return this;
	}
	
	/**
	 * Remove a statistic from the list.
	 */
	public TimestepSettings removeStatistic(GlobalStatistics stat) {
		statistics.remove(stat);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		List<ValidationError> statErrs = new ArrayList<>();
		int i = 0;
		for (GlobalStatistics stat : statistics) {
			if (!validateStatistic(stat)) {
				statErrs.add(new ValidationError(i, "Invalid timestep setting output found at " + i + ".", this.statistics));
			}
			i++;
		}
		if (statErrs.size() > 0) {
			ValidationError temp = new ValidationError("statistics", "Invalid timestep settings found.", this);
			statErrs.forEach(temp::addChild);
			errs.add(temp);
		}
		if (this.discretize != null && (this.discretize < 1 || this.discretize > 1000))
			errs.add(new ValidationError("discritize", "The discritization must be an integer greater than 0 and less than 1001.", this));
		
		return errs;
	}
	
	public static boolean validateStatistic(GlobalStatistics stat) {
		if (stat != GlobalStatistics.TOTAL_BURN_AREA && stat != GlobalStatistics.TOTAL_PERIMETER &&
				stat != GlobalStatistics.EXTERIOR_PERIMETER && stat != GlobalStatistics.ACTIVE_PERIMETER && stat != GlobalStatistics.TOTAL_PERIMETER_CHANGE &&
				stat != GlobalStatistics.TOTAL_PERIMETER_GROWTH_RATE && stat != GlobalStatistics.EXTERIOR_PERIMETER_CHANGE && stat != GlobalStatistics.EXTERIOR_PERIMETER_GROWTH_RATE &&
				stat != GlobalStatistics.ACTIVE_PERIMETER_CHANGE && stat != GlobalStatistics.ACTIVE_PERIMETER_GROWTH_RATE && stat != GlobalStatistics.AREA_CHANGE &&
				stat != GlobalStatistics.AREA_GROWTH_RATE && stat != GlobalStatistics.NUM_VERTICES && stat != GlobalStatistics.NUM_ACTIVE_VERTICES &&
				stat != GlobalStatistics.CUMULATIVE_ACTIVE_VERTICES && stat != GlobalStatistics.CUMULATIVE_VERTICES && stat != GlobalStatistics.NUM_FRONTS &&
				stat != GlobalStatistics.NUM_ACTIVE_FRONTS && stat != GlobalStatistics.MAX_ROS && stat != GlobalStatistics.MAX_CFB && stat != GlobalStatistics.MAX_CFC &&
				stat != GlobalStatistics.MAX_SFC && stat != GlobalStatistics.MAX_TFC && stat != GlobalStatistics.MAX_FI && stat != GlobalStatistics.MAX_FL &&
				stat != GlobalStatistics.TICKS && stat != GlobalStatistics.PROCESSING_TIME && stat != GlobalStatistics.GROWTH_TIME &&
				stat != GlobalStatistics.DATE_TIME && stat != GlobalStatistics.SCENARIO_NAME && stat != GlobalStatistics.HFI && stat != GlobalStatistics.HCFB) {
			return false;
		}
		return true;
	}
}
