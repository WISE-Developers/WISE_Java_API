package ca.wise.api.input;

import java.util.ArrayList;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A timezone.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timezone implements IWISESerializable {

    /**
     * Is the timezone currently in daylight savings time.
     */
	@Getter @Setter
	@Builder.Default
	private boolean dst = false;

    /**
     * The offset from GMT.
     */
	@Getter @Setter
	private String offset;

	/**
	 * Optional value of the timezone. If set to a valid value, the offset will be
	 * looked up once the job is submitted so {@link Timezone#offset} and {@link Timezone#dst}
	 * will be ignored.
	 */
	@Getter @Setter
	private Integer value;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (this.value == null) {
			if (this.offset == null)
				errs.add(new ValidationError("offset", "The timezone offset is not valid.", this));
		}
		
		return errs;
	}
}
