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
 * An asset that can be used to stop a simulation early.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetFile implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the asset. The name must be unique amongst asset file collections.
	 */
	@Getter
	@Builder.Default
	private String id = "asset" + counter.getAndIncrement();

    /**
     * Set the name of the asset. Must be unique amongst the asset collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the asset. The name must be unique amongst asset file collections.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the asset. Must be unique amongst the asset collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

    /**
     * User comments about the asset (optional).
     */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

    /**
     * The type of asset (required).
     */
	@Getter @Setter
	@Builder.Default
	private AssetShapeType type = AssetShapeType.INVALID;
	
	/**
	 * The filename associated with this asset. Only valid if type is FILE.
	 */
	@Getter
	@Builder.Default
	private String filename = "";

	/**
	 * The filename associated with this asset. Only valid if type is FILE.
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The asset file does not exist");
		this.filename = value;
	}

    /**
     * An array of LatLon describing the asset. Only valid if type is POLYLINE, POLYGON, or POINT.
     */
	@Getter
	@Builder.Default
	private List<LatLon> feature = new ArrayList<>();

	/**
	 * The buffer size to use for line or point assets. If null, no buffer will be used.
	 */
	@Getter @Setter
	private Double buffer;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the weather stream.", this));
		if (this.type == AssetShapeType.FILE) { 
			if (!SocketMsg.skipFileTests) {
				if (!this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
					errs.add(new ValidationError("filename", "The specified asset file does not exist.", this));
			}
		}
		else if (this.type == AssetShapeType.POLYLINE || this.type == AssetShapeType.POLYGON || this.type == AssetShapeType.POINT) {
			if (this.feature.size() == 0)
				errs.add(new ValidationError("feature", "No locations have been added to the asset.", this));
		}
		else
			errs.add(new ValidationError("type", "Invalid asset type.", this));
		
		return errs;
	}
}
