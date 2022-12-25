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
 * Information about an ignition input.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ignition implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the ignition. The name must be unique amongst ignition collections.
	 */
	@Getter
	@Builder.Default
	private String id = "ign" + counter.getAndIncrement();

    /**
     * Set the name of the ignition. Must be unique amongst the ignition collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the ignition. The name must be unique amongst ignition collections.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the ignition. Must be unique amongst the ignition collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

    /**
     * User comments about the ignition (optional).
     */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

    /**
     * The ignition start time (required).
     */
	@Getter @Setter
	private String startTime;

    /**
     * The type of ignition (required).
     */
	@Getter @Setter
	@Builder.Default
	private IgnitionType type = IgnitionType.INVALID;
	
	/**
	 * Get the location of the file containing the ignition.
	 */
	@Getter
	@Builder.Default
	private String filename = "";

	/**
	 * Get the location of the file containing the ignition.
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The ignition file does not exist");
		this.filename = value;
	}

    /**
     * An array of LatLon describing the ignition. Only valid if type is POLYLINE, POLYGON, or POINT.
     */
	@Getter
	@Builder.Default
	private List<LatLon> feature = new ArrayList<>();

	/**
	 * A list of attributes for the ignition. Not valid for {@link #filename} types.
	 * Valid types for the value are Integer, Long, Double, and String.
	 */
	@Getter
	@Builder.Default
	private List<AttributeEntry> attributes = new ArrayList<>();

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather stream.", this));
		if (this.startTime == null)
			errs.add(new ValidationError("startTime", "No start time has been set for the ignition.", this));
		if (this.type == IgnitionType.FILE) {
			if (!SocketMsg.skipFileTests) {
				if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
					errs.add(new ValidationError("filename", "The specified ignition file does not exist.", this));
			}
		}
		else if (this.type != IgnitionType.INVALID) {
			if (this.feature.size() == 0)
				errs.add(new ValidationError("feature", "No locations have been added to the ignition.", this));
		}
		else
			errs.add(new ValidationError("type", "Invalid ignition type.", this));
		
		return errs;
	}
}
