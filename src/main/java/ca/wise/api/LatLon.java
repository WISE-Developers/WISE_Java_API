package ca.wise.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class to store location information.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LatLon {
	
    /**
     * The locations latitude.
     */
    @Getter @Setter
    @Builder.Default
    private double latitude = 0;
    
    /**
     * The locations longitude.
     */
    @Getter @Setter
    @Builder.Default
    private double longitude = 0;
}
