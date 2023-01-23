package ca.wise.api.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Options that define how and if wind gusting is applied to a scenario.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GustingOptions {
    
    @Getter @Setter
    private Gusting gusting;
    
    /**
     * Must be available for time derived gusting.
     */
    @Getter @Setter
    private Integer gustsPerHour;
    
    /**
     * Must be available for average, time derived, and ROS derived gusting.
     * For average gusting this is a weighted averaging of wind speed and gusting. ws = ((100-percentGusting)*ws + percentGusting*gust)/100.
     * For time derived gusting gusts will occur for (3600/gustPerHour*(percentGusting*100)) seconds per gust.
     * For ROS derived gusting gusts will occur for (3600*(percentGusting/100)) seconds per hour.
     */
    @Getter @Setter
    private Double percentGusting;
    
    /**
     * Must be present for time and ROS derived gusting. Middle is not valid for ROS derived gusting.
     */
    @Getter @Setter
    private GustBias gustBias;
    
    public enum Gusting {
        NO_GUSTING(0),
        AVERAGE_GUSTING(1),
        TIME_DERIVED_GUSTING(2),
        ROS_DERIVED_GUSTING(3);
        
        public final int value;
        
        Gusting(int value) {
            this.value = value;
        }
        
        public static Gusting fromInt(int value) {
            Gusting[] gusts = values();
            for (int i = 0; i < gusts.length; i++) {
                if (gusts[i].value == value)
                    return gusts[i];
            }
            return NO_GUSTING;
        }
    }
    
    public enum GustBias{
        MIDDLE(0),
        START(1),
        END(2);
        
        public final int value;
        
        GustBias(int value) {
            this.value = value;
        }
        
        public static GustBias fromInt(int value) {
            GustBias[] gusts = values();
            for (int i = 0; i < gusts.length; i++) {
                if (gusts[i].value == value)
                    return gusts[i];
            }
            return START;
        }
    }
}
