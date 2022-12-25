package ca.wise.api.output;

import java.util.Collections;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.units.AngleUnit;
import ca.wise.api.units.AreaUnit;
import ca.wise.api.units.CoordinateUnit;
import ca.wise.api.units.DistanceUnit;
import ca.wise.api.units.EnergyUnit;
import ca.wise.api.units.IntensityUnit;
import ca.wise.api.units.MassAreaUnit;
import ca.wise.api.units.MassUnit;
import ca.wise.api.units.TemperatureUnit;
import ca.wise.api.units.TimeUnit;
import ca.wise.api.units.VelocityUnit;
import ca.wise.api.units.VolumeUnit;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitSettings implements IWISESerializable {
	
	/**
	 * Units for displaying small distance measurements.
	 */
	@Getter @Setter
	@Builder.Default
	private DistanceUnit smallMeasureOutput = DistanceUnit.DEFAULT;
	
	/**
	 * Units for displaying small distances.
	 */
	@Getter @Setter
	@Builder.Default
	private DistanceUnit smallDistanceOutput = DistanceUnit.DEFAULT;
	
	/**
	 * Units for displaying distances.
	 */
	@Getter @Setter
	@Builder.Default
	private DistanceUnit distanceOutput = DistanceUnit.DEFAULT;
	
	/**
	 * Alternate units for displaying distances.
	 */
	@Getter @Setter
	@Builder.Default
	private DistanceUnit alternateDistanceOutput = DistanceUnit.DEFAULT;
	
	/**
	 * Units for displaying coordinates.
	 */
	@Getter @Setter
	@Builder.Default
	private CoordinateUnit coordinateOutput = CoordinateUnit.DEFAULT;
	
	/**
	 * Units for displaying areas.
	 */
	@Getter @Setter
	@Builder.Default
	private AreaUnit areaOutput = AreaUnit.DEFAULT;
	
	/**
	 * Units for displaying volumes.
	 */
	@Getter @Setter
	@Builder.Default
	private VolumeUnit volumeOutput = VolumeUnit.DEFAULT;
	
	/**
	 * Units for displaying temperature.
	 */
	@Getter @Setter
	@Builder.Default
	private TemperatureUnit temperatureOutput = TemperatureUnit.DEFAULT;
	
	/**
	 * Units for displaying mass or weight.
	 */
	@Getter @Setter
	@Builder.Default
	private MassUnit massOutput = MassUnit.DEFAULT;
	
	/**
	 * Units for displaying energy.
	 */
	@Getter @Setter
	@Builder.Default
	private EnergyUnit energyOutput = EnergyUnit.DEFAULT;
	
	/**
	 * Units for displaying angles.
	 */
	@Getter @Setter
	@Builder.Default
	private AngleUnit angleOutput = AngleUnit.DEFAULT;
	
	/**
	 * Units for displaying velocity.
	 */
	@Getter @Setter
	@Builder.Default
	private VelocityUnit velocityOutput = new VelocityUnit();
	
	/**
	 * An alternate unit for displaying velocity.
	 */
	@Getter @Setter
	@Builder.Default
	private VelocityUnit alternateVelocityOutput = new VelocityUnit();
	
	/**
	 * Units for displaying fire intensity.
	 */
	@Getter @Setter
	@Builder.Default
	private IntensityUnit intensityOutput = new IntensityUnit();
	
	/**
	 * Units for displaying mass.
	 */
	@Getter @Setter
	@Builder.Default
	private MassAreaUnit massAreaOutput = new MassAreaUnit();

	@Override
	public List<ValidationError> checkValid() {
		return Collections.emptyList();
	}
	
	public boolean anyValid() {
		return smallMeasureOutput != DistanceUnit.DEFAULT || smallDistanceOutput != DistanceUnit.DEFAULT ||
				distanceOutput != DistanceUnit.DEFAULT || alternateDistanceOutput != DistanceUnit.DEFAULT ||
				coordinateOutput != CoordinateUnit.DEFAULT || areaOutput != AreaUnit.DEFAULT ||
				volumeOutput != VolumeUnit.DEFAULT || temperatureOutput != TemperatureUnit.DEFAULT ||
				massOutput != MassUnit.DEFAULT || energyOutput != EnergyUnit.DEFAULT ||
				angleOutput != AngleUnit.DEFAULT || velocityOutput.getDistance() != DistanceUnit.DEFAULT ||
				velocityOutput.getTime() != TimeUnit.DEFAULT || alternateVelocityOutput.getDistance() != DistanceUnit.DEFAULT ||
				alternateVelocityOutput.getTime() != TimeUnit.DEFAULT || intensityOutput.getEnergy() != EnergyUnit.DEFAULT ||
				intensityOutput.getDistance() != DistanceUnit.DEFAULT || massAreaOutput.getMass() != MassUnit.DEFAULT ||
				massAreaOutput.getArea() != AreaUnit.DEFAULT;
	}
}
