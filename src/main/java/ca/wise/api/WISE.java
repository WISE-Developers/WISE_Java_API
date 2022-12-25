package ca.wise.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import ca.wise.api.admin.MqttSettings;
import ca.wise.api.fuels.FuelDefinition;
import ca.wise.api.input.WISEInputs;
import ca.wise.api.output.OutputStreamInfo;
import ca.wise.api.output.WISEOutputs;
import ca.wise.api.output.UnitSettings;
import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WISE implements IWISESerializable {
	
	/**
	 * Optional user comments about the code.
	 */
	@Getter @Setter
	@Builder.Default
	private String comments = "";
	
	/**
	 * Files that are needed as input for the job.
	 */
	@Getter @Setter
	@Builder.Default
	private WISEInputs inputs = new WISEInputs();
	
	/**
	 * Files that will be output from the job.
	 */
	@Getter @Setter
	@Builder.Default
	private WISEOutputs outputs = new WISEOutputs();
	
	/**
	 * Settings that modify W.I.S.E.'s behaviour at the end of each timestep.
	 */
	@Getter @Setter
	@Builder.Default
	private TimestepSettings timestepSettings = new TimestepSettings();
	
	/**
	 * Details of a service to stream output files to after all
	 * simulations have completed.
	 */
	@Getter
	@Builder.Default
	private List<OutputStreamInfo> streamInfo = new ArrayList<>();
	
	/**
	 * Settings that define which units will be used when data is exported in summary
	 * or statistics files.
	 */
	@Getter @Setter
	@Builder.Default
	private UnitSettings exportUnits = new UnitSettings();
	
	/**
	 * Options concerning how to run the job, not related directly
	 * to scenarios or fire growth.
	 */
	@Getter @Setter
	@Builder.Default
	private JobOptions jobOptions = new JobOptions();
	
	@Getter
	@Builder.Default
	private final List<FileAttachment> attachments = new ArrayList<>();
	
	/**
	 * Change the MQTT connection settings of the instance that runs the generated FGM.
	 * This is an administrator operation and requires the FGM builder be started with
	 * the correct permissions to allow this operation.
	 */
	@Getter @Setter
	private MqttSettings mqttSettings;
	
	@Builder.Default
	private int attachmentIndex = 1;

	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();
		
		List<ValidationError> inputErrs = this.inputs.checkValid();
		if (inputErrs.size() > 0) {
			ValidationError temp = new ValidationError("inputs", "Errors in W.I.S.E. input values.", this);
			inputErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> outputErrs = this.outputs.checkValid();
		if (outputErrs.size() > 0) {
			ValidationError temp = new ValidationError("outputs", "Errors in W.I.S.E. output values.", this);
			outputErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> timestepErrs = this.timestepSettings.checkValid();
		if (timestepErrs.size() > 0) {
			ValidationError temp = new ValidationError("timestepSettings", "Errors in W.I.S.E. timestep settings.", this);
			timestepErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> streamErrs = new ArrayList<>();
		for (int i = 0; i < this.streamInfo.size(); i++) {
			List<ValidationError> stream = this.streamInfo.get(i).checkValid();
			if (stream.size() > 0) {
				ValidationError temp = new ValidationError(i, "Errors in W.I.S.E. stream info at " + i + ".", this.streamInfo);
				stream.forEach(x -> temp.addChild(x));
				errs.add(temp);
			}
		}
		if (streamErrs.size() > 0) {
			ValidationError temp = new ValidationError("streamInfo", "Errors in W.I.S.E. stream info.", this);
			streamErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> unitErrs = this.exportUnits.checkValid();
		if (unitErrs.size() > 0) {
			ValidationError temp = new ValidationError("exportUnits", "Errors in W.I.S.E. unit settings.", this);
			unitErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		List<ValidationError> jobErrs = this.jobOptions.checkValid();
		if (jobErrs.size() > 0) {
			ValidationError temp = new ValidationError("jobOptions", "Errors in W.I.S.E. job options.", this);
			jobErrs.forEach(x -> temp.addChild(x));
			errs.add(temp);
		}
		
		return errs;
	}
	
	public String setLutDefinition(List<FuelDefinition> fuels, String filename) {
		String s = "API_FUEL_DEF";
		for (FuelDefinition fuel : fuels) {
			s += "|API_FUEL|";
			s += fuel.toString();
		}
		String name = this.addAttachment(filename, s);
		if (name == null)
			throw new WISEException("Invalid LUT definition. Unable to attach to job.");
		this.inputs.getFiles().setLutFile(name);
		return name;
	}

	/**
	 * Test the validity of a filename.
	 * - The filename must not contain any of the following characters: \ / : * ? " < > |
	 * - The filename must not begin with a dot (.)
	 * - The filename may not be any of the following: nul, prn, con, aux, lpt#, com#
	 * @param filename The filename to test for validity.
	 */
	private boolean validateFilename(String filename) {
		return !filename.matches("^[^\\\\/:\\*\\?\"<>\\|]+$") && !filename.matches("^\\.") && !filename.matches("^(nul|prn|con|aux|lpt[0-9]|com[1-9])(\\.|$)");
	}

	/**
	 * Add a file attachment to the project. Attachments can be used anywhere a filename would be used.
	 * @param filename The name of the file to attach. Must be a valid Windows filename. See {@link validateFilename}
	 * @param contents The file contents. Must still be valid if streamed to a file with UTF-8 encoding.
	 * @returns Will return null if the filename is not valid, otherwise the URL to use as the filename
	 *          when referencing the attachment will be returned.
	 */
	public String addAttachment(String filename, String contents) {
		if (!validateFilename(filename))
			return null;
		try {
    		String name = "attachment:/" + this.attachmentIndex + "/" + URLEncoder.encode(filename, "UTF-8");
    		this.attachmentIndex++;
    		FileAttachment stream = new FileAttachment(name, contents);
    		this.attachments.add(stream);
    		return name;
		}
		catch (UnsupportedEncodingException e) {
		    return null;
		}
	}

	/**
	 * Add a file attachment to the project. Attachments can be used anywhere a filename would be used.
	 * @param filename The name of the file to attach. Must be a valid Windows filename. See {@link validateFilename}
	 * @param data The file contents. Must still be valid if streamed to a file with UTF-8 encoding.
	 * @returns Will return null if the filename is not valid, otherwise the URL to use as the filename
	 *          when referencing the attachment will be returned.
	 */
	public String addAttachment(String filename, byte[] data) {
		if (!validateFilename(filename))
			return null;
        try {
    		String name = "attachment:/" + this.attachmentIndex + "/" + URLEncoder.encode(filename, "UTF-8");
    		this.attachmentIndex++;
    		FileAttachment stream = new FileAttachment(name, data);
    		this.attachments.add(stream);
    		return name;
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
	}
}
