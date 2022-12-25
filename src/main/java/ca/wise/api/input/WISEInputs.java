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
 * A class that holds information about the files and settings that will be inputs to W.I.S.E..
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WISEInputs implements IWISESerializable {
	
	/**
	 * Information about the files that may be needed by the scenarios.
	 */
	@Getter @Setter
	@Builder.Default
	private WISEInputFiles files = new WISEInputFiles();
	
	/**
	 * All weather stations. At least one is required.
	 */
	@Getter
	@Builder.Default
	private List<WeatherStation> weatherStations = new ArrayList<>();
	
	/**
	 * All ignition features.
	 */
	@Getter
	@Builder.Default
	private List<Ignition> ignitions = new ArrayList<>();
	
	/**
	 * The scenarios to run. At least one is required.
	 */
	@Getter
	@Builder.Default
	private List<Scenario> scenarios = new ArrayList<>();
	
	/**
	 * The timezone of the scenarios (required).
	 */
	@Getter @Setter
	@Builder.Default
	private Timezone timezone = new Timezone();
	
	/**
	 * Options to apply to the fuel types in the LUT file.
	 */
	@Getter
	@Builder.Default
	private List<FuelOption> fuelOptions = new ArrayList<>();
	
	/**
	 * Assets that can stop simulations when reached.
	 */
	@Getter
	@Builder.Default
	private List<AssetFile> assetFiles = new ArrayList<>();
	
	/**
	 * Targets that can affect how weather information is processed.
	 */
	@Getter
	@Builder.Default
	private List<TargetFile> targetFiles = new ArrayList<>();

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		List<ValidationError> tz = this.timezone.checkValid();
		if (tz.size() > 0) {
			ValidationError temp = new ValidationError("timezone", "Errors in the simulation timezone.", this);
			tz.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> fls = this.files.checkValid();
		if (fls.size() > 0) {
			ValidationError temp = new ValidationError("files", "Errors in W.I.S.E. input files.", this);
			fls.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		if (this.weatherStations.size() < 1)
			errs.add(new ValidationError("weatherStations", "No weather stations have been added.", this));
		if (this.scenarios.size() < 1)
			errs.add(new ValidationError("scenarios", "No scenarios have been added.", this));
		List<ValidationError> weatherStationErrors = new ArrayList<>();
		for (int i = 0; i < this.weatherStations.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in weather station at index " + i + ".", this.weatherStations);
			for (int j = i + 1; j < this.weatherStations.size(); j++) {
				if (this.weatherStations.get(i).getId().equalsIgnoreCase(this.weatherStations.get(j).getId())) {
					wsErr.addChild(new ValidationError("id", "Duplicate weather station IDs.", this.weatherStations.get(i)));
					break;
				}
			}
			this.weatherStations.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0)
				weatherStationErrors.add(wsErr);
		}
		if (weatherStationErrors.size() > 0) {
			ValidationError temp = new ValidationError("weatherStations", "Errors found in weather stations.", this);
			weatherStationErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> ignitionErrors = new ArrayList<>();
		for (int i = 0; i < this.ignitions.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in ignition at index " + i + ".", this.ignitions);
			for (int j = i + 1; j < this.weatherStations.size(); j++) {
				if (this.ignitions.get(i).getId().equalsIgnoreCase(this.ignitions.get(j).getId())) {
					wsErr.addChild(new ValidationError("id", "Duplicate ignition IDs.", this.ignitions.get(i)));
					break;
				}
			}
			this.ignitions.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0)
				ignitionErrors.add(wsErr);
		}
		if (ignitionErrors.size() > 0) {
			ValidationError temp = new ValidationError("ignitions", "Errors found in weather stations.", this);
			ignitionErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> scenarioErrors = new ArrayList<>();
		for (int i = 0; i < this.scenarios.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in scenario at index " + i + ".", this.scenarios);
			for (int j = i + 1; j < this.scenarios.size(); j++) {
				if (this.scenarios.get(i).getId().equalsIgnoreCase(this.scenarios.get(j).getId())) {
					wsErr.addChild(new ValidationError("id", "Duplicate scenario IDs.", this.scenarios.get(i)));
					break;
				}
			}
			this.scenarios.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0)
				scenarioErrors.add(wsErr);
		}
		if (scenarioErrors.size() > 0) {
			ValidationError temp = new ValidationError("scenarios", "Errors found in scenarios.", this);
			scenarioErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> assetErrors = new ArrayList<>();
		for (int i = 0; i < this.assetFiles.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in asset at index " + i + ".", this.assetFiles);
			for (int j = i + 1; j < this.assetFiles.size(); j++) {
				if (this.assetFiles.get(i).getId().equalsIgnoreCase(this.assetFiles.get(j).getId())) {
					wsErr.addChild(new ValidationError("id", "Duplicate asset IDs.", this.assetFiles.get(i)));
					break;
				}
			}
			this.assetFiles.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0)
				assetErrors.add(wsErr);
		}
		if (assetErrors.size() > 0) {
			ValidationError temp = new ValidationError("assetFiles", "Errors found in assets.", this);
			assetErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> targetErrors = new ArrayList<>();
		for (int i = 0; i < this.targetFiles.size(); i++) {
			ValidationError wsErr = new ValidationError(i, "Errors found in target at index " + i + ".", this.targetFiles);
			for (int j = i + 1; j < this.targetFiles.size(); j++) {
				if (this.targetFiles.get(i).getId().equalsIgnoreCase(this.targetFiles.get(j).getId())) {
					wsErr.addChild(new ValidationError("id", "Duplicate target IDs.", this.targetFiles.get(i)));
					break;
				}
			}
			this.targetFiles.get(i).checkValid().forEach(x -> wsErr.addChild(x));
			if (wsErr.getChildren().size() > 0)
				targetErrors.add(wsErr);
		}
		if (targetErrors.size() > 0) {
			ValidationError temp = new ValidationError("targetFiles", "Errors found in targets.", this);
			targetErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		List<ValidationError> fuelOptionErrors = new ArrayList<>();
		for (int i = 0; i < this.fuelOptions.size(); i++) {
			List<ValidationError> foErrs = this.fuelOptions.get(i).checkValid();
			if (foErrs.size() > 0) {
				ValidationError wsErr = new ValidationError(i, "Errors found in fuel option at index " + i + ".", this.fuelOptions);
				foErrs.forEach(x -> wsErr.addChild(x));
				fuelOptionErrors.add(wsErr);
			}
		}
		if (fuelOptionErrors.size() > 0) {
			ValidationError temp = new ValidationError("fuelOptions", "Errors found in fuel options.", this);
			fuelOptionErrors.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		return errs;
	}
}
