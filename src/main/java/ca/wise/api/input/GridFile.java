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
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Information about a grid input file.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridFile implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the grid file. Must be unique amongst the grid file collection.
	 */
	@Getter
	@Builder.Default
	private String id = "grdfl" + counter.getAndIncrement();

    /**
     * Set the name of the grid file. Must be unique amongst the grid file collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the grid file. Must be unique amongst the grid file collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the grid file. Must be unique amongst the grid file collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

	/**
	 * Comment about the grid file (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String comment = "";
	
	@Getter @Setter
	@Builder.Default
	private GridFileType type = GridFileType.INVALID;
	
	/**
	 * The location of the file containing the grid data (required).
	 */
	@Getter
	@Builder.Default
	private String filename = "";

	/**
	 * The location of the file containing the grid data (required).
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The grid file does not exist");
		this.filename = value;
	}
	
	/**
	 * The projection file for the grid file (required).
	 */
	@Getter
	@Builder.Default
	private String projection = "";

	/**
	 * The projection file for the grid file (required).
	 */
	public void setProjection(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The grid file projection does not exist");
		this.projection = value;
	}

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather stream.", this));
		if (this.type == GridFileType.INVALID)
			errs.add(new ValidationError("type", "An invalid type has been set on the grid file.", this));
		if (!SocketMsg.skipFileTests) {
			if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
				errs.add(new ValidationError("filename", "The grid file does not exist.", this));
			if (!this.projection.startsWith("attachment:/") && !Files.exists(Paths.get(this.projection)))
				errs.add(new ValidationError("projection", "The grid file projection does not exist.", this));
		}
		
		return errs;
	}
}
