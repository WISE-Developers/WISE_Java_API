package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
import lombok.Setter;

@Builder
@AllArgsConstructor
public class FuelBreak implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the fuel break. The name must be unique amongst fuel break collections.
	 */
	@Getter
	@Builder.Default
	private String id = "flbrk" + counter.getAndIncrement();

    /**
     * Set the name of the fuel break. Must be unique amongst the fuel break collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the fuel break. The name must be unique amongst fuel break collections.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the fuel break. Must be unique amongst the fuel break collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the fuel break is not valid");
		this.id = value.replace("|", "");
	}
	
	/**
	 * The width of the fuel break (required if type is POLYLINE, otherwise ignored).
	 */
	@Getter
	private Double width;
	
	/**
	 * The width of the fuel break (required if type is POLYLINE, otherwise ignored).
	 */
	public void setWidth(double value) {
		if (SocketMsg.inlineThrowError && (value < 0 || value > 250))
			throw new WISEException("The width of the fuel break is not valid.");
		this.width = value;
	}
	
	@Getter @Setter
	@Builder.Default
	private String comments = "";
	
	@Getter @Setter
	@Builder.Default
	private FuelBreakType type = FuelBreakType.INVALID;
	
	@Getter
	private String filename;

	/**
	 * The location of the file containing the stream data (required).
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The fuel break file does not exist.");
		this.filename = value;
	}

	/**
	 * An array of LatLon describing the fuel break. Only valid if type is POLYLINE or POLYGON.
	 */
	@Getter
	@Builder.Default
	private final List<LatLon> feature = new ArrayList<>();

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the fuelbreak.", this));
		if (this.type == FuelBreakType.POLYLINE) {
			if (this.width < 0 || this.width > 250)
				errs.add(new ValidationError("width", "The fuelbreak width must be greater than 0 and less than 250m.", this));
		}
		else if (this.type == FuelBreakType.FILE) {
			if (!SocketMsg.skipFileTests && !this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
				errs.add(new ValidationError("filename", "The fuelbreak file does not exist.", this));
		}
		else if (this.type == FuelBreakType.POLYGON) {
			if (this.feature.size() == 0)
				errs.add(new ValidationError("feature", "No points have been added to teh polygon fuelbreak.", this));
		}
		else {
			errs.add(new ValidationError("type", "An invalid type has been set for the fuelbreak", this));
		}
		
		return errs;
	}
	
	public static class FuelBreakBuilder {
		private Double width;
		private String filename;
		
		/**
		 * The width of the fuel break (required if type is POLYLINE, otherwise ignored).
		 */
		public FuelBreakBuilder width(double value) {
			if (SocketMsg.inlineThrowError && (value < 0 || value > 250))
				throw new WISEException("The width of the fuel break is not valid.");
			this.width = value;
			return this;
		}

		/**
		 * The location of the file containing the stream data (required).
		 */
		public FuelBreakBuilder filename(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The fuel break file does not exist.");
			this.filename = value;
			return this;
		}
	}
}
