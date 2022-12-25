package ca.wise.api.color;

import ca.wise.api.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A color defined by hue, saturation, and luminance.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HSLColor implements Color {

    /**
     * The hue of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int hue;


    /**
     * The saturation of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int saturation;

    /**
     * The luminance of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int luminance;

	@Override
	public String stream() {
        return "HSL|" + hue + "|" + saturation + "|" + luminance;
	}
}
