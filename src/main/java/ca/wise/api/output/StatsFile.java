package ca.wise.api.output;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.LatLon;
import ca.wise.api.input.WeatherStream;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An output file to mimic the Prometheus stats view. Contains
 * stats from each timestep of a scenarios simulation.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsFile implements IWISESerializable {
	
	/**
	 * Should the file be streamed/uploaded to an external service after
	 * it has been created? The streaming services are defined by
	 * {@link OutputStreamInfo} and helper methods such as
	 * {@link WISE#streamOutputToMqtt} or {@link WISE#streamOutputToGeoServer}.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean shouldStream = false;

	@Getter @Setter
	private String scenName;

	@Getter @Setter
	private String streamName;

	@Getter @Setter
	@Builder.Default
	private LatLon location = null;
	
	/**
	 * The name of the output file.
	 */
	@Getter @Setter
	private String filename;

	/**
	 * The file format to export to.
	 */
	@Getter @Setter
	@Builder.Default
	private StatsFileType fileType = StatsFileType.AUTO_DETECT;
	
	/**
	 * An array of {@link GlobalStatistics} that dictates which statistics
	 * will be added to the file.
	 */
	@Getter
	@Builder.Default
	private final List<GlobalStatistics> columns = new ArrayList<>();
	
	/**
	 * The amount to discritize the existing grid to (optional).
	 * Must be in [1, 1000].
	 */
	@Getter @Setter
	@Builder.Default
	private Integer discretize = null;
	
	public void setWeatherStream(WeatherStream stream) {
		this.streamName = stream.getId();
	}
	
	public StatsFile addColumn(GlobalStatistics col) {
		if (validateStatistic(col)) {
			this.columns.add(col);
			return this;
		}
		return null;
	}
	
	public boolean removeColumn(GlobalStatistics col) {
		return this.columns.remove(col);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		if (Strings.isNullOrEmpty(filename))
			errs.add(new ValidationError("filename", "The output filename was not specified.", this));
		if (Strings.isNullOrEmpty(scenName))
			errs.add(new ValidationError("scenName", "The scenario that the output is for has not been specified.", this));
		List<ValidationError> columnErrs = new ArrayList<>();
		for (int i = 0; i < this.columns.size(); i++) {
			if (!validateStatistic(this.columns.get(i))) {
				columnErrs.add(new ValidationError(i, "Invalid statistics column at " + i + ".", this.columns));
			}
		}
		if (columnErrs.size() > 0) {
			ValidationError temp = new ValidationError("columns", "Invalid statistics columns.", this);
			columnErrs.forEach(err -> {
				temp.addChild(err);
			});
			errs.add(temp);
		}
		if (this.discretize != null && (this.discretize < 1 || this.discretize > 1000)) {
			errs.add(new ValidationError("discritize", "The discritization must be an integer greater than 0 and less than 1001.", this));
		}
		return errs;
	}
	
	public static boolean validateStatistic(GlobalStatistics col) {
		if (col == GlobalStatistics.DATE_TIME || col == GlobalStatistics.ELAPSED_TIME || col == GlobalStatistics.TIME_STEP_DURATION ||
				col == GlobalStatistics.TEMPERATURE || col == GlobalStatistics.DEW_POINT || col == GlobalStatistics.RELATIVE_HUMIDITY ||
				col == GlobalStatistics.WIND_SPEED || col == GlobalStatistics.WIND_DIRECTION || col == GlobalStatistics.PRECIPITATION ||
				col == GlobalStatistics.HFFMC || col == GlobalStatistics.HISI || col == GlobalStatistics.DMC ||
				col == GlobalStatistics.DC || col == GlobalStatistics.HFWI || col == GlobalStatistics.BUI ||
				col == GlobalStatistics.FFMC || col == GlobalStatistics.ISI || col == GlobalStatistics.FWI ||
				col == GlobalStatistics.TIMESTEP_AREA || col == GlobalStatistics.TIMESTEP_BURN_AREA || col == GlobalStatistics.TOTAL_AREA ||
				col == GlobalStatistics.TOTAL_BURN_AREA || col == GlobalStatistics.AREA_GROWTH_RATE || col == GlobalStatistics.EXTERIOR_PERIMETER ||
				col == GlobalStatistics.EXTERIOR_PERIMETER_GROWTH_RATE || col == GlobalStatistics.ACTIVE_PERIMETER || col == GlobalStatistics.ACTIVE_PERIMETER_GROWTH_RATE ||
				col == GlobalStatistics.TOTAL_PERIMETER || col == GlobalStatistics.TOTAL_PERIMETER_GROWTH_RATE || col == GlobalStatistics.FI_LT_10 ||
				col == GlobalStatistics.FI_10_500 || col == GlobalStatistics.FI_500_2000 || col == GlobalStatistics.FI_2000_4000 ||
				col == GlobalStatistics.FI_4000_10000 || col == GlobalStatistics.FI_GT_10000 || col == GlobalStatistics.ROS_0_1 ||
				col == GlobalStatistics.ROS_2_4 || col == GlobalStatistics.ROS_5_8 || col == GlobalStatistics.ROS_9_14 ||
				col == GlobalStatistics.ROS_GT_15 || col == GlobalStatistics.MAX_ROS || col == GlobalStatistics.MAX_FI ||
				col == GlobalStatistics.MAX_FL || col == GlobalStatistics.MAX_CFB || col == GlobalStatistics.MAX_CFC ||
				col == GlobalStatistics.MAX_SFC || col == GlobalStatistics.MAX_TFC || col == GlobalStatistics.TOTAL_FUEL_CONSUMED ||
				col == GlobalStatistics.CROWN_FUEL_CONSUMED || col == GlobalStatistics.SURFACE_FUEL_CONSUMED || col == GlobalStatistics.NUM_ACTIVE_VERTICES ||
				col == GlobalStatistics.NUM_VERTICES || col == GlobalStatistics.CUMULATIVE_VERTICES || col == GlobalStatistics.CUMULATIVE_ACTIVE_VERTICES ||
				col == GlobalStatistics.NUM_ACTIVE_FRONTS || col == GlobalStatistics.NUM_FRONTS || col == GlobalStatistics.MEMORY_USED_START ||
				col == GlobalStatistics.MEMORY_USED_END || col == GlobalStatistics.NUM_TIMESTEPS || col == GlobalStatistics.NUM_DISPLAY_TIMESTEPS ||
				col == GlobalStatistics.NUM_EVENT_TIMESTEPS || col == GlobalStatistics.NUM_CALC_TIMESTEPS || col == GlobalStatistics.TICKS ||
				col == GlobalStatistics.PROCESSING_TIME || col == GlobalStatistics.GROWTH_TIME)
			return true;
		return false;
	}
}
