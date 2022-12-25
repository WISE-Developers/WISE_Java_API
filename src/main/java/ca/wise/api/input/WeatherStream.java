package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
 * Information about a weather stream.
 */
@Builder
@AllArgsConstructor
public class WeatherStream implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the weather stream. The name must be unique amongst a weather stream collection.
	 */
	@Getter
	@Builder.Default
	private String id = "wthrstrm" + counter.getAndIncrement();

    /**
     * Set the name of the weather stream. Must be unique amongst the weather stream collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the weather stream. The name must be unique amongst a weather stream collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the weather stream. Must be unique amongst the weather stream collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}
	
	/**
	 * User comments about the weather stream (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String comments = "";
	
	/**
	 * The location of the file containing the stream data (required).
	 */
	@Getter
	private String filename;

	/**
	 * The location of the file containing the stream data (required).
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The weather stream file does not exist");
		this.filename = value;
	}
	
	/**
	 * Yesterday's daily starting fine fuel moisture code (required).
	 */
	@Getter
	private double startingFfmc;

	/**
	 * Yesterday's daily starting fine fuel moisture code (required).
	 */
	public void setStartingFfmc(double value) {
		if (SocketMsg.inlineThrowError && (value < 0 || value > 101))
			throw new WISEException("The start FFMC value is not valid.");
		this.startingFfmc = value;
	}
	
	/**
     * Get yesterday's daily starting duff moisture code.
	 */
	@Getter
	private double startingDmc;

	/**
     * Get yesterday's daily starting duff moisture code.
	 */
	public void setStartingDmc(double value) {
		if (SocketMsg.inlineThrowError && (value < 0 || value > 500))
			throw new WISEException("The start DMC value is not valid.");
		this.startingDmc = value;
	}
	
	/**
     * Yesterday's daily starting drought code (required).
	 */
	@Getter
	private double startingDc;

	/**
     * Yesterday's daily starting drought code (required).
	 */
	public void setStartingDc(double value) {
		if (SocketMsg.inlineThrowError && (value < 0 || value > 1500))
			throw new WISEException("The start DC value is not valid.");
		this.startingDc = value;
	}
	
	/**
     * Yesterday's daily starting BUI (optional).
	 */
	@Getter
	private Double startingBui;
	
	/**
     * Yesterday's daily starting precipitation (13:01-23:00 if daylight savings time, 12:01-23:00 otherwise) (required).
	 */
	@Getter
	private double startingPrecip;

	/**
     * Yesterday's daily starting precipitation (13:01-23:00 if daylight savings time, 12:01-23:00 otherwise) (required).
	 */
	public void setStartingPrecip(double value) {
		if (SocketMsg.inlineThrowError && value < 0)
			throw new WISEException("The start precipitation value is not valid.");
		this.startingPrecip = value;
	}
	
	/**
     * The hour that the HFFMC value is for (required). Must be between -1 and 23 inclusive.
	 */
	@Getter
	private int hffmcHour;

	/**
     * The hour that the HFFMC value is for (required). Must be between -1 and 23 inclusive.
	 */
	public void setHffmcHour(int value) {
		if (SocketMsg.inlineThrowError && (value < -1 || value > 23))
			throw new WISEException("The HFFMC hour is not valid.");
		this.hffmcHour = value;
	}
	
	/**
	 * The HFFMC value (required).
	 */
	@Getter @Setter
	private double hffmcValue;
	
	/**
	 * The HFFMC calculation method (required).
	 */
	@Getter @Setter
	@Builder.Default
	private HFFMCMethod hffmcMethod = HFFMCMethod.INVALID;
	
	/**
	 * Diurnal parameters - temperature alpha (optional).
	 */
	@Getter @Setter
	private Double diurnalTemperatureAlpha;
	
	/**
	 * Diurnal parameters - temperature beta (optional).
	 */
	@Getter @Setter
	private Double diurnalTemperatureBeta;
	
	/**
	 * Diurnal parameters - temperature gamma (optional).
	 */
	@Getter @Setter
	private Double diurnalTemperatureGamma;
	
	/**
	 * Diurnal parameters - wind speed alpha (optional).
	 */
	@Getter @Setter
	private Double diurnalWindSpeedAlpha;
	
	/**
	 * Diurnal parameters - wind speed beta (optional).
	 */
	@Getter @Setter
	private Double diurnalWindSpeedBeta;
	
	/**
	 * Diurnal parameters - wind speed gamma (optional).
	 */
	@Getter @Setter
	private Double diurnalWindSpeedGamma;
	
	/**
	 * The starting time of the weather stream (required).
	 */
	@Getter @Setter
	private String startTime;
	
	/**
	 * The starting time of the weather stream (required).
	 */
	@Getter @Setter
	private String endTime;
	
	/**
	 * The ID of the weather station that this stream came from.
	 */
	@Getter
	private final String parentId;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather stream.", this));
		
		if (!SocketMsg.skipFileTests) {
			if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
				errs.add(new ValidationError("filename", "The specified weather file does not exist.", this));
		}
		if (this.startingFfmc < 0 || this.startingFfmc > 101)
			errs.add(new ValidationError("startingFfmc", "Invalid starting FFMC value for the weather stream.", this));
		if (this.startingDmc < 0 || this.startingDmc > 500)
			errs.add(new ValidationError("startingDmc", "Invalid starting DMC value for the weather stream.", this));
		if (this.startingDc < 0 || this.startingDc > 1500)
			errs.add(new ValidationError("startingDc", "Invalid starging DC value for the weather stream.", this));
		if (this.startingPrecip < 0)
			errs.add(new ValidationError("startingPrecip", "Invalid starting precipitation value for the weather stream.", this));
		if (this.hffmcHour < -1 || this.hffmcHour > 23)
			errs.add(new ValidationError("hffmcHour", "Invalid starting HFFMC hour for the weather stream.", this));
		if (this.hffmcMethod == HFFMCMethod.INVALID)
			errs.add(new ValidationError("hffmcMethod", "Invalid HFFMC calculation method for the weather stream.", this));
		if (this.startTime == null)
			errs.add(new ValidationError("startTime", "No start time has been set for the weather stream.", this));
		if (this.endTime == null)
			errs.add(new ValidationError("endTime", "No end time has been set for the weather stream.", this));
		
		return errs;
	}
	
	public static class WeatherStreamBuilder {
		
		private String filename = "";

		private double startingFfmc = -1;

		private double startingDmc = -1;

		private double startingDc = -1;

		private double startingPrecip = -1;

		private int hffmcHour = -1;

		/**
		 * The location of the file containing the stream data (required).
		 */
		public WeatherStreamBuilder filename(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The weather stream file does not exist");
			this.filename = value;
			return this;
		}

		/**
		 * Yesterday's daily starting fine fuel moisture code (required).
		 */
		public WeatherStreamBuilder startingFfmc(double value) {
			if (SocketMsg.inlineThrowError && (value < 0 || value > 101))
				throw new WISEException("The start FFMC value is not valid.");
			this.startingFfmc = value;
			return this;
		}

		/**
		 * Get yesterday's daily starting duff moisture code.
		 */
		public WeatherStreamBuilder startingDmc(double value) {
			if (SocketMsg.inlineThrowError && (value < 0 || value > 500))
				throw new WISEException("The start DMC value is not valid.");
			this.startingDmc = value;
			return this;
		}

		/**
		 * Yesterday's daily starting drought code (required).
		 */
		public WeatherStreamBuilder startingDc(double value) {
			if (SocketMsg.inlineThrowError && (value < 0 || value > 1500))
				throw new WISEException("The start DC value is not valid.");
			this.startingDc = value;
			return this;
		}

		/**
		 * Yesterday's daily starting precipitation (13:01-23:00 if daylight savings time, 12:01-23:00 otherwise) (required).
		 */
		public WeatherStreamBuilder startingPrecip(double value) {
			if (SocketMsg.inlineThrowError && value < 0)
				throw new WISEException("The start precipitation value is not valid.");
			this.startingPrecip = value;
			return this;
		}

		/**
		 * The hour that the HFFMC value is for (required). Must be between -1 and 23 inclusive.
		 */
		public WeatherStreamBuilder hffmcHour(int value) {
			if (SocketMsg.inlineThrowError && (value < -1 || value > 23))
				throw new WISEException("The HFFMC hour is not valid.");
			this.hffmcHour = value;
			return this;
		}
	}
}
