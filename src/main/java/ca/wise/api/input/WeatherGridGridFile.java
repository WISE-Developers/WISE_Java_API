package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ca.wise.api.IWISESerializable;
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
public class WeatherGridGridFile implements IWISESerializable {

    /**
     * The wind speed (required).
     */
	@Getter
	private double speed;

    /**
     * The wind speed (required).
     */
	public void setSpeed(double value) {
		if (SocketMsg.inlineThrowError && (value < 0 || value > 250))
			throw new WISEException("The wind speed is not valid.");
		this.speed = value;
	}

    /**
     * The sector to apply this grid file to (required).
     */
	@Getter @Setter
	@Builder.Default
	private WeatherGridSector sector = WeatherGridSector.INVALID;

    /**
     * The location of the grid file (required).
     */
	@Getter
	private String filename;
	
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The grid file does not exist");
		this.filename = value;
	}

    /**
     * The projection file for the grid file (required).
     */
	@Getter @Setter
	private String projection;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (this.speed < 0 || this.speed > 250)
			errs.add(new ValidationError("speed", "The wind speed must be >= 0km/h and <= 250km/h.", this));
		if (this.sector == WeatherGridSector.INVALID)
			errs.add(new ValidationError("sector", "The wind direction sector is not one of the valid values.", this));
		if (!SocketMsg.skipFileTests) {
			if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
				errs.add(new ValidationError("filename", "The weather grid data file does not exist.", this));
			if (!this.projection.startsWith("attachment:/") && !Files.exists(Paths.get(this.projection)))
				errs.add(new ValidationError("projection", "The weather grid data projection file does not exist.", this));
		}
		
		return errs;
	}
	
	public static class WeatherGridGridFileBuilder {
		private String filename;

	    /**
	     * The location of the grid file (required).
	     */
		public WeatherGridGridFileBuilder filename(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The grid file does not exist");
			this.filename = value;
			return this;
		}
	}
}
