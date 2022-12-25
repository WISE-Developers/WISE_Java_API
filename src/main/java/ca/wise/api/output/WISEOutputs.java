package ca.wise.api.output;

import java.util.ArrayList;
import java.util.List;

import ca.wise.api.IWISESerializable;
import ca.wise.api.input.Scenario;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Information about which files should be output from the job.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WISEOutputs implements IWISESerializable {
	
	/**
	 * The vector files that should be output (optional).
	 */
	@Getter
	@Builder.Default
	private final List<VectorFile> vectorFiles = new ArrayList<>();
	
	/**
	 * The grid files that should be output (optional).
	 */
	@Getter
	@Builder.Default
	private final List<GridFile> gridFiles = new ArrayList<>();
	
	/**
	 * The fuel grid files that should be output (optional).
	 */
	@Getter
	@Builder.Default
	private final List<FuelGridFile> fuelGridFiles = new ArrayList<>();
	
	/**
	 * The summary files that should be output (optional).
	 */
	@Getter
	@Builder.Default
	private final List<SummaryFile> summaryFiles = new ArrayList<>();
	
	/**
	 * Output a stats file with information from each scenario timestep.
	 */
	@Getter
	@Builder.Default
	private final List<StatsFile> statsFiles = new ArrayList<>();
	
	/**
	 * Output an asset stats file with information from each scenario timestep.
	 */
	@Getter
	@Builder.Default
	private final List<AssetStatsFile> assetStatsFiles = new ArrayList<>();

	/**
	 * The default stream status for all newly created output
	 * files. If true, newly created output files will be
	 * defaulted to streaming to any specified stream
	 * locations. If false, newly created output files will
	 * be defaulted to not stream. The user can override
	 * this setting on each output file.
	 */
	@Getter @Setter
	@Builder.Default
	private boolean streamAll = false;

	/**
	 * Create a new vector file and add it to the list of
	 * vector file outputs.
	 * @param scen The scenario to output the vector for.
	 */
	public VectorFile newVectorFile(Scenario scenario) {
		VectorFile file = new VectorFile();
		file.setScenarioName(scenario.getId());
		file.setShouldStream(this.streamAll);
		this.vectorFiles.add(file);
		return file;
	}

	/**
	 * Create a new grid file and add it to the list of
	 * grid file outputs.
	 * @param scen The scenario to output the grid for.
	 */
	public GridFile newGridFile(Scenario scenario) {
		GridFile file = new GridFile();
		file.setScenarioName(scenario.getId());
		file.setShouldStream(this.streamAll);
		this.gridFiles.add(file);
		return file;
	}

	/**
	 * Create a new summary file and add it to the list of
	 * summary file outputs.
	 * @param scen The scenario to output the summary for.
	 */
	public SummaryFile newSummaryFile(Scenario scenario) {
		SummaryFile file = new SummaryFile();
		file.setScenName(scenario.getId());
		file.setShouldStream(this.streamAll);
		this.summaryFiles.add(file);
		return file;
	}

	/**
	 * Create a new stats file and add it to the list of
	 * stats file outputs.
	 * @param scen The scenario to output the stats for.
	 */
	public StatsFile newStatsFile(Scenario scenario) {
		StatsFile file = new StatsFile();
		file.setScenName(scenario.getId());
		file.setShouldStream(this.streamAll);
		this.statsFiles.add(file);
		return file;
	}

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		List<ValidationError> summaryErrs = new ArrayList<>();
		for (int i = 0; i < this.summaryFiles.size(); i++) {
			List<ValidationError> summary = this.summaryFiles.get(i).checkValid();
			if (summary.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in summary file at " + i + ".", this.summaryFiles);
				summary.stream().forEach(x -> temp.addChild(x));
				summaryErrs.add(temp);
			}
		}
		if (summaryErrs.size() > 0) {
			ValidationError temp = new ValidationError("summaryFiles", "Errors found in summary file outputs.", this);
			summaryErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> vectorErrs = new ArrayList<>();
		for (int i = 0; i < this.vectorFiles.size(); i++) {
			List<ValidationError> vector = this.vectorFiles.get(i).checkValid();
			if (vector.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in vector file at " + i + ".", this.vectorFiles);
				vector.stream().forEach(x -> temp.addChild(x));
				vectorErrs.add(temp);
			}
		}
		if (vectorErrs.size() > 0) {
			ValidationError temp = new ValidationError("vectorFiles", "Errors found in vector file outputs.", this);
			vectorErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> gridErrs = new ArrayList<>();
		for (int i = 0; i < this.gridFiles.size(); i++) {
			List<ValidationError> grid = this.gridFiles.get(i).checkValid();
			if (grid.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in grid file at " + i + ".", this.gridFiles);
				grid.stream().forEach(x -> temp.addChild(x));
				gridErrs.add(temp);
			}
		}
		if (gridErrs.size() > 0) {
			ValidationError temp = new ValidationError("gridFiles", "Errors found in grid file outputs.", this);
			gridErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> fuelErrs = new ArrayList<>();
		for (int i = 0; i < this.fuelGridFiles.size(); i++) {
			List<ValidationError> grid = this.fuelGridFiles.get(i).checkValid();
			if (grid.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in fuel grid file at " + i + ".", this.fuelGridFiles);
				grid.stream().forEach(x -> temp.addChild(x));
				fuelErrs.add(temp);
			}
		}
		if (fuelErrs.size() > 0) {
			ValidationError temp = new ValidationError("fuelGridFiles", "Errors found in fuel grid file outputs.", this);
			fuelErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> statsErrs = new ArrayList<>();
		for (int i = 0; i < this.statsFiles.size(); i++) {
			List<ValidationError> stats = this.statsFiles.get(i).checkValid();
			if (stats.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in statistics file at " + i + ".", this.statsFiles);
				stats.stream().forEach(x -> temp.addChild(x));
				statsErrs.add(temp);
			}
		}
		if (statsErrs.size() > 0) {
			ValidationError temp = new ValidationError("statsFiles", "Errors found in statistics file outputs.", this);
			statsErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> assetErrs = new ArrayList<>();
		for (int i = 0; i < this.assetStatsFiles.size(); i++) {
			List<ValidationError> stats = this.assetStatsFiles.get(i).checkValid();
			if (stats.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors found in asset statistics file at " + i + ".", this.assetStatsFiles);
				stats.stream().forEach(x -> temp.addChild(x));
				assetErrs.add(temp);
			}
		}
		if (assetErrs.size() > 0) {
			ValidationError temp = new ValidationError("assetStatsFiles", "Errors found in asset statistics file outputs.", this);
			assetErrs.stream().forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		return errs;
	}
}
