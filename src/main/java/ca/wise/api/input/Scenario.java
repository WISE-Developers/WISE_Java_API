package ca.wise.api.input;

import java.util.List;
import java.util.ArrayList;
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
 * Information about a weather stream.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scenario implements IWISESerializable {

	protected static AtomicInteger counter = new AtomicInteger(0);
	
	/**
	 * The name of the scenario. The name must be unique amongst the scenarios.
	 */
	@Getter
	@Builder.Default
	private String id = "scen" + counter.getAndIncrement();

    /**
     * Set the name of the scenario. Must be unique amongst the scenario collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setId(String value) {
		this.setName(value);
	}

	/**
	 * The name of the scenario. The name must be unique amongst the scenarios.
	 */
	public String getName() {
		return id;
	}

    /**
     * Set the name of the scenario. Must be unique amongst the scenario collection. Cannot be null or empty.
	 * @throws A {@link WISEException} will be thrown if value is null or empty.
     */
	public void setName(String value) {
		if (SocketMsg.inlineThrowError && Strings.isNullOrEmpty(value))
			throw new WISEException("The ID of the weather stream is not valid");
		this.id = value.replace("|", "");
	}
	
	@Builder.Default
	protected boolean copy = false;

    /**
     * The scenario start time (required).
     */
	@Getter @Setter
	private String startTime;

    /**
     * The scenario end time (required).
     */
	@Getter @Setter
	private String endTime;
	
	/**
	 * The scenarios display interval (required).
	 */
	@Getter @Setter
	@Builder.Default
	private String displayInterval = "PT1H";

    /**
     * User comments about the scenario (optional).
     */
	@Getter @Setter
	@Builder.Default
	private String comments = "";

    /**
     * The FGM option values.
     */
	@Getter @Setter
	@Builder.Default
	private FGMOptions fgmOptions = new FGMOptions();

    /**
     * The FBP option values.
     */
	@Getter @Setter
	@Builder.Default
	private FBPOptions fbpOptions = new FBPOptions();

	/**
	 * The FMC option values.
	 */
	@Getter @Setter
	@Builder.Default
	private FMCOptions fmcOptions = new FMCOptions();

	/**
	 * The FWI option values.
	 */
	@Getter @Setter
	@Builder.Default
	private FWIOptions fwiOptions = new FWIOptions();

	/**
	 * References to weather stations and streams.
	 */
	@Getter
	@Builder.Default
	private List<StationStream> stationStreams = new ArrayList<>();

	/**
	 * A set of burning conditions.
	 */
	@Getter
	@Builder.Default
	private List<BurningConditions> burningConditions = new ArrayList<>();

	/**
	 * A list of vectors used by this scenario.
	 */
	@Getter
	@Builder.Default
	private List<String> vectorInfo = new ArrayList<>();

	/**
	 * A list of ignitions used by this scenario.
	 */
	@Getter
	@Builder.Default
	private List<IgnitionReference> ignitionInfo = new ArrayList<>();

	/**
	 * A list of grids used by the scenario. The list contains an index value that defines the order of the layers.
	 */
	@Getter @Setter
	@Builder.Default
	private List<LayerInfo> layerInfo = new ArrayList<>();

	/**
	 * A list of assets used by this scenario. Assets will be used to end simulations early when a firefront
	 * reaches the shape.
	 */
	@Getter
	@Builder.Default
	private List<AssetReference> assetFiles = new ArrayList<>();
	
	/**
	 * A list of seasonal condition.
	 */
	@Getter
	@Builder.Default
	private List<SeasonalCondition> seasonalConditions = new ArrayList<>();

	/**
	 * A target used by this scenario to modify the wind direction.
	 */
	@Getter
	private TargetReference windTargetFile;

	/**
	 * A target used by this scenario to modify the vector behaviour.
	 */
	@Getter
	private TargetReference vectorTargetFile;

	/**
	 * The name of the scenario that will be copied.
	 */
	@Getter @Setter
	private String scenToCopy;
	
	@Getter @Setter
	private StopModellingOptions stopModellingOptions;

	/**
	 * Add a new burning condition.
	 * @param date The date that the condition is valid on. Must be formatted as 'YYYY-MM-DD'.
	 * @param startTime The starting hour. Must be between 0 and 23 inclusive.
	 * @param endTime The ending hour. Must be between 1 and 24 inclusive.
	 * @param fwiGreater The minimum FWI value that will allow burning.
	 * @param wsGreater The minimum wind speed that will allow burning.
	 * @param rhLess The maximum relative humidity that will allow burning (as a percent [0-100]).
	 * @param isiGreater The minimum ISI that will allow burning.
	 */
	public BurningConditions addBurningCondition(String date, String startTime, String endTime,
			double fwiGreater, double wsGreater, double rhLess, double isiGreater) {
		BurningConditions bc = BurningConditions.builder()
				.date(date)
				.startTime(startTime)
				.endTime(endTime)
				.fwiGreater(fwiGreater)
				.wsGreater(wsGreater)
				.rhLess(rhLess)
				.isiGreater(isiGreater)
				.build();
		this.burningConditions.add(bc);
		return bc;
	}

	/**
	 * Add an ignition to the scenario.
	 * @param ignition The ignition to add to the scenario.
	 */
	public IgnitionReference addIgnitionReference(Ignition ignition) {
		IgnitionReference ref = IgnitionReference.builder()
				.ignition(ignition.getId())
				.build();
		this.ignitionInfo.add(ref);
		return ref;
	}

	/**
	 * Add a weather stream to the scenario.
	 * @param stream The weather stream to add to the scenario.
	 */
	public StationStream addWeatherStreamReference(WeatherStream stream) {
		StationStream ref = StationStream.builder()
				.station(stream.getParentId())
				.stream(stream.getId())
				.build();
		this.stationStreams.add(ref);
		return ref;
	}

	/**
	 * Add a weather stream to the scenario.
	 * @param stream The weather stream to add to the scenario.
	 * @param primary Is this the primary weather stream for the scenario.
	 */
	public StationStream addWeatherStreamReference(WeatherStream stream, boolean primary) {
		StationStream ref = StationStream.builder()
				.station(stream.getParentId())
				.stream(stream.getId())
				.primaryStream(primary)
				.build();
		this.stationStreams.add(ref);
		return ref;
	}

	/**
	 * Add a fuel break to the scenario.
	 * @param brck The fuel break to add to the scenario.
	 */
	public FuelBreak addFuelBreakReference(FuelBreak brck) {
		this.vectorInfo.add(brck.getId());
		return brck;
	}

	/**
	 * Add a weather grid to the scenario.
	 * @param wthr The weather grid to add to the scenario.
	 * @param index The layers index in the scenario.
	 * @return LayerInfo The reference that was just added.
	 */
	public LayerInfo addWeatherGridReference(WeatherGrid wthr, int index) {
		LayerInfo ref = LayerInfo.builder()
				.name(wthr.getId())
				.index(index)
				.build();
		this.layerInfo.add(ref);
		return ref;
	}

	/**
	 * Add a grid file to the scenario.
	 * @param grid The grid file to add to the scenario.
	 * @param index The layers index in the scenario.
	 * @return LayerInfo The reference that was just added.
	 */
	public LayerInfo addGridFileReference(GridFile wthr, int index) {
		LayerInfo ref = LayerInfo.builder()
				.name(wthr.getId())
				.index(index)
				.build();
		this.layerInfo.add(ref);
		return ref;
	}

	/**
	 * Add a fuel patch to the scenario.
	 * @param patch The fuel patch to add to the scenario.
	 * @param index The layers index in the scenario.
	 * @return LayerInfo The reference that was just added.
	 */
	public LayerInfo addFuelPatchReference(FuelPatch wthr, int index) {
		LayerInfo ref = LayerInfo.builder()
				.name(wthr.getId())
				.index(index)
				.build();
		this.layerInfo.add(ref);
		return ref;
	}

	/**
	 * Add a weather patch to the scenario.
	 * @param patch The weather patch to add to the scenario.
	 * @param index The layers index in the scenario.
	 * @return LayerInfo The reference that was just added.
	 */
	public LayerInfo addWeatherPatchReference(WeatherPatch wthr, int index) {
		LayerInfo ref = LayerInfo.builder()
				.name(wthr.getId())
				.index(index)
				.build();
		this.layerInfo.add(ref);
		return ref;
	}

	/**
	 * Add an asset file to the scenario. Must already be added to the {@link WISE} object.
	 * @param file The asset file to add to the scenario.
	 */
	public AssetReference addAssetFile(AssetFile file) {
		AssetReference ref = AssetReference.builder()
				.name(file.getId())
				.build();
		this.assetFiles.add(ref);
		return ref;
	}

	/**
	 * Add a target file to the scenario for wind direction. Must already be added to the {@link WISE} object.
	 * @param file The target file to add to the scenario.
	 */
	public TargetReference setWindTargetFile(TargetFile file) {
		this.windTargetFile = TargetReference.builder()
				.name(file.getId())
				.build();
		return this.windTargetFile;
	}

	/**
	 * Add a target file to the scenario for wind direction. Must already be added to the {@link WISE} object.
	 * @param file The target file to add to the scenario.
	 */
	public void setWindTargetFile(TargetReference file) {
		this.windTargetFile = file;
	}

	/**
	 * Add a target file to the scenario for vector direction. Must already be added to the {@link WISE} object.
	 * @param file The target file to add to the scenario.
	 */
	public TargetReference setVectorTargetFile(TargetFile file) {
		this.vectorTargetFile = TargetReference.builder()
				.name(file.getId())
				.build();
		return this.windTargetFile;
	}

	/**
	 * Add a target file to the scenario for vector direction. Must already be added to the {@link WISE} object.
	 * @param file The target file to add to the scenario.
	 */
	public void setVectorTargetFile(TargetReference file) {
		this.vectorTargetFile = file;
	}
	
	/**
	 * Make this scenario a copy of another.
	 * @param toCopy The scenario to copy.
	 */
	public void makeCopy(Scenario toCopy) {
		this.copy = true;
		this.scenToCopy = toCopy.getId();
	}

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(id))
			errs.add(new ValidationError("id", "No ID/name has been set for the scenario.", this));
		if (this.copy) {
			if (Strings.isNullOrEmpty(this.scenToCopy))
				errs.add(new ValidationError("scenToCopy", "The scenario has been specified as a copy of another but the other scenarios ID was not set.", this));
		}
		else {
			if (this.startTime == null)
				errs.add(new ValidationError("startTime", "The start time for the scenario has not been set.", this));
			if (this.endTime == null)
				errs.add(new ValidationError("endTime", "The end time for the scenario has not been set.", this));
			if (this.displayInterval == null)
				errs.add(new ValidationError("displayInterval", "The scenario display interval is not set.", this));
			List<ValidationError> tempErr = this.fgmOptions.checkValid();
			if (tempErr.size() > 0) {
				ValidationError temp = new ValidationError("fgmOptions", "Invalid FGM options.", this);
				tempErr.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			tempErr = this.fbpOptions.checkValid();
			if (tempErr.size() > 0) {
				ValidationError temp = new ValidationError("fbpOptions", "Invalid FBP options.", this);
				tempErr.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			tempErr = this.fmcOptions.checkValid();
			if (tempErr.size() > 0) {
				ValidationError temp = new ValidationError("fmcOptions", "Invalid FMC options.", this);
				tempErr.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			tempErr = this.fwiOptions.checkValid();
			if (tempErr.size() > 0) {
				ValidationError temp = new ValidationError("fwiOptions", "Invalid FWI options.", this);
				tempErr.stream().forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			ValidationError burns = new ValidationError("burningConditions", "Errors in burning conditions.", this);
			for (int i = 0; i < this.burningConditions.size(); i++) {
				ValidationError burnErr = new ValidationError(i, "Errors found in burn condition at " + i + ".", this.burningConditions);
				this.burningConditions.get(i).checkValid().forEach(x -> burnErr.addChild(x));
				if (burnErr.getChildren().size() > 0)
					burns.addChild(burnErr);
			}
			if (burns.getChildren().size() > 0)
				errs.add(burns);
			List<ValidationError> stationErrs = new ArrayList<>();
			for (int i = 0; i < this.stationStreams.size(); i++) {
				ValidationError station = new ValidationError(i, "Errors found in weather stream reference at " + i + ".", this.stationStreams);
				this.stationStreams.get(i).checkValid().forEach(x -> station.addChild(x));
				if (station.getChildren().size() > 0)
					stationErrs.add(station);
			}
			if (stationErrs.size() > 0) {
				ValidationError temp = new ValidationError("stationStreams", "Errors found in weather stream references.", this);
				stationErrs.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			List<ValidationError> vectorErrs = new ArrayList<>();
			for (int i = 0; i < this.vectorInfo.size(); i++) {
				if (this.vectorInfo.get(i).length() == 0) {
					ValidationError vector = new ValidationError(i, "Errors found in vector reference at " + i + ".", this.vectorInfo);
					vector.addChild(new ValidationError(i, "No vector ID has been specified.", this.vectorInfo));
				}
			}
			if (vectorErrs.size() > 0) {
				ValidationError temp = new ValidationError("vectorInfo", "Errors found in vector references.", this);
				vectorErrs.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			List<ValidationError> ignitionErrs = new ArrayList<>();
			for (int i = 0; i < this.ignitionInfo.size(); i++) {
				ValidationError station = new ValidationError(i, "Errors found in ignition reference at " + i + ".", this.ignitionInfo);
				this.ignitionInfo.get(i).checkValid().forEach(x -> station.addChild(x));
				if (station.getChildren().size() > 0)
					ignitionErrs.add(station);
			}
			if (ignitionErrs.size() > 0) {
				ValidationError temp = new ValidationError("ignitionInfo", "Errors found in ignition references.", this);
				ignitionErrs.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			List<ValidationError> layerErrs = new ArrayList<>();
			for (int i = 0; i < this.layerInfo.size(); i++) {
				ValidationError station = new ValidationError(i, "Errors found in layer reference at " + i + ".", this.layerInfo);
				this.layerInfo.get(i).checkValid().forEach(x -> station.addChild(x));
				if (station.getChildren().size() > 0)
					layerErrs.add(station);
			}
			if (layerErrs.size() > 0) {
				ValidationError temp = new ValidationError("layerInfo", "Errors found in layer references.", this);
				layerErrs.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			List<ValidationError> assetErrs = new ArrayList<>();
			for (int i = 0; i < this.assetFiles.size(); i++) {
				ValidationError station = new ValidationError(i, "Errors found in asset reference at " + i + ".", this.assetFiles);
				this.assetFiles.get(i).checkValid().forEach(x -> station.addChild(x));
				if (station.getChildren().size() > 0)
					assetErrs.add(station);
			}
			if (assetErrs.size() > 0) {
				ValidationError temp = new ValidationError("assetFiles", "Errors found in asset references.", this);
				assetErrs.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			List<ValidationError> seasonalErrors = new ArrayList<>();
			for (int i = 0; i < this.seasonalConditions.size(); i++) {
				ValidationError station = new ValidationError(i, "Errors found in seasonal condition at " + i + ".", this.seasonalConditions);
				this.seasonalConditions.get(i).checkValid().forEach(x -> station.addChild(x));
				if (station.getChildren().size() > 0)
					seasonalErrors.add(station);
			}
			if (seasonalErrors.size() > 0) {
				ValidationError temp = new ValidationError("seasonalConditions", "Errors found in seasonal conditions.", this);
				seasonalErrors.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
			if (this.windTargetFile != null) {
				ValidationError temp = new ValidationError("windTargetFile", "Errors found in target reference.", this);
				this.windTargetFile.checkValid().forEach(x -> temp.addChild(x));
				if (temp.getChildren().size() > 0)
					errs.add(temp);
			}
			if (this.vectorTargetFile != null) {
				ValidationError temp = new ValidationError("vectorTargetFile", "Errors found in target reference.", this);
				this.vectorTargetFile.checkValid().forEach(x -> temp.addChild(x));
				if (temp.getChildren().size() > 0)
					errs.add(temp);
			}
		}
		
		return errs;
	}
}
