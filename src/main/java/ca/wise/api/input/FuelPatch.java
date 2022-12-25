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
import lombok.Setter;

/**
 * A fuel patch file.
 */
@Builder
@AllArgsConstructor
public class FuelPatch implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the fuel patch. The name must be unique amongst the fuel patch collection.
	 */
	@Getter
	@Builder.Default
	private String id = "flptch" + counter.getAndIncrement();

    /**
     * Set the name of the fuel patch. Must be unique amongst the fuel patch collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the fuel patch. The name must be unique amongst the fuel patch collection.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the fuel patch. Must be unique amongst the fuel patch collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}

	/**
	 * The fuel the patch changes to.
	 */
	@Getter @Setter
	@Builder.Default
	private String toFuel = "";

	/**
	 * Any comments about the fuel patch (optional).
	 */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

	/**
	 * The type of fuel patch (required).
	 */
	@Getter @Setter
	@Builder.Default
	private FuelPatchType type = FuelPatchType.INVALID;

	/**
	 * The filename associated with this fuel patch. Only valid if type is FILE.
	 */
	@Getter
	private String filename;

	/**
	 * Set the location of the file containing the fuel patch.
	 */
	public void setFilename(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The fuel patch file does not exist");
		this.filename = value;
	}

	/**
	 * An array of LatLon describing the fuel patch. Only valid if type is POLYGON.
	 */
	@Getter
	@Builder.Default
	private List<LatLon> feature = new ArrayList<>();

	/**
	 * The fuel that the patch changes from (one of this, {@link #fromFuelIndex}, or {@link #fromFuelRule} is required).
	 */
	@Getter @Setter
	private String fromFuel;

	/**
	 * The rule about which fuels to apply the patch to (one of this, {@link #fromFuelIndex}, or {@link #fromFuel} is required).
	 */
	@Getter @Setter
	@Builder.Default
	private FromFuel fromFuelRule = FromFuel.INVALID;

	/**
	 * Instead of using the name of a fuel, reference it by index.
	 */
	@Getter @Setter
	private Integer toFuelIndex;

	/**
	 * Instead of using the name of a fuel, reference it by index.
	 */
	@Getter @Setter
	private Integer fromFuelIndex;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the fuel patch.", this));

		if (this.type == FuelPatchType.FILE) {
			if (!SocketMsg.skipFileTests && !this.filename.startsWith("attachment:/") && !Files.exists(Paths.get(this.filename)))
				errs.add(new ValidationError("filename", "The fuel patch file does not exist.", this));
		}
		else if (this.type == FuelPatchType.POLYGON) {
			if (this.feature.size() == 0)
				errs.add(new ValidationError("feature", "No points have been added to the polygon fuel patch.", this));
		}
		else if (this.type == FuelPatchType.INVALID)
			errs.add(new ValidationError("type", "An invalid type has been set for the fuelbreak.", this));
		if (Strings.isNullOrEmpty(this.fromFuel) && this.fromFuelRule == FromFuel.INVALID && this.fromFuelIndex == null)
			errs.add(new ValidationError("fromFuel", "No from fuel has been set on the fuel patch.", this));
		if (Strings.isNullOrEmpty(this.toFuel) && this.toFuelIndex == null)
			errs.add(new ValidationError("toFuel", "No to fuel has been set on the fuel patch.", this));
		
		return errs;
	}
	
	public static class FuelPatchBuilder {
		private String filename;

		/**
		 * Set the location of the file containing the fuel patch.
		 */
		public FuelPatchBuilder filename(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The fuel patch file does not exist");
			this.filename = value;
			return this;
		}
	}
}
