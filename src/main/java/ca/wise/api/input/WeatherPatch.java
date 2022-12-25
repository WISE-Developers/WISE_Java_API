package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.LatLon;
import ca.wise.api.WISEException;
import ca.wise.api.SocketMsg;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Information about a weather patch input file.
 */
@Builder
@AllArgsConstructor
public class WeatherPatch implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the weather patch. The name must be unique amongst the weather patch collection.
	 */
	@Getter
	@Builder.Default
	private String id = "wthrptch" + counter.getAndIncrement();

    /**
     * Set the name of the weather patch. Must be unique amongst the weather patch collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the weather patch. The name must be unique amongst the weather patch collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the weather patch. Must be unique amongst the weather patch collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

	/**
	 * The patch start time (required).
	 */
	@Getter @Setter
	private String startTime;

	/**
	 * The patch end time (required).
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
	 * Any user comments about the weather patch (optional).
	 */
	@Getter @Setter
	private String comments;

	/**
	 * The type of weather patch (required).
	 */
	@Getter @Setter
	@Builder.Default
	private WeatherPatchType type = WeatherPatchType.INVALID;

	/**
	 * The filename associated with this weather patch. Only valid if type is FILE.
	 */
	@Getter
	private String filename;

	/**
	 * The filename associated with this weather patch. Only valid if type is FILE.
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The weather stream file does not exist");
		this.filename = value;
	}

	/**
	 * An array of LatLon describing the weather patch. Only valid if type is POLYGON.
	 */
	@Getter
	@Builder.Default
	private List<LatLon> feature = new ArrayList<>();

	/**
	 * The temperature to apply with this patch.
	 */
	@Getter @Setter
	private Temperature temperature;

	/**
	 * The relative humidty to apply with this patch.
	 */
	@Getter @Setter
	private RelativeHumidity rh;

	/**
	 * The precipitation to apply with this patch.
	 */
	@Getter @Setter
	private Precipitation precip;

	/**
	 * The wind speed to apply with this patch.
	 */
	@Getter @Setter
	private WindSpeed windSpeed;

	/**
	 * The wind direction to apply with this patch.
	 */
	@Getter @Setter
	private WindDirection windDirection;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather stream.", this));
		if (this.startTimeOfDay == null)
			errs.add(new ValidationError("startTimeOfDay", "No start time of day has been set on the weather patch.", this));
		if (this.endTimeOfDay == null)
			errs.add(new ValidationError("endTimeOfDay", "No end time of day has been set on the weather patch.", this));
		if (this.startTime == null)
			errs.add(new ValidationError("startTime", "No start time has been set on the weather patch.", this));
		if (this.endTime == null)
			errs.add(new ValidationError("endTime", "No end time has been set on the weather patch.", this));
		if (this.type == WeatherPatchType.FILE) {
			if (!SocketMsg.skipFileTests) {
				if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
					errs.add(new ValidationError("filename", "The weather patch file does not exist.", this));
			}
		}
		else if (this.type == WeatherPatchType.POLYGON) {
			if (this.feature.size() == 0)
				errs.add(new ValidationError("feature", "No points have been added to the polygon weather patch.", this));
		}
		else if (this.type == WeatherPatchType.INVALID) {
			errs.add(new ValidationError("type", "The weather patch type is invalid.", this));
		}
		
		if (this.temperature != null) {
			List<ValidationError> tempErr = this.temperature.checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError("temperature", "Errors found in weather patch temperature details.", this);
				tempErr.stream().forEach(x -> err.addChild(x));
				errs.add(err);
			}
		}
		if (this.rh != null) {
			List<ValidationError> tempErr = this.rh.checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError("rh", "Errors found in weather patch relative humidity details.", this);
				tempErr.stream().forEach(x -> err.addChild(x));
				errs.add(err);
			}
		}
		if (this.precip != null) {
			List<ValidationError> tempErr = this.precip.checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError("precip", "Errors found in weather patch precipitation details.", this);
				tempErr.stream().forEach(x -> err.addChild(x));
				errs.add(err);
			}
		}
		if (this.windSpeed != null) {
			List<ValidationError> tempErr = this.windSpeed.checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError("windSpeed", "Errors found in weather patch wind speed details.", this);
				tempErr.stream().forEach(x -> err.addChild(x));
				errs.add(err);
			}
		}
		if (this.windDirection != null) {
			List<ValidationError> tempErr = this.windDirection.checkValid();
			if (tempErr.size() > 0) {
				ValidationError err = new ValidationError("windDirection", "Errors found in weather patch wind direction details.", this);
				tempErr.stream().forEach(x -> err.addChild(x));
				errs.add(err);
			}
		}
		
		return errs;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	public static abstract class WeatherPatchDetails implements IWISESerializable {

		/**
		 * The operation that the patch applies (required).
		 */
		@Getter @Setter
		protected WeatherPatchOperation operation = WeatherPatchOperation.INVALID;

		/**
		 * The value to apply with this operation.
		 */
		@Getter @Setter
		protected double value;

		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (this.operation == WeatherPatchOperation.INVALID)
				errs.add(new ValidationError("operation", "An invalid operation has been set on the weather patch details.", this));
			if (this.value <= 0)
				errs.add(new ValidationError("value", "An invalid value has been set on the weather patch details.", this));
			
			return errs;
		}
	}
	
	@NoArgsConstructor
	public static class Temperature extends WeatherPatchDetails {
		
		@Builder
		public Temperature(WeatherPatchOperation operation, double value) {
			super(operation, value);
		}
	}

	@NoArgsConstructor
	public static class RelativeHumidity extends WeatherPatchDetails {
		
		public void setValuePercent(double value) {
			this.value = value / 100.0;
		}
		
		@Builder
		public RelativeHumidity(WeatherPatchOperation operation, double value) {
			super(operation, value);
		}
	}

	@NoArgsConstructor
	public static class Precipitation extends WeatherPatchDetails {
		
		@Builder
		public Precipitation(WeatherPatchOperation operation, double value) {
			super(operation, value);
		}
	}

	@NoArgsConstructor
	public static class WindSpeed extends WeatherPatchDetails {
		
		@Builder
		public WindSpeed(WeatherPatchOperation operation, double value) {
			super(operation, value);
		}
	}

	@NoArgsConstructor
	public static class WindDirection extends WeatherPatchDetails {
		
		@Builder
		public WindDirection(WeatherPatchOperation operation, double value) {
			super(operation, value);
		}
		
		@Override
		public List<ValidationError> checkValid() {
			List<ValidationError> errs = new ArrayList<>();
			
			if (this.operation == WeatherPatchOperation.INVALID || this.operation.value > WeatherPatchOperation.MINUS.value)
				errs.add(new ValidationError("operation", "An invalid operation has been set on the weather patch details.", this));
			if (this.value <= 0)
				errs.add(new ValidationError("value", "An invalid value has been set on the weather patch details.", this));
			
			return errs;
		}
	}
}
