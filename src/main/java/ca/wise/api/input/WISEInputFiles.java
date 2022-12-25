package ca.wise.api.input;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.IWISESerializable;
import ca.wise.api.WISEException;
import ca.wise.api.SocketMsg;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WISEInputFiles implements IWISESerializable {

	/**
	 * The location of the projection file.
	 */
	@Getter
	private String projFile;

	/**
	 * The location of the projection file.
	 */
	public void setProjFile(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The projection file does not exist.");
		this.projFile = value;
	}

	/**
	 * The location of the LUT file.
	 */
	@Getter
	private String lutFile;

	/**
	 * The location of the LUT file.
	 */
	public void setLutFile(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The lookup table file does not exist.");
		this.lutFile = value;
	}

	/**
	 * The location of the fuel map file.
	 */
	@Getter
	private String fuelmapFile;

	/**
	 * The location of the fuel map file.
	 */
	public void setFuelmapFile(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The fuel map file does not exist.");
		this.fuelmapFile = value;
	}

	/**
	 * The location of the elevation file.
	 */
	@Getter
	private String elevFile;

	/**
	 * The location of the elevation file.
	 */
	public void setElevFile(String value) {
		if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
			throw new WISEException("The elevation file does not exist.");
		this.elevFile = value;
	}

	/**
	 * An array of fuel break files.
	 */
	@Getter
	@Builder.Default
	private final List<FuelBreak> fuelBreakFiles = new ArrayList<>();
	
	/**
	 * An array of fuel patch files.
	 */
	@Getter
	@Builder.Default
	private final List<FuelPatch> fuelPatchFiles = new ArrayList<>();
	
	/**
	 * An array of weather files.
	 */
	@Getter
	@Builder.Default
	private final List<WeatherGrid> weatherGridFiles = new ArrayList<>();
	
	/**
	 * An array of weather patch files.
	 */
	@Getter
	@Builder.Default
	private final List<WeatherPatch> weatherPatchFiles = new ArrayList<>();
	
	/**
	 * An array of grid files.
	 */
	@Getter
	@Builder.Default
	private final List<GridFile> gridFiles = new ArrayList<>();

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		if (Strings.isNullOrEmpty(this.projFile))
			errs.add(new ValidationError("projFile", "No projection file has been specified.", this));
		else if (!SocketMsg.skipFileTests && !this.projFile.startsWith("attachment:/") && !Files.exists(Paths.get(this.projFile)))
			errs.add(new ValidationError("projFile", "The specified projection file does not exist.", this));
		
		if (Strings.isNullOrEmpty(this.lutFile))
			errs.add(new ValidationError("lutFile", "No lookup table has been specified.", this));
		else if (!SocketMsg.skipFileTests && !this.lutFile.startsWith("attachment:/") && !Files.exists(Paths.get(this.lutFile)))
			errs.add(new ValidationError("lutFile", "The specified lookup table does not exist.", this));
		
		if (Strings.isNullOrEmpty(this.fuelmapFile))
			errs.add(new ValidationError("fuelmapFile", "No fuelmap file has been specified.", this));
		else if (!SocketMsg.skipFileTests && !this.fuelmapFile.startsWith("attachment:/") && !Files.exists(Paths.get(this.fuelmapFile)))
			errs.add(new ValidationError("fuelmapFile", "The specified fuelmap file does not exist.", this));
		
		if (Strings.isNullOrEmpty(this.elevFile))
			errs.add(new ValidationError("lutFile", "No elevation file has been specified.", this));
		else if (!SocketMsg.skipFileTests && !this.elevFile.startsWith("attachment:/") && !Files.exists(Paths.get(this.elevFile)))
			errs.add(new ValidationError("elevFile", "The specified elevation file does not exist.", this));
		
		List<ValidationError> tempErrs = new ArrayList<ValidationError>();
		for (int i = 0; i < this.fuelBreakFiles.size(); i++) {
			ValidationError fbErr = new ValidationError(i, "Errors found in fuelbreak at index " + i + ".", this.fuelBreakFiles);
			for (int j = i + 1; j < this.fuelBreakFiles.size(); j++) {
				if (this.fuelBreakFiles.get(i).getId().equalsIgnoreCase(this.fuelBreakFiles.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate fuelbreak IDs.", this.fuelBreakFiles.get(i));
					fbErr.addChild(err);
					break;
				}
			}
			this.fuelBreakFiles.get(i).checkValid().forEach(err -> fbErr.addChild(err));
			if (fbErr.getChildren().size() > 0) {
				tempErrs.add(fbErr);
			}
		}
		if (tempErrs.size() > 0) {
			ValidationError err = new ValidationError("fuelBreakFiles", "Errors found in fuelbreaks.", this);
			tempErrs.stream().forEach(x -> err.addChild(x));
			errs.add(err);
		}
		
		tempErrs = new ArrayList<ValidationError>();
		for (int i = 0; i < this.fuelPatchFiles.size(); i++) {
			ValidationError fpErr = new ValidationError(i, "Errors found in fuel patch at index " + i + ".", this.fuelPatchFiles);
			for (int j = i + 1; j < this.fuelPatchFiles.size(); j++) {
				if (this.fuelBreakFiles.get(i).getId().equalsIgnoreCase(this.fuelPatchFiles.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate fuel patch IDs.", this.fuelPatchFiles.get(i));
					fpErr.addChild(err);
					break;
				}
			}
			this.fuelPatchFiles.get(i).checkValid().forEach(err -> fpErr.addChild(err));
			if (fpErr.getChildren().size() > 0) {
				tempErrs.add(fpErr);
			}
		}
		if (tempErrs.size() > 0) {
			ValidationError err = new ValidationError("fuelPatchFiles", "Errors found in fuel patchs.", this);
			tempErrs.stream().forEach(x -> err.addChild(x));
			errs.add(err);
		}
		
		tempErrs = new ArrayList<ValidationError>();
		for (int i = 0; i < this.weatherGridFiles.size(); i++) {
			ValidationError wgErr = new ValidationError(i, "Errors found in weather grid at index " + i + ".", this.weatherGridFiles);
			for (int j = i + 1; j < this.weatherGridFiles.size(); j++) {
				if (this.weatherGridFiles.get(i).getId().equalsIgnoreCase(this.weatherGridFiles.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate weather grid IDs.", this.weatherGridFiles.get(i));
					wgErr.addChild(err);
					break;
				}
			}
			this.weatherGridFiles.get(i).checkValid().forEach(err -> wgErr.addChild(err));
			if (wgErr.getChildren().size() > 0) {
				tempErrs.add(wgErr);
			}
		}
		if (tempErrs.size() > 0) {
			ValidationError err = new ValidationError("weatherGridFiles", "Errors found in weather grids.", this);
			tempErrs.stream().forEach(x -> err.addChild(x));
			errs.add(err);
		}
		
		tempErrs = new ArrayList<ValidationError>();
		for (int i = 0; i < this.weatherPatchFiles.size(); i++) {
			ValidationError wpErr = new ValidationError(i, "Errors found in weather patch at index " + i + ".", this.weatherPatchFiles);
			for (int j = i + 1; j < this.weatherPatchFiles.size(); j++) {
				if (this.weatherPatchFiles.get(i).getId().equalsIgnoreCase(this.weatherPatchFiles.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate weather patch IDs.", this.weatherPatchFiles.get(i));
					wpErr.addChild(err);
					break;
				}
			}
			this.weatherPatchFiles.get(i).checkValid().forEach(err -> wpErr.addChild(err));
			if (wpErr.getChildren().size() > 0) {
				tempErrs.add(wpErr);
			}
		}
		if (tempErrs.size() > 0) {
			ValidationError err = new ValidationError("weatherPatchFiles", "Errors found in weather patchs.", this);
			tempErrs.stream().forEach(x -> err.addChild(x));
			errs.add(err);
		}
		
		tempErrs = new ArrayList<ValidationError>();
		for (int i = 0; i < this.gridFiles.size(); i++) {
			ValidationError wgErr = new ValidationError(i, "Errors found in grid file at index " + i + ".", this.gridFiles);
			for (int j = i + 1; j < this.gridFiles.size(); j++) {
				if (this.gridFiles.get(i).getId().equalsIgnoreCase(this.gridFiles.get(j).getId())) {
					ValidationError err = new ValidationError("id", "Duplicate grid file IDs.", this.fuelBreakFiles.get(i));
					wgErr.addChild(err);
					break;
				}
			}
			this.gridFiles.get(i).checkValid().forEach(err -> wgErr.addChild(err));
			if (wgErr.getChildren().size() > 0) {
				tempErrs.add(wgErr);
			}
		}
		if (tempErrs.size() > 0) {
			ValidationError err = new ValidationError("gridFiles", "Errors found in grid files.", this);
			tempErrs.stream().forEach(x -> err.addChild(x));
			errs.add(err);
		}
		
		return errs;
	}
	
	public static class WISEInputFilesBuilder {
		private String projFile;
		private String lutFile;
		private String fuelmapFile;
		private String elevFile;

		/**
		 * The location of the projection file.
		 */
		public WISEInputFilesBuilder projFile(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The projection file does not exist.");
			this.projFile = value;
			return this;
		}

		/**
		 * The location of the LUT file.
		 */
		public WISEInputFilesBuilder lutFile(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The lookup table file does not exist.");
			this.lutFile = value;
			return this;
		}

		/**
		 * The location of the fuel map file.
		 */
		public WISEInputFilesBuilder fuelmapFile(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The fuel map file does not exist.");
			this.fuelmapFile = value;
			return this;
		}

		/**
		 * The location of the elevation file.
		 */
		public WISEInputFilesBuilder elevFile(String value) {
			if (SocketMsg.inlineThrowError && !SocketMsg.skipFileTests && !value.startsWith("attachment:/") && !Files.exists(Paths.get(value)))
				throw new WISEException("The elevation file does not exist.");
			this.elevFile = value;
			return this;
		}
	}
}
