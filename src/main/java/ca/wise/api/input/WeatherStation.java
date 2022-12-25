package ca.wise.api.input;

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

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherStation implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the weather station. The name must be unique amongst a weather station collection.
	 */
	@Getter
	@Builder.Default
	private String id = "wthrstn" + counter.getAndIncrement();

    /**
     * Set the name of the weather station. Must be unique amongst the weather station collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the weather station. The name must be unique amongst a weather station collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the weather station. Must be unique amongst the weather station collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

	/**
	 * The location of the weather station (required).
	 */
	@Getter @Setter
	private LatLon location;

	/**
	 * The weather streams from this weather station.
	 */
	@Getter
	@Builder.Default
	private List<WeatherStream> streams = new ArrayList<>();

	/**
	 * User comments about the weather station (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

	/**
	 * The elevation of the weather station (required).
	 */
	@Getter @Setter
	@Builder.Default
	private double elevation = 0;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather station.", this));
		if (this.location == null)
			errs.add(new ValidationError("location", "The location of the weather station has not been set.", this));
		
		List<ValidationError> weatherStreamErrs = new ArrayList<>();
		for (int i = 0; i < this.streams.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in the weather stream at index " + i + ".", this.streams);
			for (int j = i + 1; j < this.streams.size(); j++) {
				if (this.streams.get(i).getId().equalsIgnoreCase(this.streams.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate weather stream IDs.", this.streams.get(i));
					wsErr.addChild(err);
					break;
				}
			}
			this.streams.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0) {
				weatherStreamErrs.add(wsErr);
			}
		}
		if (weatherStreamErrs.size() > 0) {
			ValidationError temp = new ValidationError("streams", "Errors found in weather streams.", this);
			weatherStreamErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		return errs;
	}

    /**
     * Add a weather stream to the station.
     * @param filename The file location for the streams data. Can either be the actual file path 
	 * 				   or the attachment URL returned from {@link WISE#addAttachment}
     * @param hffmc_value The HFFMC value.
     * @param hffmc_hour The hour the HFFMC value was measured. Must be between -1 and 23 inclusive.
     * @param hffmc_method The method to calculate HFFMC.
     * @param starting_ffmc The starting FFMC value.
     * @param starting_dmc The starting DMC value.
     * @param starting_dc The starting DC value.
     * @param starting_precip The starting amount of precipitation.
     * @param start_time The starting time of the weather stream. If a string is used it must be formatted as "YYYY-MM-DD".
     * @param end_time The ending time of the weather stream. If a string is used it must be formatted as "YYYY-MM-DD".
     * @param comments An optional user comment to attach to the weather stream.
	 * @throws If {@link SocketMsg.inlineThrowOnError} is set and {@link SocketMsg.skipFileTests} is not set a {@link ca.firegrowthmodel.api.WISEException} will be thrown if the file doesn't exist.
     * @return WeatherStream
     */
	public WeatherStream addWeatherStream(String filename, double hffmcValue, int hffmcHour, HFFMCMethod hffmcMethod,
			double startingFfmc, double startingDmc, double startingDc, double startingPrecip,
			String startTime, String endTime, String comments) {
		WeatherStream stream = WeatherStream.builder()
				.filename(filename)
				.hffmcValue(hffmcValue)
				.hffmcHour(hffmcHour)
				.hffmcMethod(hffmcMethod)
				.startingFfmc(startingFfmc)
				.startingDmc(startingDmc)
				.startingDc(startingDc)
				.startingPrecip(startingPrecip)
				.startTime(startTime)
				.endTime(endTime)
				.build();
		this.streams.add(stream);
		return stream;
	}

    /**
     * Add a weather stream to the station.
     * @param filename The file location for the streams data. Can either be the actual file path 
	 * 				   or the attachment URL returned from {@link WISE#addAttachment}
     * @param hffmc_value The HFFMC value.
     * @param hffmc_hour The hour the HFFMC value was measured. Must be between -1 and 23 inclusive.
     * @param hffmc_method The method to calculate HFFMC.
     * @param starting_ffmc The starting FFMC value.
     * @param starting_dmc The starting DMC value.
     * @param starting_dc The starting DC value.
     * @param starting_precip The starting amount of precipitation.
     * @param start_time The starting time of the weather stream. If a string is used it must be formatted as "YYYY-MM-DD".
     * @param end_time The ending time of the weather stream. If a string is used it must be formatted as "YYYY-MM-DD".
	 * @param talpha The diurnal alpha temperature parameter.
	 * @param tbeta The diurnal alpha temperature parameter.
	 * @param tgamma The diurnal gamma temperature parameter.
	 * @param wsalpha The diurnal alpha wind speed parameter.
	 * @param wsbeta The diurnal beta wind speed parameter.
	 * @param wsgamma The diurnal gamma wind speed parameter.
     * @param comments An optional user comment to attach to the weather stream.
	 * @throws If {@link SocketMsg.inlineThrowOnError} is set and {@link SocketMsg.skipFileTests} is not set a {@link ca.firegrowthmodel.api.WISEException} will be thrown if the file doesn't exist.
     * @return WeatherStream
     */
	public WeatherStream addWeatherStream(String filename, double hffmcValue, int hffmcHour, HFFMCMethod hffmcMethod,
			double startingFfmc, double startingDmc, double startingDc, double startingPrecip,
			String startTime, String endTime, double talpha, double tbeta, double tgamma,
			double wsalpha, double wsbeta, double wsgamma, String comments) {
		WeatherStream stream = WeatherStream.builder()
				.filename(filename)
				.hffmcValue(hffmcValue)
				.hffmcHour(hffmcHour)
				.hffmcMethod(hffmcMethod)
				.startingFfmc(startingFfmc)
				.startingDmc(startingDmc)
				.startingDc(startingDc)
				.startingPrecip(startingPrecip)
				.startTime(startTime)
				.endTime(endTime)
				.diurnalTemperatureAlpha(talpha)
				.diurnalTemperatureBeta(tbeta)
				.diurnalTemperatureGamma(tgamma)
				.diurnalWindSpeedAlpha(wsalpha)
				.diurnalWindSpeedBeta(wsbeta)
				.diurnalWindSpeedGamma(wsgamma)
				.build();
		this.streams.add(stream);
		return stream;
	}
}
