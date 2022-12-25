package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.WISEException;
import ca.wise.api.SocketMsg;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Information about a weather grid input.
 */
@Builder
@AllArgsConstructor
public class WeatherGrid implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the weather grid. The name must be unique amongst the weather grid collection.
	 */
	@Getter
	@Builder.Default
	private String id = "wthrgrd" + counter.getAndIncrement();

    /**
     * Set the name of the weather grid. Must be unique amongst the weather grid collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the weather grid. The name must be unique amongst the weather grid collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the weather grid. Must be unique amongst the weather grid collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

	/**
	 * Any comments about the weather grid (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

	/**
	 * The grid start time (required).
	 */
	@Getter @Setter
	private String startTime;

	/**
	 * The grid end time (required).
	 */
	@Getter @Setter
	private String endTime;

	/**
	 * The patches start time of day (required).
	 */
	@Getter @Setter
	private String startTimeOfDay;

	/**
	 * The patches end time of day (required).
	 */
	@Getter @Setter
	private String endTimeOfDay;

	/**
	 * An array of WeatherGrid_GridFile. There can be one for each wind sector (North, Northeast, East, etc.).
	 */
	@Getter
	@Builder.Default
	private List<WeatherGridGridFile> gridData = new ArrayList<>();

	/**
	 * Whether this wind grid is for wind speed, or wind direction (required). Must be one of TYPE_DIRECTION and TYPE_SPEED.
	 */
	@Getter @Setter
	@Builder.Default
	private WeatherGridType type = WeatherGridType.INVALID;

	/**
	 * The default sector data. If specified {@link defaultValuesProjection} must also be specified.
	 */
	@Getter @Setter
	private String defaultValuesFile;

	/**
	 * The projection file for the default sector data. Must be specified if {@link defaultValuesFile} is specified.
	 */
	@Getter @Setter
	private String defaultValuesProjection;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather grid.", this));
		if (this.startTimeOfDay == null)
			errs.add(new ValidationError("startTiemOfDay", "No start time of day has been set on the weather grid.", this));
		if (this.endTimeOfDay == null)
			errs.add(new ValidationError("endTimeOfDay", "No end time of day has been set on the weather grid.", this));
		if (this.startTime == null)
			errs.add(new ValidationError("startTime", "No start time has been set on the weather grid.", this));
		if (this.endTime == null)
			errs.add(new ValidationError("endTime", "No end time has been set on the weather grid.", this));
		
		List<ValidationError> dataErrs = new ArrayList<>();
		for (int i = 0; i < gridData.size(); i++) {
			List<ValidationError> tempErr = gridData.get(i).checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError(i, "Error in weather grid data file at " + i + ".", this.gridData);
				tempErr.stream().forEach(x -> err.addChild(x));
				dataErrs.add(err);
			}
		}
		if (dataErrs.size() > 0) {
			ValidationError tempErr = new ValidationError("gridData", "Errors in weather grid data.", this);
			dataErrs.stream().forEach(x -> tempErr.addChild(x));
			errs.add(tempErr);
		}
		if (!Strings.isNullOrEmpty(this.defaultValuesFile)) {
			if (Strings.isNullOrEmpty(this.defaultValuesProjection))
				errs.add(new ValidationError("defaultValuesProjection", "The projection for the default sector data is required.", this));
			else if (!SocketMsg.skipFileTests) {
				if (!this.defaultValuesFile.startsWith("attachment:/") && !Files.exists(Paths.get(this.defaultValuesFile)))
					errs.add(new ValidationError("defaultValuesFile", "The default grid data file does not exist.", this));
				if (!this.defaultValuesProjection.startsWith("attachment:/") && !Files.exists(Paths.get(this.defaultValuesProjection)))
					errs.add(new ValidationError("defaultValuesProjection", "The default grid data projection file does not exist.", this));
			}
		}
		
		return errs;
	}
}
