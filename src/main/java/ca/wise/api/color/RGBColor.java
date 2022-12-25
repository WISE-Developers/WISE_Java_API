package ca.wise.api.color;

import ca.wise.api.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A color defined by red, green, and blue.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RGBColor implements Color {

    /**
     * The red component of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int red;

    /**
     * The green component of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int green;

    /**
     * The blue component of the color as an integer in [0, 255].
     */
	@Getter @Setter
	private int blue;

	@Override
	public String stream() {
        return "RGB|" + red + "|" + green + "|" + blue;
	}
}
